package enlace_datos_gui;

import datos.UtilesBD;
import gui.Informes;
import gui.Main;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Benjamín Pérez
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeInformes {

    INFORMES;
    private Informes informes;
    private Main main;
    private UtilesBD bd = UtilesBD.INSTANCE;
    private AyudaUtil ayuda = AyudaUtil.AYUDA;
    /**
     * Abre la ventana de creación de informes
     *
     * @param main
     */
    public void openInformes(Main main) {
        this.main = main;
        informes = new Informes();
        ayuda.asociateF1ToJFrame(informes, AyudaUtil.PantallaAyuda.INFORMES);
        main.getDpEscritorio().add(informes);
        informes.show();
    }
    /**
     * Se encarga de crear el informe que proceda en función de la pestaña activa
     */
    public void onCrear() {
        informes.getBtCrear().setEnabled(false);
        informes.getBtVolver().setEnabled(false);
        switch(informes.getpSesionesFecha().getSelectedIndex()) {
            case 0:
                crearInforme1();
                break;
            case 1:
                crearInforme2();
                break;
            case 2:
                crearInforme3();
                break;
            case 3:
                crearInforme4();
                break;
            case 4:
                crearInforme5();
                break;
        }
        informes.getBtCrear().setEnabled(true);
        informes.getBtVolver().setEnabled(true);
    }
    /**
     * Se encarga de crear el segundo informe
     */
    private void crearInforme2() {
        Timestamp ini = new Timestamp(informes.getDateTextField3().getDate().getTime());
        Timestamp fin = new Timestamp(informes.getDateTextField4().getDate().getTime());
        String jasper = "./informes/consulta2.jasper";
        String pdf ="./informesgenerados/sesiones.pdf";
        creaInformeRangoFechas(ini, fin, jasper, pdf);

    }

    /**
     * Se encarga de crear el primer informe
     */
    private void crearInforme1() {
        Timestamp ini = new Timestamp(informes.getDateTextField1().getDate().getTime());
        Timestamp fin = new Timestamp(informes.getDateTextField2().getDate().getTime());
        String jasper = "./informes/consulta1.jasper";
        String pdf =  "./informesgenerados/itinerarios.pdf";
        creaInformeRangoFechas(ini, fin, jasper, pdf);
    }
    /**
     * Se encarga de crear el quinto informe
     */
    private void crearInforme5() {
        String jasper = "./informes/consulta4.jasper";
        Timestamp ini = new Timestamp(informes.getDateTextField5().getDate().getTime());
        Timestamp fin = new Timestamp(informes.getDateTextField6().getDate().getTime());
        String destino = "./informesgenerados/sesionestipo.pdf";
        creaInformeRangoFechas(ini, fin, jasper, destino);
    }
    /**
     * Crea un informe que necesita dos fechas como parámetros
     * @param ini
     * @param fin
     * @param jasper
     * @param destino
     * @throws HeadlessException 
     */
    private void creaInformeRangoFechas(Timestamp ini, Timestamp fin, String jasper, String destino) throws HeadlessException {
        Map parametros = new HashMap();
        parametros.put("fechainicio", ini);
        parametros.put("fechafin", fin);
        creaInformeBasico(jasper, parametros, destino);
    }
    /**
     * Se encarga de crear el tercer informe
     */
    private void crearInforme3() {
        String jasper = "./informes/consulta3.jasper";
        String pdf = "./informesgenerados/entrenamientosemanal.pdf";
        Map parametros = new HashMap();
        parametros.put("anio", Integer.valueOf(informes.getTfAnno().getText()));
        parametros.put("mes", Integer.valueOf(informes.getCbMes().getSelectedIndex()) + 1);
        creaInformeBasico(jasper, parametros, pdf);
    }
    /**
     * Contiene las sentencias necesarias para crear un informe
     * @param jasper
     * @param parametros
     * @param pdf
     * @throws HeadlessException 
     */
    private void creaInformeBasico(String jasper, Map parametros, String pdf) throws HeadlessException {
        try {
            Connection connection = bd.getConnection();
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, pdf);
            JOptionPane.showMessageDialog(informes, "Informe creado", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (JRException ex) {
            Logger.getLogger(BridgeInformes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(informes, "No se pudo crear el informe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Se encarga de crear el cuarto informe
     */
    private void crearInforme4() {
        String jasper = "./informes/consulta5.jasper";
        String pdf = "./informesgenerados/itinerariosdificultad.pdf";
        Timestamp ini = new Timestamp(informes.getDateTextField7().getDate().getTime());
        Timestamp fin = new Timestamp(informes.getDateTextField8().getDate().getTime());
        creaInformeRangoFechas(ini, fin, jasper, pdf);
    }
}
