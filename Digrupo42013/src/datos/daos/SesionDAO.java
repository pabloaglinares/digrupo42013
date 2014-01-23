
package datos.daos;

import datos.Comparador;
import datos.UtilesBD;
import datos.pojos.Sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este Singleton se encarga de hacer de intermediario entre la base de datos
 * y la aplicación realizando las tareas de insertar y recuperar objetos de tipo
 * Sesion
 * @author Andrés Traspuesto Lanza
 */
public enum SesionDAO {
    SESION_DAO;
    private UtilesBD utiles = UtilesBD.INSTANCE;
    
     /*
     ========================================================================
     ............................SELECTS.....................................
     ========================================================================
     */
    
    /**
     * Devuelve todas las sesiones almacenadas en una lista
     *
     * @return
     */
    public List<Sesion> getAllSesion() {
        List<Sesion> sesiones = new ArrayList<>();
        try (Statement st = utiles.getConnection().createStatement()) {
            String sql = "SELECT fh_inicio, fh_fin, a_tipo, p_sesion, descripcion FROM Sesion";
            ResultSet rs = st.executeQuery(sql);
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "SELECT fh_inicio,fh_fin, a_tipo, p_sesion, descripcion "
                + "FROM Sesion WHERE  fh_inicio "+cmp+" ?;";
        try (PreparedStatement st = utiles.getConnection().prepareStatement(sql)) {
            st.setTimestamp(1, new Timestamp(fecha.getTime()));
            ResultSet rs = st.executeQuery();
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "SELECT fh_inicio, fh_fin, a_tipo, p_sesion, descripcion "
                + "FROM Sesion WHERE  fh_inicio BETWEEN "
                + "? AND ?;";
        try (PreparedStatement st = utiles.getConnection().prepareStatement(sql)) {
            st.setTimestamp(1, new Timestamp(fecha1.getTime()));
            st.setTimestamp(2, new Timestamp(fecha2.getTime()));
            ResultSet rs = st.executeQuery();
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "SELECT fh_inicio, fh_fin, a_tipo, p_sesion, descripcion "
                + "FROM Sesion WHERE  a_tipo = ?;";
        try (PreparedStatement st = utiles.getConnection().prepareStatement(sql)) {
            st.setInt(1, tipo.ordinal());
            ResultSet rs = st.executeQuery();
            cargaListaSesiones(rs, sesiones);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sesiones;
    }
    
    /*
     ========================================================================
     ............................UPDATES.....................................
     ========================================================================
     */
    
    /**
     * Modifica la sesion en la base de datos
     *
     * @param sesion
     */
    public void updateSesion(Sesion sesion) {
        String sql = "UPDATE Sesion SET fh_inicio = ?, fh_fin = ?, a_tipo = ?,"
                + " descripcion = ? WHERE p_sesion = ?";
        executeUpdateOnSesion(sql, sesion);
    }
    
    /*
     ========================================================================
     ............................INSERTS.....................................
     ========================================================================
     */
    
    /**
     * Inserta la Sesion en la base de datos
     *
     * @param sesion
     */
    public void insertSesion(Sesion sesion) {
        String sql = "INSERT INTO Sesion (fh_inicio, fh_fin, a_tipo, descripcion)"
                + " VALUES(?,?,?,?)";
        executeUpdateOnSesion(sql, sesion);
    }
    
    
    /**
     * Se encarga de hacer insert o update, en función del sql en la tabla
     * Sesion
     *
     * @param sesion
     */
    private void executeUpdateOnSesion(String sql, Sesion sesion) {
        Connection connection = utiles.getConnection();
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setTimestamp(1, new Timestamp(sesion.getFecha_hora1().getTime()));
            pst.setTimestamp(2, new Timestamp(sesion.getFecha_hora1().getTime()));
            pst.setInt(3, sesion.getTipo().ordinal());
            pst.setString(4, sesion.getDescripcion());
            if (sesion.getpSesion() > 0) {
                pst.setInt(5, sesion.getpSesion());
            }
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        utiles.saveData();
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
            sesion.setFecha_hora1(rs.getTimestamp("fh_inicio"));
            sesion.setFecha_hora2(rs.getTimestamp("fh_fin"));
            sesiones.add(sesion);
        }
    }

}
