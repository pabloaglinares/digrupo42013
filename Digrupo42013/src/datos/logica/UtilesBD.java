package datos.logica;

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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton que contiene los métodos comunes para trabajar con la base de datos
 *
 * @author Andrés Traspuesto Lanza
 */
public enum UtilesBD {

    INSTANCE;
    private Connection connection;

    /**
     * Constructor del singleton encargado de la lógica de interacción con la
     * base de datos
     */
    UtilesBD() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:entrenoBD");
            createTables();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una conexión a la base de datos
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * La finalidad de este método es crear las tablas de la base de datos.
     */
    public void createTables() {
        try (Statement st = connection.createStatement()) {
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
            st.executeUpdate(sql + "'Físico')");
            st.executeUpdate(sql + "'Rocódromo')");
            st.executeUpdate(sql + "'Roca')");

        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creo el directorio para almacenar las imágenes si no existe
        File path = new File("imagenes");
        if (!path.exists()) {
            path.mkdir();
        }
    }

    /**
     * Método que calcula el rendimiento del usuario
     *
     * @return
     */
    public float calculaRendimiento() {
        return calcPtosPorSesiones() + calcPtosPorItinerario();
    }

    /**
     * Devuelve un objeto Date a partir del tiempo en segundos desde 1/1/1970
     * almacenado en SQLite3
     *
     * @param segundos
     * @return
     */
    public Date getDateDeSQLite3(long segundos) {
        return new Date(segundos * 1000);
    }

    /**
     * Devuelve el tiempo en segundos desde 1/1/1970 a partir de un Date
     *
     * @param date
     * @return
     */
    public long getSegundosParaSQLite3(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * Encuentra un nombre que no exista para guardar la imagen
     *
     * @param pathImagen
     * @return
     */
    public File getFileImagen(File pathImagen) {
        File aux = new File("imagenes" + File.separatorChar + "_0" + pathImagen.getName());
        int i = 1;
        while (aux.exists()) {
            aux = new File("imagenes" + File.separatorChar + "_" + (i++) + pathImagen.getName());
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

    /**
     * Calcula los puntos debidos a las horas acumuladas por sesiones de
     * entrenamiento
     *
     * @param cfg
     * @return
     */
    private float calcPtosPorSesiones() {
        String sql = "SELECT SUM(fecha_fin-fecha_inicio)/3600 "
                + "FROM Sesion WHERE DATE(fecha_inicio,'unixepoch') BETWEEN "
                + "DATE((SELECT fecha1 FROM configuracion WHERE p_configuracion = 1),'unixepoch') "
                + "AND DATE((SELECT fecha2 FROM configuracion WHERE p_configuracion = 1),'unixepoch')";
        float weight = 0.5F;
        return getPoints(sql, weight);
    }

    /**
     * Calcula los puntos debidos al número medio de itinerarios resuelto por
     * semana
     *
     * @return
     */
    private float calcPtosPorItinerario() {
        String sql = "SELECT AVG(nIte) FROM (SELECT COUNT(*) AS nIte FROM FechaItinerario "
                + "WHERE DATE(fecha,'unixepoch') BETWEEN "
                + "DATE((SELECT fecha1 FROM configuracion WHERE p_configuracion = 1),'unixepoch') "
                + "AND DATE((SELECT fecha2 FROM configuracion WHERE p_configuracion = 1),'unixepoch')"
                + " GROUP BY STRFTIME('%W', fecha))";
        float weight = 0.25F;
        return getPoints(sql, weight);
    }

    /**
     * Calcula los puntos correspondientes al valor devuelto por la consulta
     *
     * @param sql
     * @param weight
     * @return
     */
    private float getPoints(String sql, float weight) {
        float ptos = 0F;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            rs.next();
            ptos = rs.getInt(1) * weight;
            ptos = ptos > 5 ? 5 : ptos;
        } catch (SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ptos;
    }

}
