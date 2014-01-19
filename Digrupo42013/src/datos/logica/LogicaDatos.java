
package datos.logica;

import datos.pojos.Configuracion;
import datos.pojos.Itinerario;
import datos.pojos.Sesion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton que contiene los métodos para los procesos de interacción con la
 * base de datos
 *
 * @author Andrés Traspuesto Lanza
 */
public enum LogicaDatos {

    INSTANCE;
    private Connection con;

    /**
     * Constructor del singleton encargado de la lógica de interacción con la
     * base de datos
     */
    LogicaDatos() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:entrenoBD");
            createTables();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * La finalidad de este método es crear las tablas de la base de datos.
     */
    public void createTables() {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("PRAGMA foreign_keys = ON");
            String sql = "CREATE TABLE IF NOT EXISTS TipoSesion( "
                    + "p_tipo INTEGER PRIMARY KEY, "
                    + "tipo TEXT COLLATE NOCASE, UNIQUE(tipo)ON CONFLICT ABORT "
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS Sesion ( "
                    + "p_sesion INTEGER PRIMARY KEY, "
                    + "fecha_inicio INTEGER, "
                    + "fecha_fin INTEGER, "
                    + "descripcion TEXT, "
                    + "a_tipo INTEGER, "
                    + "FOREIGN KEY(a_tipo) REFERENCES TipoSesion(p_tipo), "
                    + "UNIQUE (fecha_inicio, fecha_fin) ON CONFLICT ABORT "
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS Itinerario ( "
                    + "p_itinerario INTEGER PRIMARY KEY, "
                    + "nombre TEXT COLLATE NOCASE, "
                    + "localizacion TEXT COLLATE NOCASE, "
                    + "dificultad TEXT, "
                    + "imagen TEXT,"
                    + " UNIQUE (nombre, localizacion) ON CONFLICT ABORT "
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS FechaItinerario ( "
                    + "fecha INTEGER, "
                    + "a_itinerario INTEGER, "
                    + "FOREIGN KEY(a_itinerario) REFERENCES Itinerario(p_itinerario), "
                    + "PRIMARY KEY(fecha, a_itinerario) "
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS Configuracion( "
                    + "p_configuracion INTEGER PRIMARY KEY, "
                    + "nombre TEXT COLLATE NOCASE, "
                    + "apellidos TEXT COLLATE NOCASE, "
                    + "fecha1 INTEGER, "
                    + "fecha2 INTEGER, "
                    + "UNIQUE (nombre, apellidos) ON CONFLICT ABORT "
                    + ")";
            st.executeUpdate(sql);
            sql = "INSERT OR IGNORE INTO TipoSesion (tipo) VALUES ( ";
            st.executeUpdate(sql+"'Físico')");
            st.executeUpdate(sql+"'Rocódromo')");
            st.executeUpdate(sql+"'Roca')");

        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creo el directorio para almacenar las imágenes si no existe
        File path = new File("imagenes");
        if (!path.exists()) {
            path.mkdir();
        }
    }

    /*
     ========================================================================
     ............................SELECTS.....................................
     ========================================================================
     */
    /**
     * Devuelve el usuario almacenado en la base de datos
     *
     * @return
     */
    public Configuracion getConfiguracion() {
        Configuracion usr = null;
        try (Statement st = con.createStatement()) {
            String sql = "SELECT nombre, apellidos, fecha1, fecha2 FROM Configuracion";
            ResultSet rs = st.executeQuery(sql);
            usr = new Configuracion();
            while (rs.next()) {
                usr.setNombre(rs.getString("nombre"));
                usr.setApellidos(rs.getString("apellidos"));
                usr.setFecha1Intervalo(getDateDeSQLite3(rs.getLong("fecha1")));
                usr.setFecha2Intervalo(getDateDeSQLite3(rs.getLong("fecha2")));
                usr.setRendimiento(calculaRendimiento());

            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }

    /**
     * Devuelve todas las sesiones almacenadas en una lista
     *
     * @return
     */
    public List<Sesion> getAllSesion() {
        List<Sesion> sesiones = new ArrayList<>();
        try (Statement st = con.createStatement()) {
            String sql = "SELECT fecha_inicio, fecha_fin, a_tipo, p_sesion, descripcion FROM Sesion";
            ResultSet rs = st.executeQuery(sql);
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sesiones;
    }

    /**
     * Devuelve una lista de las sesiones de entrenamiento que cumplan la condición
     * impuesta por la fecha pasada como parámetro y el comparador
     *
     * @param fecha
     * @param cmp
     * @return
     */
    public List<Sesion> getSesionByFecha(Date fecha, Comparador cmp) {
        List<Sesion> sesiones = new ArrayList<>();
        String sql = "SELECT fecha_inicio, fecha_fin, a_tipo, p_sesion, descripcion "
                + "FROM Sesion WHERE  date(fecha_inicio,'unixepoch') "+cmp+" date(?,'unixepoch');";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setLong(1, getSegundosParaSQLite3(fecha));
            ResultSet rs = st.executeQuery();
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sesiones;
    }

    /**
     * Devuelve una lista de las sesiones de entrenamiento de un periodo de
     * tiempo determinado por las fecha parámetro
     *
     * @param fecha1 primera fecha incluida en el intervalo de tiempo
     * @param fecha2 última fecha incluida en el intervalo de tiempo
     * @return
     */
    public List<Sesion> getSesionBetweenFechas(Date fecha1, Date fecha2) {
        List<Sesion> sesiones = new ArrayList<>();
        String sql = "SELECT fecha_inicio, fecha_fin, a_tipo, p_sesion, descripcion "
                + "FROM Sesion WHERE  date(fecha_inicio,'unixepoch') BETWEEN "
                + "date(?,'unixepoch') AND date(?,'unixepoch');";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setLong(1, getSegundosParaSQLite3(fecha1));
            st.setLong(2, getSegundosParaSQLite3(fecha2));
            ResultSet rs = st.executeQuery();
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sesiones;
    }

    /**
     * Devuelve todas las sesiones de un determinado tipo
     *
     * @param tipo
     * @return
     */
    public List<Sesion> getSesionByTipo(Sesion.TipoSesion tipo) {
        List<Sesion> sesiones = new ArrayList<>();
        String sql = "SELECT fecha_inicio, fecha_fin, a_tipo, p_sesion, descripcion "
                + "FROM Sesion WHERE  a_tipo = ?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, tipo.ordinal());
            ResultSet rs = st.executeQuery();
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sesiones;
    }
    /**
     * Devuelve todos los itinerarios contenidos en la BD en un List
     * @return 
     */
    public List<Itinerario> getAllItinerario() {
        String sql = "SELECT p_itinerario, nombre, localizacion, dificultad, imagen FROM Itinerario";
        List<Itinerario> itinerarios = new ArrayList<>();
        try(Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;
        
    }
    /**
     * Devuelve todos los resultados comparando las fechas de resolución, según
     * el operador lógico almacenado en el comparador, con la fecha pasada como
     * parámetro.
     * determinada fecha.
     * @param fecha
     * @param cmp
     * @return 
     */
    public List<Itinerario> getItinerariosByFecha(Date fecha, Comparador cmp) {
        List<Itinerario> itinerarios = new ArrayList<>();
        String sql = "SELECT DISTINCT p_itinerario, nombre, localizacion, dificultad, imagen "
                + "FROM Itinerario WHERE p_itinerario IN "
                + "(SELECT a_itinerario FROM FechaItinerario "
                + "WHERE date(fecha,'unixepoch')"+cmp+" date(?,'unixepoch'))";
        try(PreparedStatement st = con.prepareStatement(sql)) {
            st.setLong(1, getSegundosParaSQLite3(fecha));
            ResultSet rs = st.executeQuery();
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;
    }
    public List<Itinerario> getItinerariosByFechaRange(Date fecha1, Date fecha2) {
        List<Itinerario> itinerarios = new ArrayList<>();
        String sql = "SELECT DISTINCT p_itinerario, nombre, localizacion, dificultad, imagen "
                + "FROM Itinerario WHERE p_itinerario IN "
                + "(SELECT a_itinerario FROM FechaItinerario "
                + "WHERE date(fecha,'unixepoch') BETWEEN date(?,'unixepoch') AND date(?,'unixepoch'))";
        try(PreparedStatement st = con.prepareStatement(sql)) {
            st.setLong(1, getSegundosParaSQLite3(fecha1));
            st.setLong(2, getSegundosParaSQLite3(fecha2));
            ResultSet rs = st.executeQuery();
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;
    }
    public List<Itinerario> getItinerariosByDificultad(String dificultad) {
        List<Itinerario> itinerarios = new ArrayList<>();
        String sql = "SELECT DISTINCT p_itinerario, nombre, localizacion, dificultad, imagen "
                + "FROM Itinerario WHERE p_itinerario IN "
                + "(SELECT a_itinerario FROM FechaItinerario "
                + "WHERE date(fecha,'unixepoch') BETWEEN date(?,'unixepoch') AND date(?,'unixepoch'))";
        try(PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;
    }
    /*
     ========================================================================
     ............................UPDATES.....................................
     ========================================================================
     */
    

    /**
     * Actualiza los datos del usuario
     *
     * @param usr
     */
    public void updateConfiguracion(Configuracion usr) {
        String sql = "UPDATE Configuracion SET nombre = ?, apellidos = ?, "
                + "fecha1 = ?, fecha2 = ? WHERE p_usuario = 0";
        executeUpdateOnConfiguracion(sql, usr);
    }

    /**
     * Modifica la sesion en la base de datos
     *
     * @param sesion
     */
    public void updateSesion(Sesion sesion) {
        String sql = "UPDATE Sesion SET fecha_inicio = ?, fecha_fin = ?, a_tipo = ?,"
                + " descripcion = ? WHERE p_sesion = ?";
        executeUpdateOnSesion(sql, sesion);
    }
    /**
     * Modifica los datos del itinerario para que concuerden con los contenidos
     * por el Itinerario pasado como parámetro, el segundo parámetro especifica que
     * es lo que se quiere actualizar del itinerario
     * @param itinerario
     * @param tipo 
     * @param img contiene el archivo de imagen que quiere asociarse al itinerario,
     *  si no se quiere modificar la imagen debe ser null
     */
    public void updateItinerario(Itinerario itinerario, ModItinerario tipo, File img) {
        switch (tipo) {
            case SOLO_IMAGEN:
                updateImagenItinerario(itinerario, img);
                break;
            case SOLO_FECHAS:
                updateFechasItinerario(itinerario);
                break;
            case SOLO_NOMBRE_LOCALIZACION_DIFICULTAD:
                updateNombreYDificultad(itinerario);
                break;
            default:
                updateFechasItinerario(itinerario);
                updateImagenItinerario(itinerario, img);
                updateNombreYDificultad(itinerario);
        }
        
    }
    
    
    /*
     ========================================================================
     ............................INSERTS.....................................
     ========================================================================
     */
    /**
     * Inserta la configuracion en la base de datos
     *
     * @param usr
     */
    public void insertConfiguracion(Configuracion usr) {
        String sql = "INSERT INTO Configuracion (nombre, apellidos, fecha1, fecha2) "
                + "VALUES(?,?,?,?)";
        executeUpdateOnConfiguracion(sql, usr);
    }

    /**
     * Inserta la Sesion en la base de datos
     *
     * @param sesion
     */
    public void insertSesion(Sesion sesion) {
        String sql = "INSERT INTO Sesion (fecha_inicio, fecha_fin, a_tipo, descripcion)"
                + " VALUES(?,?,?,?)";
        executeUpdateOnSesion(sql, sesion);
    }

    /**
     * Inserta un itinerario en la base de datos
     *
     * @param itinerario
     */
    public void insertItinerario(Itinerario itinerario) {
        String sql = "INSERT INTO Itinerario (nombre, localizacion, dificultad, imagen)"
                + " VALUES(?,?,?,?)";
        File imgFile;
        if (itinerario.getPathImagen() != null) {
            imgFile = getFileImagen(itinerario.getPathImagen());
        } else {
            imgFile = new File("imagenes/sinImagen.jpg");
        }
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, itinerario.getNombre());
            pst.setString(2, itinerario.getLocalizacion());
            pst.setString(3, itinerario.getDifucultad());
            pst.setString(4, imgFile.getName());
            pst.executeUpdate();
            itinerario.setpItinerario(getLastItinerario());
            //Si hay fecha de resolución del itinerario la almaceno
            if (!itinerario.getFechasResolucion().isEmpty()) {
                insertaFechaItinerario(itinerario, itinerario.getFechasResolucion().get(0));
            }
            if (itinerario.getPathImagen() != null) {
                guardaArchivoImagen(itinerario.getPathImagen(), imgFile);
            }
            itinerario.setPathImagen(imgFile);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Inserta una fecha de resolución en el itinerario
     * @param itinerario
     * @param fecha 
     */
    public void insertaFechaItinerario(Itinerario itinerario, Date fecha) {
        String sql = "INSERT INTO FechaItinerario (a_itinerario, fecha) "
                + "VALUES (?,?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, itinerario.getpItinerario());
            pst.setLong(2, getSegundosParaSQLite3(fecha));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     ========================================================================
     ....................MÉTODOS PRIVADOS AUXILIARES.........................
     ========================================================================
     */
    /**
     * Modifica las fechas en las que se ha resuelto el itinerario, NO DEBE USARSE
     * PARA AÑADIR UNA FECHA DE RESOLUCIÓN, para eso se usa insertaFechaItinerario
     * @param itinerario 
     */
    private void updateFechasItinerario(Itinerario itinerario) {
        String sql = "DELETE FROM Itinerario WHERE a_itinerario = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, itinerario.getpItinerario());
            pst.executeUpdate();
            insertAllFechasItinerario(itinerario);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Inserta todas las fechas de resolución del itinerario en la tabla
     * FechaItinerario
     * @param itinerario 
     */
    private void insertAllFechasItinerario(Itinerario itinerario) {
        for (Date fecha: itinerario.getFechasResolucion()) {
            insertaFechaItinerario(itinerario, fecha);
        }
    }

    /**
     * Se encarga de hacer insert o update, en función del sql en la tabla
     * Configuracion
     *
     * @param sql
     * @param usr
     */
    private void executeUpdateOnConfiguracion(String sql, Configuracion usr) {
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, usr.getNombre());
            pst.setString(2, usr.getApellidos());
            pst.setLong(3, getSegundosParaSQLite3(usr.getFecha1Intervalo()));
            pst.setLong(4, getSegundosParaSQLite3(usr.getFecha2Intervalo()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Se encarga de hacer insert o update, en función del sql en la tabla
     * Sesion
     *
     * @param sesion
     */
    private void executeUpdateOnSesion(String sql, Sesion sesion) {
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, getSegundosParaSQLite3(sesion.getFecha_hora1()));
            pst.setLong(2, getSegundosParaSQLite3(sesion.getFecha_hora2()));
            pst.setInt(3, sesion.getTipo().ordinal());
            pst.setString(4, sesion.getDescripcion());
            if (sesion.getpSesion() > 0) {
                pst.setInt(5, sesion.getpSesion());
            }
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que se encarga de cargar una lista con obejetos Sesion creados a
     * partir de un ResultSet
     *
     * @param rs
     * @param sesiones
     * @throws SQLException
     */
    private void cargaListaSesiones(ResultSet rs, List<Sesion> sesiones) throws SQLException {
        Sesion sesion;
        while (rs.next()) {
            sesion = new Sesion();
            sesion.setDescripcion(rs.getString("descripcion"));
            sesion.setpSesion(rs.getInt("p_sesion"));
            sesion.setTipo(Sesion.TipoSesion.values()[rs.getInt("a_tipo")]);
            sesion.setFecha_hora1(getDateDeSQLite3(rs.getLong("fecha_inicio")));
            sesion.setFecha_hora2(getDateDeSQLite3(rs.getLong("fecha_fin")));
            sesiones.add(sesion);
        }
    }

   
    /**
     * Devuelve un objeto Date a partir del tiempo en segundos desde 1/1/1970
     * almacenado en SQLite3
     *
     * @param segundos
     * @return
     */
    private Date getDateDeSQLite3(long segundos) {
        return new Date(segundos * 1000);
    }

    /**
     * Devuelve el tiempo en segundos desde 1/1/1970 a partir de un Date
     *
     * @param date
     * @return
     */
    private long getSegundosParaSQLite3(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * Encuentra un nombre que no exista para guardar la imagen
     *
     * @param pathImagen
     * @return
     */
    private File getFileImagen(File pathImagen) {
        File aux = new File("imagenes" + File.separatorChar + "_0" + pathImagen.getName());
        int i = 1;
        while (aux.exists()) {
            aux = new File("imagenes" + File.separatorChar + "_" + (i++) + pathImagen.getName());
        }
        return aux;
    }
    /**
     * Devuelve el p_itinerario del último itinerario registrado
     * @return 
     */
    private int getLastItinerario() {
        String sql = "SELECT max(p_itinerario) as m from Itinerario";
        int  max = Integer.MIN_VALUE;
        try(Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) max = rs.getInt("m");
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    
    /**
     * Actualiza el nombre y la dificultad de un itinerario dado
     * @param itinerario 
     */
    private void updateNombreYDificultad(Itinerario itinerario) {
        String sql = "UPDATE Itinerario SET nombre = ?, dificultad = ?, localizacion = ? "
                + "WHERE p_itinerario = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, itinerario.getNombre());
            pst.setString(2, itinerario.getDifucultad());
            pst.setString(3, itinerario.getLocalizacion());
            pst.setInt(4, itinerario.getpItinerario());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Actualiza la imagen asociada al itinerario
     * @param itinerario
     * @param newImg 
     */
    private void updateImagenItinerario(Itinerario itinerario, File newImg) {
        if(!itinerario.getPathImagen().getName().equals("sinImagen.jpg"))itinerario.getPathImagen().delete();
        File img = getFileImagen(newImg);
        String sql = "UPDATE Itinerario SET imagen = ? WHERE p_itinerario = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, img.getName());
            pst.setInt(2, itinerario.getpItinerario());
            pst.executeUpdate();
            guardaArchivoImagen(newImg, img);
            itinerario.setPathImagen(img);
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * Guarda una copia del archivo de origen src en el destino dst
     * @param src
     * @param dst 
     */
    private void guardaArchivoImagen(File src, File dst) {
        try(FileInputStream fis = new FileInputStream(src); 
                FileOutputStream fos = new FileOutputStream(dst)){
            byte[] buffer = new byte[1024];
            int leido;
            while((leido = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, leido);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    /**
     * Carga en el itinerario todas las fechas en las que ha sido resuelto
     * @param itinerario 
     */
    private void cargaFechasItinerario(Itinerario itinerario) {
        String sql = "SELECT fecha FROM FechaItinerario WHERE a_itinerario = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, itinerario.getpItinerario());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                itinerario.addFechaResolucion(getDateDeSQLite3(rs.getLong("fecha")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Se encarga de cargar los Itinerarios contenidos en el ResultSet dentro
     * de la lista de itinerarios
     * @param rs
     * @param itinerarios
     * @throws SQLException 
     */
    private void cargaItinerarios(ResultSet rs, List<Itinerario> itinerarios) throws SQLException {
        Itinerario itinerario;
        while (rs.next()) {
            itinerario = new Itinerario();
            itinerario.setNombre(rs.getString("nombre"));
            itinerario.setLocalizacion(rs.getString("localizacion"));
            itinerario.setDifucultad(rs.getString("dificultad"));
            itinerario.setpItinerario(rs.getInt("p_itinerario"));
            itinerario.setPathImagen(new File("imagenes"+File.separatorChar+rs.getString("imagen")));
            cargaFechasItinerario(itinerario);
            itinerarios.add(itinerario);
        }
    }
     /**
     ******************************************
     * **********SIN IMPLEMENTAR**************
     * *************************************
     *
     * Método que calcula el rendimiento del usuario
     *
     * @return
     */
    public float calculaRendimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Calcula los puntos debidos a las horas acumuladas por sesiones de 
     * entrenamiento
     * @param cfg
     * @return 
     */
    private float calcPtonPorSesiones(Configuracion cfg) {
        float ptos = 0F;
        String sql = "SELECT SUM(fecha_fin-fecha_inicio)/3600 AS horas"
                + "FROM Sesion "
                + "WHERE date(fecha_inicio,'unixepoch') "
                + "BETWEEN date(?,'unixepoch') AND date(?,'unixepoch')";
        try(PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, getSegundosParaSQLite3(cfg.getFecha1Intervalo()));
            pst.setLong(2, getSegundosParaSQLite3(cfg.getFecha2Intervalo()));
            ResultSet rs = pst.executeQuery();
            rs.next();
            ptos = rs.getInt("horas")*0.5F;
            ptos = ptos > 5?  5 : ptos;
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ptos;
    }
}
