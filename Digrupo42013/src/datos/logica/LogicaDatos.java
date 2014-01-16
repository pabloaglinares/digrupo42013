/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.logica;

import datos.pojos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
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
            sql = "CREATE TABLE IF NOT EXISTS Usuario( "
                    + "p_usuario INTEGER PRIMARY KEY, "
                    + "nombre TEXT COLLATE NOCASE, "
                    + "apellidos TEXT COLLATE NOCASE, "
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
    public Usuario getUsuario() {
        Usuario usr = null;
        try(Statement st =con.createStatement()) {
            String sql = "SELECT nombre, apellidos FROM Usuario";
            ResultSet rs = st.executeQuery(sql);
            usr = new Usuario();
            while(rs.next()) {
                usr.setNombre(rs.getNString("nombre"));
                usr.setApellidos("apellidos");
                usr.setRendimiento(calculaRendimiento());
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }
    /**
     * Actualiza los datos del usuario
     * @param nuevosValores contiene los campos a modificar, donde la clave es el
     *  nombre del campo y el valor en nuevo contenido que se le quiere asignar
     */
    public void updateUsuario(Map<String,String> nuevosValores) {
        try(Statement st =con.createStatement()) {
            StringBuilder sql = new StringBuilder("UPDATE Usuario SET ");
            for (Map.Entry<String,String> parametro: nuevosValores.entrySet()) {
                sql.append(parametro.getKey()).append("='").append(parametro.getValue()).append("',");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append("WHERE p_usuario = 0");
            st.executeUpdate(sql.toString());
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
}
