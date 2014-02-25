package datos;

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton que contiene los métodos comunes para trabajar con la base de
 * datos, así como aquellos cuya finalidad no es recuperar o insertar objetos
 * Itinerario, Sesion o Configuracion en la BD.
 *
 * @author Andrés Traspuesto Lanza
 */
public enum UtilesBD {

    INSTANCE;

    private final static SimpleDateFormat sdfYMD_hms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
    private final String BASE_DATOS = "jdbc:hsqldb:file:db" + File.separatorChar + "escalador.db;";
    private final String USUARIO = "sa";
    private final String PASS = "";
    private final String EXIST = "ifexists=true;";
    private final String SHUTDOWN = "shutdown=true";
    private final String FILE_SCRIPT = "db" + File.separatorChar + "escalador.db.script";
    private final String DIR_IMAGENES = "db" + File.separatorChar + "imagenes";

    /**
     * Constructor del singleton encargado de la lógica de interacción con la
     * base de datos
     */
    UtilesBD() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            if (!new File(FILE_SCRIPT).exists()) { //Si la base de datos no existe la crea
                createTables(BASE_DATOS + SHUTDOWN, USUARIO, PASS, DIR_IMAGENES);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una conexión a la base de datos
     *
     * @return
     */
    public Connection getConnection() {
        return getConnection(BASE_DATOS + EXIST + SHUTDOWN, USUARIO, PASS);
    }

    /**
     * Devuelve una conexión a la base de datos
     *
     * @param baseDatos
     * @param usr
     * @param pass
     * @return
     */
    public Connection getConnection(String baseDatos, String usr, String pass) {
        Connection db = null;
        try {
            db = DriverManager.getConnection(baseDatos, usr, pass);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    /**
     * La finalidad de este método es crear las tablas de la base de datos.
     *
     * @param baseDatos
     * @param usr
     * @param pass
     * @param carpetaImagenes
     */
    public void createTables(String baseDatos, String usr, String pass, String carpetaImagenes) {
        try (Connection bd = getConnection(baseDatos, usr, pass);
                Statement st = bd.createStatement()) {
            String sql = "CREATE TABLE TipoSesion(\n"
                    + "p_tipo INTEGER IDENTITY,\n"
                    + "tipo VARCHAR(12), \n"
                    + "UNIQUE(tipo)\n"
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE Sesion (\n"
                    + "p_sesion INTEGER IDENTITY,\n"
                    + "fh_inicio DATETIME,\n"
                    + "fh_fin DATETIME,\n"
                    + "descripcion VARCHAR(200),\n"
                    + "a_tipo INTEGER,\n"
                    + "FOREIGN KEY(a_tipo) REFERENCES TipoSesion(p_tipo),\n"
                    + "UNIQUE (fh_inicio, fh_fin)\n"
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE Itinerario (\n"
                    + "p_itinerario INTEGER IDENTITY,\n"
                    + "nombre VARCHAR(150),\n"
                    + "localizacion VARCHAR(200),\n"
                    + "dificultad VARCHAR(10),\n"
                    + "tipo VARCHAR(8),"
                    + "imagen VARCHAR(200),\n"
                    + "UNIQUE (nombre, localizacion) \n"
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE FechaItinerario (\n"
                    + "fecha DATETIME,\n"
                    + "a_itinerario INTEGER,\n"
                    + "FOREIGN KEY(a_itinerario) REFERENCES Itinerario(p_itinerario) ON DELETE CASCADE,\n"
                    + "PRIMARY KEY(fecha, a_itinerario)\n"
                    + ")";
            st.executeUpdate(sql);
            sql = "CREATE TABLE Configuracion(\n"
                    + "p_configuracion INTEGER IDENTITY,\n"
                    + "nombre VARCHAR(50),\n"
                    + "apellidos VARCHAR(100),\n"
                    + "fecha1 DATETIME,\n"
                    + "fecha2 DATETIME,\n"
                    + "UNIQUE (nombre, apellidos)\n"
                    + ")";
            st.executeUpdate(sql);
            sql = "INSERT INTO TipoSesion (tipo) VALUES ( ";
            st.executeUpdate(sql + "'Físico')");
            st.executeUpdate(sql + "'Rocódromo')");
            st.executeUpdate(sql + "'Roca')");
            st.execute("shutdown");

        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creo el directorio para almacenar las imágenes si no existe
        File path = new File(carpetaImagenes);
        if (!path.exists()) {
            path.mkdir();
        }
    }

    /**
     * Se encarga de salvar el contenido en memoria de la BD en el archivo de BD
     *
     * @param baseDatos
     * @param usr
     * @param pass
     */
    public void saveData(String baseDatos, String usr, String pass) {
        Connection db = getConnection(baseDatos, usr, pass);
        try {
            db.createStatement().execute("shutdown");
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Se encarga de salvar el contenido en memoria de la BD en el archivo de BD
     *
     */
    public void saveData() {
        saveData(BASE_DATOS, USUARIO, PASS);
    }

    /**
     * Método que calcula el rendimiento del usuario
     *
     * @param usr
     * @param baseDatos
     * @param pass
     * @return
     */
    public float calculaRendimiento(String baseDatos, String usr, String pass) {
        return calcPtosPorSesiones(baseDatos, usr, pass) + calcPtosPorItinerario(baseDatos, usr, pass);
    }

    /**
     * Método que calcula el rendimiento del usuario
     *
     * @return
     */
    public float calculaRendimiento() {
        return calculaRendimiento(BASE_DATOS, USUARIO, PASS);
    }

    /**
     * Devuelve un objeto Date a partir del tiempo en segundos desde 1/1/1970
     * almacenado en SQLite3
     *
     * @param segundos
     * @return
     */
    public Date getDateDeHSQLDB(long segundos) {
        return new Date(segundos * 1000);
    }

    /**
     * Devuelve l literal correspondiente a la fecha indicada en formato
     * año-mes-dia horas:minutos:segundos
     *
     * @param date
     * @return
     */
    public String getDateTimeForHSQLDB(Date date) {
        return sdfYMD_hms.format(date);
    }

    /**
     * Devuelve el literal correspondiente a la fecha indicada en formato
     * año-mes-dia
     *
     * @param date
     * @return
     */
    public String getDateForHSQLDB(Date date) {
        return sdfYMD.format(date);
    }

    /**
     * Encuentra un nombre que no exista para guardar la imagen
     *
     * @param pathImagen
     * @return
     */
    public File getFileImagen(File pathImagen) {
        File aux;
        if (pathImagen.equals(new File(getClass().getClassLoader().getResource("resources/sinImagen.jpg").getFile()))) {
            aux = null;
        } else {
            aux = new File(DIR_IMAGENES + File.separatorChar + "_0" + pathImagen.getName());
            int i = 1;
            while (aux.exists()) {
                aux = new File(DIR_IMAGENES + File.separatorChar + "_" + (i++) + pathImagen.getName());
            }
        }
        return aux;
    }

    /**
     * Guarda una copia del archivo de origen src en el destino dst
     *
     * @param src
     * @param dst
     */
    public void guardaArchivoImagen(File src, File dst) {
        if (!src.getName().equalsIgnoreCase("sinImagen.jpg")) { //Solo guarda la imagen si se ha seleccionado una
            try (FileInputStream fis = new FileInputStream(src);
                    FileOutputStream fos = new FileOutputStream(dst)) {
                byte[] buffer = new byte[1024];
                int leido;
                while ((leido = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, leido);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    /**
     * Calcula los puntos debidos a las horas acumuladas por sesiones de
     * entrenamiento
     *
     * @param cfg
     * @return
     */
    private float calcPtosPorSesiones(String baseDatos, String usr, String pass) {
        String sql = "SELECT SUM(datediff('hh', fh_inicio,fh_fin)) "
                + "FROM Sesion WHERE fh_inicio >= "
                + "(SELECT fecha1 FROM configuracion WHERE p_configuracion = 0) "
                + "AND fh_fin <= ?";
        float weight = 0.5F;
        float ptos = 0F;
        float n2 = 0F;
        try (Connection db = getConnection(baseDatos, usr, pass)) {
            PreparedStatement pst = db.prepareStatement(sql);
            Date current = new Date();
            Date lastDate = getLastDate(baseDatos, usr, pass);
            if (lastDate == null) {
                return 0;
            }
            long time = current.after(lastDate) ? lastDate.getTime() : current.getTime();
            pst.setTimestamp(1, new Timestamp(time));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ptos = rs.getFloat(1) * weight;
                ptos = ptos > 5 ? 5 : ptos;
            }
            sql = "SELECT DATEDIFF('dd',fecha1,?)/7 AS nWeek FROM Configuracion";
            pst = db.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(time));
            rs = pst.executeQuery();
            if (rs.next()) {
                n2 = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n2 == 0 ? 0 : ptos / n2 > 5 ? 5 : ptos / n2;
    }

    /**
     * Calcula los puntos debidos al número medio de itinerarios resuelto por
     * semana
     *
     * @return
     */
    private float calcPtosPorItinerario(String baseDatos, String usr, String pass) {
        float n1 = 0F;
        float n2 = 0F;
        try (Connection db = getConnection(baseDatos, usr, pass)) {
            Date current = new Date();
            Date lastDate = getLastDate(baseDatos, usr, pass);
            if (lastDate == null) {
                return 0;
            }
            long time = current.after(lastDate) ? lastDate.getTime() : current.getTime();
            String sql = "SELECT count(*) from FechaItinerario f, Configuracion c WHERE "
                    + "f.fecha BETWEEN c.fecha1 AND ?";
            PreparedStatement pst = db.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(time));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                n1 = rs.getInt(1);
            }
            sql = "SELECT DATEDIFF('dd',fecha1,?)/7 AS nWeek FROM Configuracion";
            pst = db.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(time));
            rs = pst.executeQuery();
            if (rs.next()) {
                n2 = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        n1 = n2 != 0 ? n1 / n2 : 0;
        n1 *= 0.25F;
        n1 = n1 > 5 ? 5 : n1;
        return n1;
    }
    /**
     * Devuelve la última fecha del intervalo de la configuración
     * @param baseDatos
     * @param usr
     * @param pass
     * @return
     * @throws SQLException 
     */
    private Date getLastDate(String baseDatos, String usr, String pass) throws SQLException {
        String sql = "SELECT fecha2 FROM configuracion WHERE p_configuracion = 0";
        Timestamp date;
        try (Connection db = getConnection(baseDatos, usr, pass)) {
            PreparedStatement pst = db.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                return null;
            }
            date = rs.getTimestamp(1);
        }
        return new Date(date.getTime());
    }

}
