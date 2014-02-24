/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private final String BASE_DATOS = "jdbc:hsqldb:file:test" + File.separatorChar + "db" + File.separatorChar + "escalador;";
    private final String EXIST = "ifexists=true;";
    private final String SHUTDOWN = "shutdown=true";
    private final String USUARIO = "sa";
    private final String PASS="";
    UtilesBD utilBD = UtilesBD.INSTANCE;
    public UtilesBDTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        borraBD();
        utilBD.createTables(BASE_DATOS+SHUTDOWN, USUARIO, PASS,"test/db/imagenes");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
       // borraBD();
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
     * Inserta la configuración
     *
     * @param db
     * @param t1
     * @param t2
     * @throws SQLException
     * @throws ParseException
     */
    private void insertaConfiguracion(Timestamp t1, Timestamp t2) throws SQLException, ParseException {
        try (Connection db = utilBD.getConnection(BASE_DATOS + EXIST + SHUTDOWN, USUARIO, PASS)) {
            PreparedStatement pst = db.prepareStatement("INSERT INTO Configuracion (nombre, apellidos, fecha1, fecha2) values (' ',' ',?,?)");
            pst.setTimestamp(1, t1);
            pst.setTimestamp(2, t2);
            pst.execute();
        }
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
    private void insertSesion(Timestamp t1, Timestamp t2) throws SQLException {
        try (Connection db = utilBD.getConnection(BASE_DATOS + EXIST + SHUTDOWN, USUARIO, PASS)) {
            String sql = "INSERT INTO Sesion (fh_inicio, fh_fin, a_tipo, descripcion)"
                    + " VALUES(?,?,0,'nada')";
            PreparedStatement pst = db.prepareStatement(sql);
            pst.setTimestamp(1, t1);
            pst.setTimestamp(2, t2);
            pst.execute();
        }
    }

    /**
     * Inserta las fechas correspondientes a la resolución de itinerarios
     * contenidas en la lista
     *
     * @param db
     * @param fechas
     */
    private void insertaFechasItinerario(List<Timestamp> fechas) throws SQLException {
        String sql1 = "INSERT INTO Itinerario (nombre, localizacion, tipo, dificultad, imagen)"
                + " VALUES('asdfa','asdfva',1,'sa','asd')";
        String sql2 = "INSERT INTO FechaItinerario (a_itinerario, fecha) "
                + "VALUES (?,?)";
        try(Connection db = utilBD.getConnection(BASE_DATOS + EXIST + SHUTDOWN, USUARIO, PASS);
                PreparedStatement pst1 = db.prepareStatement(sql1)){
            pst1.executeUpdate();
        }
        for (Timestamp fecha : fechas) {
            try (Connection db = utilBD.getConnection(BASE_DATOS + EXIST + SHUTDOWN, USUARIO, PASS);
                    PreparedStatement pst2 = db.prepareStatement(sql2)) {
                pst2.setInt(1, 0);
                pst2.setTimestamp(2, fecha);
                pst2.executeUpdate();
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
            insertaConfiguracion(new Timestamp(sdf.parse("1/02/2014 13:00").getTime()), new Timestamp(sdf.parse("28/02/2014 14:00").getTime()));

            insertSesion(new Timestamp(sdf.parse("11/02/2014 13:00").getTime()), new Timestamp(sdf.parse("11/02/2014 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("12/02/2014 13:00").getTime()), new Timestamp(sdf.parse("12/02/2014 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("13/02/2014 13:00").getTime()), new Timestamp(sdf.parse("13/02/2014 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("14/02/2014 13:00").getTime()), new Timestamp(sdf.parse("14/02/2014 14:00").getTime()));
            float expResult = 4*0.5F/3;
            float result = UtilesBD.INSTANCE.calculaRendimiento(BASE_DATOS+EXIST+SHUTDOWN,USUARIO,PASS);
            assertEquals(expResult, result, 0.005);
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
            insertSesion(new Timestamp(sdf.parse("11/02/2013 13:00").getTime()), new Timestamp(sdf.parse("11/02/2013 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("12/02/2013 13:00").getTime()), new Timestamp(sdf.parse("12/02/2013 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("13/02/2013 13:00").getTime()), new Timestamp(sdf.parse("13/02/2013 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("14/02/2013 13:00").getTime()), new Timestamp(sdf.parse("14/02/2013 14:00").getTime()));
            insertaConfiguracion(new Timestamp(sdf.parse("1/02/2014 13:00").getTime()), new Timestamp(sdf.parse("28/02/2014 14:00").getTime()));
            float expResult = 0F;
            float result = UtilesBD.INSTANCE.calculaRendimiento(BASE_DATOS+EXIST+SHUTDOWN,USUARIO,PASS);
            System.out.println(expResult + "=" + result+"?");
            assertEquals(expResult, result, 0.005);
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
        float result = UtilesBD.INSTANCE.calculaRendimiento(BASE_DATOS+EXIST+SHUTDOWN,USUARIO,PASS);
        System.out.println(expResult+"="+result+"?");
        assertEquals(expResult, result, 0.005);
    }
    /**
     * Compruebo que cuadra el cálculo cuando hay 5 resoluciones de itinerario en 5 semanas
     */
    public void testCalculaRendimiento5FechasEnIntervalo5Semanas() {
        try {
            System.out.println("Calcula rendimiento solo con itinerarios en 5 fechas en 5 semanas");
            insertaConfiguracion(new Timestamp(sdf.parse("24/01/2013 13:00").getTime()), new Timestamp(sdf.parse("28/02/2013 14:00").getTime()));
            List<Timestamp> fechas = new ArrayList<>();
            fechas.add(new Timestamp(sdf.parse("11/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("12/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("13/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("14/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("15/02/2013 13:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("11/02/2013 13:00").getTime()), new Timestamp(sdf.parse("11/02/2013 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("12/02/2013 13:00").getTime()), new Timestamp(sdf.parse("12/02/2013 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("13/02/2013 13:00").getTime()), new Timestamp(sdf.parse("13/02/2013 14:00").getTime()));
            insertSesion(new Timestamp(sdf.parse("14/02/2013 13:00").getTime()), new Timestamp(sdf.parse("14/02/2013 14:00").getTime()));
            insertaFechasItinerario(fechas);
            float expResult = 0.25f + 4*0.5F/5;
            float result = UtilesBD.INSTANCE.calculaRendimiento(BASE_DATOS,USUARIO,PASS);
            System.out.println(expResult+"="+result+"?");
            assertEquals(expResult, result, 0.005);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UtilesBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Compruebo que si el intervalo configurado no tiene semanas no devuelve una
     * indeterminación
     */
    public void testCalculaRendimiento5Itinerarios0semanas() {
        try {
            System.out.println("Calcula rendimiento solo con itinerarios en 5 fechas en 0 semanas");
            insertaConfiguracion(new Timestamp(sdf.parse("24/01/2013 13:00").getTime()), new Timestamp(sdf.parse("24/01/2013 13:00").getTime()));
            List<Timestamp> fechas = new ArrayList<>();
            fechas.add(new Timestamp(sdf.parse("11/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("12/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("13/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("14/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("15/02/2013 13:00").getTime()));
            insertaFechasItinerario(fechas);
            float expResult = 0f;
            float result = UtilesBD.INSTANCE.calculaRendimiento(BASE_DATOS,USUARIO,PASS);
            System.out.println(expResult+"="+result+"?");
            assertEquals(expResult, result, 0.005);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UtilesBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Compruebo que si las fechas de resolución de itinerarios están fuera de
     * rango no las cuenta
     */
    public void testCalculaRendimiento5ItinerariosFueraDeFechas() {
        try {
            System.out.println("Calcula rendimiento solo con itinerarios en 5 fechas fuera de rango");
            insertaConfiguracion(new Timestamp(sdf.parse("24/03/2013 13:00").getTime()), new Timestamp(sdf.parse("24/06/2013 13:00").getTime()));
            List<Timestamp> fechas = new ArrayList<>();
            fechas.add(new Timestamp(sdf.parse("11/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("12/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("13/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("14/02/2013 13:00").getTime()));
            fechas.add(new Timestamp(sdf.parse("15/02/2013 13:00").getTime()));
            insertaFechasItinerario(fechas);
            float expResult = 0f;
            float result = UtilesBD.INSTANCE.calculaRendimiento(BASE_DATOS,USUARIO,PASS);
            System.out.println(expResult+"="+result+"?");
            assertEquals(expResult, result, 0.005);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UtilesBDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
