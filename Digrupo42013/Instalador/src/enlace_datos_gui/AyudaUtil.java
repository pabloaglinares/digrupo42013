

package enlace_datos_gui;

import gui.Main;
import java.awt.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public enum AyudaUtil {
    AYUDA;
    public enum PantallaAyuda {
        /**
         * Muestra la pantanlla de ayuda correspondiente a la portada
         */
        PORTADA("portada"),
        /**
         * Muestra la pantanlla de ayuda correspondiente al alta o modificación 
         * de itinerarios
         */
        ALTA_ITINERARIO("altaitinerarios"),
        /**
         * Muestra la pantanlla de ayuda correspondiente al listado de itinerarios
         */
        LISTADO_ITINERARIO("listadoitinerarios"),
        /**
         * Muestra la pantanlla de ayuda correspondiente al detalle de itinerarios
         */
        DETALLE_ITINERARIO("detalleitinerario"),
        /**
         * Muestra la pantanlla de ayuda correspondiente al alta o modificación 
         * de itinerarios
         */
        ALTA_SESION("altasesiones"),
        /**
         * Muestra la pantanlla de ayuda correspondiente al listado de sesiones
         */
        LISTADO_SESION("listadosesiones"),
        /**
         * Muestra la pantanlla de ayuda correspondiente a la pantalla de informes
         */
        INFORMES("informes"),
        /**
         * Muestra la pantanlla de ayuda correspondiente a la pantalla de configuración
         */
        CONFIGURACION("configuracion");
        
        private String map;

        private PantallaAyuda(String map) {
            this.map = map;
        }

        public String getMap() {
            return map;
        }
        
        
    }
    private Main main;
    private HelpBroker hb;
    private HelpSet helpset;
    /**
     * Crea la ayuda
     * @param main
     * @param miAyuda 
     */
    public void createHelp(Main main,JMenuItem miAyuda) {
        this.main = main;
        try {
            // Carga el fichero de ayuda
            File fichero = new File("help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();
            // Crea el HelpSet y el HelpBroker
            helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            hb = helpset.createHelpBroker();
            // Pone ayuda a item de menu al pulsarlo.
            hb.enableHelpOnButton(miAyuda, "portada", helpset);
            hb.enableHelpKey(main.getRootPane(), "portada", helpset);

        } catch (IllegalArgumentException | MalformedURLException | HelpSetException e) {
            JOptionPane.showMessageDialog(main, "Se ha producido un error "
                    + "intentando mostrar la ayuda.\n" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE, null);

        }
    }
    /**
     * Asocia al componente la pantalla de ayuda indicada
     * @param component
     * @param pantalla 
     */
    public void asociateF1ToJFrame(Component component, PantallaAyuda pantalla) {
        hb.enableHelpKey(component, pantalla.getMap(), helpset);
    }
}
