/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.logica;

import datos.pojos.Configuracion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton que contiene los métodos para los procesos de interacción con
 * la base de datos
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
        try (Statement st =con.createStatement()){
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
     * @return 
     */
    public Configuracion getUsuario() {
        Configuracion usr = null;
        try(Statement st =con.createStatement()) {
            String sql = "SELECT nombre, apellidos, fecha1, fecha2 FROM Configuracion";
            ResultSet rs = st.executeQuery(sql);
            usr = new Configuracion();
            while(rs.next()) {
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
     * Actualiza los datos del usuario
     * @param usr
     */
    public void updateUsuario(Configuracion usr) {
        String sql = "UPDATE Usuario SET nombre = ?, apellidos = ?, fecha1 = ?, fecha2 = ? WHERE p_usuario = 0";
        try(PreparedStatement pst =con.prepareStatement(sql)) {
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
     * Inserta el usuario en la base de datos
     * @param usr 
     */
    public void insertUsuario(Configuracion usr) {
        String sql = "INSERT INTO Usuario (nombre, apellidos, fecha1, fecha2) VALUES(?,?,?,?)";
        try(PreparedStatement pst =con.prepareStatement(sql)) {
            pst.setString(1, usr.getNombre());
            pst.setString(2, usr.getApellidos());
            pst.setLong(3, getSegundosParaSQLite3(usr.getFecha1Intervalo()));
            pst.setLong(4, getSegundosParaSQLite3(usr.getFecha2Intervalo()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    /**                                                  *****************************************   
     * Método que calcula el rendimiento del usuario      **********SIN IMPLEMENTAR**************
     * @return                                             *************************************             
     */
    public int calculaRendimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Devuelve un objeto Date a partir del tiempo en segundos desde 1/1/1970
     * almacenado en SQLite3
     * @param segundos
     * @return 
     */
    private Date getDateDeSQLite3(long segundos) {
        return new Date(segundos*1000);
    }
    /**
     * Devuelve el tiempo en segundos desde 1/1/1970 a partir de un Date
     * @param date
     * @return 
     */
    private long getSegundosParaSQLite3(Date date) {
        return date.getTime()/1000;
    }
}
