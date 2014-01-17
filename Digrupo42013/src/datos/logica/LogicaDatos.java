/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.logica;

import datos.pojos.Configuracion;
import datos.pojos.Sesion;
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
                    + "nombre TEXT, "
                    + "dificultad TEXT, "
                    + "imagen TEXT "
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

        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
     * Devuelve una lista de las sesiones de entrenamiento de una fecha determinada
     * @param fecha
     * @return 
     */
    public List<Sesion> getSesionByFecha(Date fecha) {
        List<Sesion> sesiones = new ArrayList<>();
        String sql = "SELECT fecha_inicio, fecha_fin, a_tipo, p_sesion, descripcion "
                + "FROM Sesion WHERE  date(fecha_inicio,'unixepoch') = date(?,'unixepoch');";
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
     * Devuelve una lista de las sesiones de entrenamiento de un periodo de tiempo
     * determinado por las fecha parámetro
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
     * @param sesion 
     */
    public void insertSesion(Sesion sesion) {
        String sql = "INSERT INTO Sesion (fecha_inicio, fecha_fin, a_tipo, descripcion)"
                + " VALUES(?,?,?,?)";
        executeUpdateOnSesion(sql, sesion);
    }
    /**
     * Modifica la sesion en la base de datos
     * @param sesion 
     */
    public void updateSesion(Sesion sesion) {
        String sql = "UPDATE Sesion SET fecha_inicio = ?, fecha_fin = ?, a_tipo = ?,"
                + " descripcion = ? WHERE p_sesion = ?";
        executeUpdateOnSesion(sql, sesion);
    }
    /**
     * Se encarga de hacer insert o update, en función del sql en la tabla
     * Configuracion
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
            if (sesion.getpSesion() > 0)pst.setInt(5, sesion.getpSesion());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método que se encarga de cargar una lista con obejetos Sesion creados a 
     * partir de un ResultSet
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
     ******************************************
     * **********SIN IMPLEMENTAR**************
     * ************************************* 
     * 
     * Método que calcula el rendimiento del usuario
     *
     * @return
     */
    public int calculaRendimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
