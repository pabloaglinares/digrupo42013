package enlace_datos_gui;

import datos.UtilesBD;
import gui.Informes;
import gui.Main;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
     * Se encarga de crear el informe que proceda en función de la pestaña
     * activa
     */
    public void onCrear() {
        informes.getBtCrear().setEnabled(false);
        informes.getBtCrear().setText("Creando");
        informes.getBtVolver().setEnabled(false);
        informes.getBtVolver().setText("Creando");
        JFileChooser fc = new JFileChooser(new File("informes"));
        if (fc.showSaveDialog(main) == JFileChooser.APPROVE_OPTION) {
            
            switch (informes.getCbInformes().getSelectedIndex()) {
                case 0:
                    crearInforme3(fc.getSelectedFile());
                    break;
                case 1:
                    crearInforme5(fc.getSelectedFile());
                    break;
                case 2:
                    crearInforme1(fc.getSelectedFile());
                    break;
                case 3:
                    crearInforme4(fc.getSelectedFile());
                    break;
                case 4:
                    crearInforme2(fc.getSelectedFile());
                    break;
            }
        }
        informes.getBtCrear().setEnabled(true);
        informes.getBtCrear().setText("Crear informe");
        informes.getBtVolver().setEnabled(true);
        informes.getBtVolver().setText("Volver");
    }

    /**
     * Se encarga de crear el primer informe
     */
    private void crearInforme1(File dst) {
        
        creaInformeRangoFechas("resources/informes/consulta1.jasper", dst.getAbsolutePath());
    }

    /**
     * Se encarga de crear el segundo informe
     */
    private void crearInforme2(File dst) {
        creaInformeRangoFechas("resources/informes/consulta2.jasper", dst.getAbsolutePath());

    }

    /**
     * Se encarga de crear el tercer informe
     */
    private void crearInforme3(File dst) {
        Map parametros = new HashMap();
        parametros.put("anio", Integer.valueOf(informes.getPnSemana().getTfAnno().getText()));
        parametros.put("mes", Integer.valueOf(informes.getPnSemana().getCbMes().getSelectedIndex()) + 1);
        creaInformeBasico("resources/informes/consulta3.jasper", parametros, dst.getAbsolutePath());
    }

    /**
     * Se encarga de crear el cuarto informe
     */
    private void crearInforme4(File dst) {
        creaInformeRangoFechas("resources/informes/consulta4.jasper", dst.getAbsolutePath());
    }

    /**
     * Se encarga de crear el quinto informe
     */
    private void crearInforme5(File dst) {
        creaInformeRangoFechas("resources/informes/consulta5.jasper", dst.getAbsolutePath());
    }

    /**
     * Crea un informe que necesita dos fechas como parámetros
     *
     * @param ini
     * @param fin
     * @param jasper
     * @param destino
     * @throws HeadlessException
     */
    private void creaInformeRangoFechas(String jasper, String destino) throws HeadlessException {
        Timestamp ini = new Timestamp(informes.getPnFechas().getFecha1().getTime());
        Timestamp fin = new Timestamp(informes.getPnFechas().getFecha2().getTime());
        Map parametros = new HashMap();
        parametros.put("fechainicio", ini);
        parametros.put("fechafin", fin);
        creaInformeBasico(jasper, parametros, destino);
    }

    /**
     * Contiene las sentencias necesarias para crear un informe
     *
     * @param jasper
     * @param parametros
     * @param pdf
     * @throws HeadlessException
     */
    private void creaInformeBasico(String jasper, Map parametros, String pdf) throws HeadlessException {
        try {
            Connection connection = bd.getConnection();
            URLClassLoader urlLoader=(URLClassLoader)this.getClass().getClassLoader();
            JasperPrint print = JasperFillManager.fillReport(urlLoader.getResourceAsStream(jasper), parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, pdf);
            // JOptionPane.showMessageDialog(informes, "Informe creado", "", JOptionPane.INFORMATION_MESSAGE);
            openInforme(pdf);

        } catch (JRException ex) {
            Logger.getLogger(BridgeInformes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(informes, "No se pudo crear el informe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Se encarga de mostrar el informe que se le pase como parámetro
     *
     * @param informe
     */
    private void openInforme(String informe) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(informe);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                //No hay aplicaciones predefinidas para abrir pdf
            }
        } else {
        }
    }
}
