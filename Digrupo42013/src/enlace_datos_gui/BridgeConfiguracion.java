package enlace_datos_gui;

import datos.daos.ConfiguracionDAO;
import datos.pojos.Configuracion;
import gui.AltaConfiguracion;
import gui.Main;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeConfiguracion {

    CONFIGURACION;
    private Main main;
    private final ConfiguracionDAO dao = ConfiguracionDAO.CONFIGURACION_DAO;
    private BridgeRendimiento bridgeRendimiento = BridgeRendimiento.RENDIMIENTO;
    private AltaConfiguracion alta;

    
    public void setAlta(AltaConfiguracion alta) {
        this.alta = alta;
    }

    public void openConfiguracion(Main main) {
        this.main = main;
        alta = new AltaConfiguracion();
        main.getDpEscritorio().add(alta);
        alta.show();
    }

    public void closeConfiguracion() {
        alta.dispose();
        alta = null;
    }
    /**
     * Guarda la configuración a partir del contenido de los campos
     */
    public void saveConfiguracion() {
        dao.insertConfiguracion(new Configuracion(alta.getTfNombre().getText(),
                alta.getTfApellidos().getText(), alta.getDtfFecha1().getDate(),
                alta.getDtfFecha2().getDate()));
        bridgeRendimiento.setRendimiento();
    }

    /**
     * Carga en la pantalla de alta de la configuración los datos actuales en
     * caso de que estén guardados
     */
    public void loadConfiguracion() {
        Configuracion cfg = dao.getConfiguracion();
        if (cfg != null) {
            alta.getDtfFecha1().setDate(cfg.getFecha1Intervalo());
            alta.getDtfFecha2().setDate(cfg.getFecha2Intervalo());
            alta.getTfNombre().setText(cfg.getNombre());
            alta.getTfApellidos().setText(cfg.getApellidos());
        }
    }
}
