package datos.daos;

import datos.UtilesBD;
import datos.pojos.Configuracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton que se encarga de hacer de intermediario entre la base de datos y
 * la aplicación para trabajar con objetos configuración.
 *
 * @author Andrés Traspuesto Lanza
 */
public enum ConfiguracionDAO {

    CONFIGURACION_DAO;
    private UtilesBD utiles = UtilesBD.INSTANCE;

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
        try (Statement st = utiles.getConnection().createStatement()) {
            String sql = "SELECT nombre, apellidos, fecha1, fecha2 FROM Configuracion";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                usr = new Configuracion();
                do {
                    usr.setNombre(rs.getString("nombre"));
                    usr.setApellidos(rs.getString("apellidos"));
                    usr.setFecha1Intervalo(rs.getTimestamp("fecha1"));
                    usr.setFecha2Intervalo(rs.getTimestamp("fecha2"));

                } while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
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
                + "fecha1 = ?, fecha2 = ? WHERE p_configuracion = 0";
        executeUpdateOnConfiguracion(sql, usr);
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
        String sql = "SELECT p_configuracion FROM Configuracion";
        Connection con = utiles.getConnection();
        boolean existe = false;
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet res = pst.executeQuery();
            existe = res.next();
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguracionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (existe) {
            updateConfiguracion(usr);
        } else {
            sql = "INSERT INTO Configuracion (nombre, apellidos, fecha1, fecha2) "
                    + "VALUES(?, ?, ?, ?)";
            executeUpdateOnConfiguracion(sql, usr);
        }
        utiles.saveData();
    }
    /*
     ========================================================================
     ....................MÉTODOS PRIVADOS AUXILIARES.........................
     ========================================================================
     */

    /**
     * Se encarga de hacer insert o update, en función del sql en la tabla
     * Configuracion
     *
     * @param sql
     * @param usr
     */
    private void executeUpdateOnConfiguracion(String sql, Configuracion usr) {
        Connection connection = utiles.getConnection();
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, usr.getNombre());
            pst.setString(2, usr.getApellidos());
            pst.setTimestamp(3, new Timestamp(usr.getFecha1Intervalo().getTime()));
            pst.setTimestamp(4, new Timestamp(usr.getFecha2Intervalo().getTime()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        utiles.saveData();
    }

}
