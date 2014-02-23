/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class UtilesBDTest extends TestCase {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public UtilesBDTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        borraBD();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        borraBD();
    }

    private void borraBD() {
        File script = new File("test" + File.separatorChar + "db");
        if (script.exists()) {
            for (File f : script.listFiles()) {
                f.delete();
            }
        }
    }

    /**
     * Devuelve la conexión a la BD, que si no existe es creada
     *
     * @return
     */
    private Connection getConnection() {
        Connection db = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            if (new File("test" + File.separatorChar + "db" + File.separatorChar + "escalador.script").exists()) {
                //Si la base de datos ya existe conecta
                db = DriverManager.getConnection("jdbc:hsqldb:file:test" + File.separatorChar + "db" + File.separatorChar + "escalador;ifexists=true;shutdown=true", "sa", "");

            } else {
                //Si la base de datos no existe la crea
                db = DriverManager.getConnection("jdbc:hsqldb:file:" + "test" + File.separatorChar + "db" + File.separatorChar + "escalador;shutdown=true", "sa", "");
                UtilesBD.INSTANCE.createTables(db, "test" + File.separatorChar + "db" + File.separatorChar + "imagenes");

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    /**
     * Inserta la configuración
     *
     * @param db
     * @param t1
     * @param t2
     * @throws SQLException
     * @throws ParseException
     */
    private void insertaConfiguracion(Connection db, Timestamp t1, Timestamp t2) throws SQLException, ParseException {
        PreparedStatement pst = db.prepareStatement("INSERT INTO Configuracion (nombre, apellidos, fecha1, fecha2) values (' ',' ',?,?)");
        pst.setTimestamp(1, t1);
        pst.setTimestamp(2, t2);
        pst.execute();
    }

    /**
     * Inserta una sesión
     *
     * @param db
     * @param sql
     * @param t1
     * @param t2
     * @throws SQLException
     */
    private void insertSesion(Connection db, String sql, Timestamp t1, Timestamp t2) throws SQLException {
        PreparedStatement pst = db.prepareStatement(sql);
        pst.setTimestamp(1, t1);
        pst.setTimestamp(2, t2);
        pst.execute();
    }

    /**
     * Inserta las fechas correspondientes a la resolución de itinerarios
     * contenidas en la lista
     *
     * @param db
     * @param fechas
     */
    private void insertaFechasItinerario(Connection db, List<Timestamp> fechas) {
        String sql = "INSERT INTO FechaItinerario (a_itinerario, fecha) "
                + "VALUES (0,?)";
        for (Timestamp fecha : fechas) {
            try (PreparedStatement pst = db.prepareStatement(sql)) {
                pst.setTimestamp(1, new Timestamp(fecha.getTime()));
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UtilesBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test rendimiento de 4h de sesiones dentro de un intervalo de 3 semanas
     */
    public void testCalculaRendimientoSoloSesiones() {
        try {
            System.out.println("calcula rendimiento de 4h de sesiones dentro de un intervalo de 3 semanas");
            String sql = "INSERT INTO Sesion (fh_inicio, fh_fin, a_tipo, descripcion)"
                    + " VALUES(?,?,0,'nada')";
            Connection db = getConnection();
            insertSesion(db, sql, new Timestamp(sdf.parse("11/02/2014 13:00").getTime()), new Timestamp(sdf.parse("11/02/2014 14:00").getTime()));
            insertSesion(db, sql, new Timestamp(sdf.parse("12/02/2014 13:00").getTime()), new Timestamp(sdf.parse("12/02/2014 14:00").getTime()));
            insertSesion(db, sql, new Timestamp(sdf.parse("13/02/2014 13:00").getTime()), new Timestamp(sdf.parse("13/02/2014 14:00").getTime()));
            insertSesion(db, sql, new Timestamp(sdf.parse("14/02/2014 13:00").getTime()), new Timestamp(sdf.parse("14/02/2014 14:00").getTime()));
            insertaConfiguracion(db, new Timestamp(sdf.parse("1/02/2014 13:00").getTime()), new Timestamp(sdf.parse("28/02/2014 14:00").getTime()));
            float expResult = 2.0F;
            float result = UtilesBD.INSTANCE.calculaRendimiento(getConnection());
            assertEquals(expResult, result, 0.00001);
            db.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UtilesBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test rendimiento de 0h de sesiones dentro de un intervalo de 3 semanas
     */
    public void testCalculaRendimientoCeroSesiones() {
        try {
            System.out.println("calcula rendimiento de 0h de sesiones dentro de un intervalo de 3 semanas");
            String sql = "INSERT INTO Sesion (fh_inicio, fh_fin, a_tipo, descripcion)"
                    + " VALUES(?,?,0,'nada')";
            Connection db = getConnection();
            insertSesion(db, sql, new Timestamp(sdf.parse("11/02/2013 13:00").getTime()), new Timestamp(sdf.parse("11/02/2013 14:00").getTime()));
            insertSesion(db, sql, new Timestamp(sdf.parse("12/02/2013 13:00").getTime()), new Timestamp(sdf.parse("12/02/2013 14:00").getTime()));
            insertSesion(db, sql, new Timestamp(sdf.parse("13/02/2013 13:00").getTime()), new Timestamp(sdf.parse("13/02/2013 14:00").getTime()));
            insertSesion(db, sql, new Timestamp(sdf.parse("14/02/2013 13:00").getTime()), new Timestamp(sdf.parse("14/02/2013 14:00").getTime()));
            insertaConfiguracion(db, new Timestamp(sdf.parse("1/02/2014 13:00").getTime()), new Timestamp(sdf.parse("28/02/2014 14:00").getTime()));
            float expResult = 0F;
            float result = UtilesBD.INSTANCE.calculaRendimiento(getConnection());
            assertEquals(expResult, result, 0.00001);
            db.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UtilesBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Compruebo que si no hay datos el rendimiento es cero
     */
    public void testCalculaRendimientoTodoCero() {
        System.out.println("Comprueba que si no hay nada sale 0");
        float expResult = 0F;
        float result = UtilesBD.INSTANCE.calculaRendimiento(getConnection());
        assertEquals(expResult, result, 0.00001);
    }
    
    public void testCalculaRendimiento5FechasEnIntervalo5Semanas() {
        try {
            Connection db = getConnection();
            insertaConfiguracion(db, new Timestamp(sdf.parse("24/01/2013 13:00").getTime()), new Timestamp(sdf.parse("28/02/2013 14:00").getTime()));
            List<Timestamp>fechas = new ArrayList<>();
            fechas.add(new Timestamp(sdf.parse("11/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("12/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("13/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("14/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("15/02/2013 13:00").getTime()));
            float expResult = 0.25f;
            float result = UtilesBD.INSTANCE.calculaRendimiento(getConnection());
            assertEquals(expResult, result, 0.00001);
            db.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UtilesBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
