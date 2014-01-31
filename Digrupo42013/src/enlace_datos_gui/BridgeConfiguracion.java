
package enlace_datos_gui;

import datos.UtilesBD;
import datos.daos.ConfiguracionDAO;
import datos.pojos.Configuracion;
import java.util.Date;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class BridgeConfiguracion {
    private final ConfiguracionDAO dao = ConfiguracionDAO.CONFIGURACION_DAO;
    /**
     * Guarda la configuración pasada por parámetros
     * @param nombre
     * @param apellidos
     * @param fecha1Intervalo 
     * @param fecha2Intervalo 
     */
    public void saveConfiguracion(String nombre, String apellidos, Date fecha1Intervalo, Date fecha2Intervalo) {
        float rendimiento = UtilesBD.INSTANCE.calculaRendimiento();
        dao.insertConfiguracion(new Configuracion(nombre, apellidos, fecha1Intervalo, fecha2Intervalo, rendimiento));
    }
    /**
     * Devuelve la configuración almacenada en la BD
     * @return 
     */
    public Configuracion getConfiguracion() {
        return dao.getConfiguracion();
    }
}
