package datos.daos;

import datos.Comparador;
import datos.ModItinerario;
import datos.UtilesBD;
import datos.pojos.Itinerario;
import java.io.File;
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
 * Singleton que se encarga de manejar toda la interacción con la base de datos
 * referente a objetos Itinerario
 *
 * @author Andrés Traspuesto Lanza
 */
public enum ItinerarioDAO {

    ITINERARIO_DAO;
    private UtilesBD utiles = UtilesBD.INSTANCE;

    /*
     ========================================================================
     ............................SELECTS.....................................
     ========================================================================
     */
    /**
     * Devuelve todos los itinerarios contenidos en la BD en un List
     *
     * @return
     */
    public List<Itinerario> getAllItinerario() {
        String sql = "SELECT p_itinerario, nombre, localizacion, dificultad, imagen FROM Itinerario";
        List<Itinerario> itinerarios = new ArrayList<>();
        try (Statement st = utiles.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;

    }

    /**
     * Devuelve todos los resultados comparando las fechas de resolución, según
     * el operador lógico almacenado en el comparador, con la fecha pasada como
     * parámetro. determinada fecha.
     *
     * @param fecha
     * @param cmp
     * @return
     */
    public List<Itinerario> getItinerariosByFecha(Date fecha, Comparador cmp) {
        List<Itinerario> itinerarios = new ArrayList<>();
        String sql = "SELECT DISTINCT p_itinerario, nombre, localizacion, dificultad, imagen "
                + "FROM Itinerario WHERE p_itinerario IN "
                + "(SELECT a_itinerario FROM FechaItinerario "
                + "WHERE fecha" + cmp + " ?)";
        try (PreparedStatement st = utiles.getConnection().prepareStatement(sql)) {
            st.setTimestamp(1,new Timestamp(fecha.getTime()));
            ResultSet rs = st.executeQuery();
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;
    }

    public List<Itinerario> getItinerariosByFechaRange(Date fecha1, Date fecha2) {
        List<Itinerario> itinerarios = new ArrayList<>();
        String sql = "SELECT DISTINCT p_itinerario, nombre, localizacion, dificultad, imagen "
                + "FROM Itinerario WHERE p_itinerario IN "
                + "(SELECT a_itinerario FROM FechaItinerario "
                + "WHERE fecha BETWEEN ? AND ?)";
        try (PreparedStatement st = utiles.getConnection().prepareStatement(sql)) {
            st.setTimestamp(1,new Timestamp(fecha1.getTime()));
            st.setTimestamp(2,new Timestamp(fecha2.getTime()));
            ResultSet rs = st.executeQuery();
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;
    }

    public List<Itinerario> getItinerariosByDificultad(String dificultad) {
        List<Itinerario> itinerarios = new ArrayList<>();
        String sql = "SELECT DISTINCT p_itinerario, nombre, localizacion, dificultad, imagen "
                + "FROM Itinerario WHERE dificultad = ?";
        try (PreparedStatement st = utiles.getConnection().prepareStatement(sql)) {
            st.setString(1, dificultad);
            ResultSet rs = st.executeQuery();
            cargaItinerarios(rs, itinerarios);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itinerarios;
    }

    /*
     ========================================================================
     ............................UPDATES.....................................
     ========================================================================
     */
    /**
     * Modifica los datos del itinerario para que concuerden con los contenidos
     * por el Itinerario pasado como parámetro, el segundo parámetro especifica
     * que es lo que se quiere actualizar del itinerario
     *
     * @param itinerario
     * @param tipo
     * @param img contiene el archivo de imagen que quiere asociarse al
     * itinerario, si no se quiere modificar la imagen debe ser null
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
        utiles.saveData();

    }

    /*
     ========================================================================
     ............................INSERTS.....................................
     ========================================================================
     */
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
            imgFile = utiles.getFileImagen(itinerario.getPathImagen());
        } else {
            imgFile = new File("imagenes/sinImagen.jpg");
        }
        try (PreparedStatement pst = utiles.getConnection().prepareStatement(sql)) {
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
                utiles.guardaArchivoImagen(itinerario.getPathImagen(), imgFile);
            }
            itinerario.setPathImagen(imgFile);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        utiles.saveData();
    }

    /**
     * Inserta una fecha de resolución en el itinerario
     *
     * @param itinerario
     * @param fecha
     */
    public void insertaFechaItinerario(Itinerario itinerario, Date fecha) {
        String sql = "INSERT INTO FechaItinerario (a_itinerario, fecha) "
                + "VALUES (?,?)";
        try (PreparedStatement pst = utiles.getConnection().prepareStatement(sql)) {
            pst.setInt(1, itinerario.getpItinerario());
            pst.setTimestamp(2,new Timestamp(fecha.getTime()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     ========================================================================
     ....................MÉTODOS PRIVADOS AUXILIARES.........................
     ========================================================================
     */
    /**
     * Modifica las fechas en las que se ha resuelto el itinerario, NO DEBE
     * USARSE PARA AÑADIR UNA FECHA DE RESOLUCIÓN, para eso se usa
     * insertaFechaItinerario
     *
     * @param itinerario
     */
    private void updateFechasItinerario(Itinerario itinerario) {
        String sql = "DELETE FROM Itinerario WHERE a_itinerario = ?";
        try (PreparedStatement pst = utiles.getConnection().prepareStatement(sql)) {
            pst.setInt(1, itinerario.getpItinerario());
            pst.executeUpdate();
            insertAllFechasItinerario(itinerario);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserta todas las fechas de resolución del itinerario en la tabla
     * FechaItinerario
     *
     * @param itinerario
     */
    private void insertAllFechasItinerario(Itinerario itinerario) {
        for (Date fecha : itinerario.getFechasResolucion()) {
            insertaFechaItinerario(itinerario, fecha);
        }
        utiles.saveData();
    }

    /**
     * Devuelve el p_itinerario del último itinerario registrado
     *
     * @return
     */
    private int getLastItinerario() {
        String sql = "SELECT max(p_itinerario) as m from Itinerario";
        int max = Integer.MIN_VALUE;
        try (Statement st = utiles.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                max = rs.getInt("m");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    /**
     * Actualiza la imagen asociada al itinerario
     *
     * @param itinerario
     * @param newImg
     */
    private void updateImagenItinerario(Itinerario itinerario, File newImg) {
        if (!itinerario.getPathImagen().getName().equals("sinImagen.jpg")) {
            itinerario.getPathImagen().delete();
        }
        File img = utiles.getFileImagen(newImg);
        String sql = "UPDATE Itinerario SET imagen = ? WHERE p_itinerario = ?";
        try (PreparedStatement pst = utiles.getConnection().prepareStatement(sql)) {
            pst.setString(1, img.getName());
            pst.setInt(2, itinerario.getpItinerario());
            pst.executeUpdate();
            utiles.guardaArchivoImagen(newImg, img);
            itinerario.setPathImagen(img);
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        utiles.saveData();

    }

    /**
     * Carga en el itinerario todas las fechas en las que ha sido resuelto
     *
     * @param itinerario
     */
    private void cargaFechasItinerario(Itinerario itinerario) {
        String sql = "SELECT fecha FROM FechaItinerario WHERE a_itinerario = ?";
        try (PreparedStatement pst = utiles.getConnection().prepareStatement(sql)) {
            pst.setInt(1, itinerario.getpItinerario());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                itinerario.addFechaResolucion(rs.getTimestamp("fecha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Se encarga de cargar los Itinerarios contenidos en el ResultSet dentro de
     * la lista de itinerarios
     *
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
            itinerario.setPathImagen(new File("imagenes" + File.separatorChar + rs.getString("imagen")));
            cargaFechasItinerario(itinerario);
            itinerarios.add(itinerario);
        }
    }

    /**
     * Actualiza el nombre y la dificultad de un itinerario dado
     *
     * @param itinerario
     */
    private void updateNombreYDificultad(Itinerario itinerario) {
        String sql = "UPDATE Itinerario SET nombre = ?, dificultad = ?, localizacion = ? "
                + "WHERE p_itinerario = ?";
        try (PreparedStatement pst = utiles.getConnection().prepareStatement(sql)) {
            pst.setString(1, itinerario.getNombre());
            pst.setString(2, itinerario.getDifucultad());
            pst.setString(3, itinerario.getLocalizacion());
            pst.setInt(4, itinerario.getpItinerario());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        utiles.saveData();
    }
}
