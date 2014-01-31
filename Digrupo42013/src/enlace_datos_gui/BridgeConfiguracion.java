
package enlace_datos_gui;

import datos.UtilesBD;
import datos.daos.ConfiguracionDAO;
import datos.pojos.Configuracion;
import gui.AltaConfiguracion;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeConfiguracion {
    CONFIGURACION;
    private final ConfiguracionDAO dao = ConfiguracionDAO.CONFIGURACION_DAO;
    private AltaConfiguracion alta;

    public void setAlta(AltaConfiguracion alta) {
        this.alta = alta;
    }
    
    /**
     * Guarda la configuración a partir del contenido de los campos
     */
    public void saveConfiguracion() {
        float rendimiento = UtilesBD.INSTANCE.calculaRendimiento();
        dao.insertConfiguracion(new Configuracion(alta.getTfNombre().getText(), 
                alta.getTfApellidos().getText(), alta.getDtfFecha1().getDate(), 
                alta.getDtfFecha2().getDate()));
    }
    
    /**
     * Carga en la pantalla de alta de la configuración los datos actuales
     * en caso de que estén guardados
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
