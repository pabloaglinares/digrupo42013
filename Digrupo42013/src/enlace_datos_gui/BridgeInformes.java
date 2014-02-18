package enlace_datos_gui;

import datos.UtilesBD;
import gui.Informes;
import gui.Main;
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

    /**
     * Abre la ventana de creación de informes
     *
     * @param main
     */
    public void openInformes(Main main) {
        this.main = main;
        informes = new Informes();
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
        try {
            Connection connection = bd.getConnection();
            Map parametros = new HashMap();
            Timestamp ini = new Timestamp(informes.getDateTextField3().getDate().getTime());
            Timestamp fin = new Timestamp(informes.getDateTextField4().getDate().getTime());
            parametros.put("fechainicio", ini);
            parametros.put("fechafin", fin);
            JasperPrint print = JasperFillManager.fillReport("./informes/consulta2.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "./informesgenerados/sesiones.pdf");
            JOptionPane.showMessageDialog(informes, "Informe creado", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(informes, "No se pudo crear el informe", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(BridgeInformes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Se encarga de crear el primer informe
     */
    private void crearInforme1() {
        try {
            Connection connection = bd.getConnection();
            Map parametros = new HashMap();
            Timestamp ini = new Timestamp(informes.getDateTextField1().getDate().getTime());
            Timestamp fin = new Timestamp(informes.getDateTextField2().getDate().getTime());
            parametros.put("fechainicio", ini);
            parametros.put("fechafin", fin);
            JasperPrint print = JasperFillManager.fillReport("./informes/consulta1.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "./informesgenerados/itinerarios.pdf");
            JOptionPane.showMessageDialog(informes, "Informe creado", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(informes, "No se pudo crear el informe", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(BridgeInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Se encarga de crear el quinto informe
     */
    private void crearInforme5() {
        try {
            Connection connection = bd.getConnection();
            Map parametros = new HashMap();
            Timestamp ini = new Timestamp(informes.getDateTextField5().getDate().getTime());
            Timestamp fin = new Timestamp(informes.getDateTextField6().getDate().getTime());
            parametros.put("fechainicio", ini);
            parametros.put("fechafin", fin);
            JasperPrint print = JasperFillManager.fillReport("./informes/consulta4.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "./informesgenerados/sesionestipo.pdf");
            JOptionPane.showMessageDialog(informes, "Informe creado", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(informes, "No se pudo crear el informe", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(BridgeInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Se encarga de crear el tercer informe
     */
    private void crearInforme3() {
        try {
            Connection connection = bd.getConnection();
            Map parametros = new HashMap();
            parametros.put("anio", Integer.valueOf(informes.getTfAnno().getText()));
            parametros.put("mes", Integer.valueOf(informes.getCbMes().getSelectedIndex()) + 1);
            JasperPrint print = JasperFillManager.fillReport("./informes/consulta3.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "./informesgenerados/entrenamientosemanal.pdf");
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
        try {
            Connection connection = bd.getConnection();
            Map parametros = new HashMap();
            //cambiar al nuevo campo fechas
            Timestamp ini = new Timestamp(informes.getDateTextField7().getDate().getTime());
            Timestamp fin = new Timestamp(informes.getDateTextField8().getDate().getTime());
            parametros.put("fechainicio", ini);
            parametros.put("fechafin", fin);
            JasperPrint print = JasperFillManager.fillReport("./informes/consulta5.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "./informesgenerados/itinerariosdificultad.pdf");
            JOptionPane.showMessageDialog(informes, "Informe creado", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (JRException ex) {
            Logger.getLogger(BridgeInformes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(informes, "No se pudo crear el informe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
