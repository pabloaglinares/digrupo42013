
package enlace_datos_gui;

import datos.daos.ItinerarioDAO;
import datos.pojos.Itinerario;
import gui.tablaItinerario.ItinerariosTableModel;
import gui.tablaItinerario.TablaItinerarios;
import java.io.File;
import java.util.Date;

/**
 * Este singleton es un bridge que enlaza la capa gráfica con la de datos Itinerario
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeItinerario {
    BRIDGE;
    private TablaItinerarios tabla;
    private ItinerarioDAO dao = ItinerarioDAO.ITINERARIO_DAO;
    private int currentTab = 0;
    private Date date1;
    private Date date2;
    
    /**
     * Asigna la pestaña actual
     * @param currentTab
     */
    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }
    /**
     * Añade un objeto TablaItinerarios para que sea actualizado si se producen 
     * cambios.
     * Hay que llamar este método si se mantiene abierta la ventana de listado
     * de itinerarios a la par que una de alta o modificación de sesiones
     * @param tabla 
     */
    public void addTablaItinerario(TablaItinerarios tabla) {
        this.tabla = tabla;
    }
    /**
     * Indica que no es necesario seguir actualizando la tabla de itinerarios
     * Es bueno llamar a este método cuando se cierra la ventana de listado de
     * sesiones
     */
    public void delTablaItinerarios() {
        this.tabla = null;
    }
    public void saveItinerario(String nombre,String localizacion, String difucultad, File pathImagen, Date date ) {
        Itinerario it = new Itinerario(nombre, localizacion, difucultad, pathImagen);
        it.addFechaResolucion(date);
        dao.insertItinerario(it);
        loadItinerarioProperly(it);
        
    }

    private void loadItinerarioProperly(Itinerario it) {
        switch (currentTab) {
            case 0:
                loadAllSesion();
                break;
            case 1:
                loadByRange(date1, date2);
                break;
        }
    }

    public void loadAllSesion() {
        if(tabla != null) ((ItinerariosTableModel)tabla.getModel()).setItinerarios(dao.getAllItinerario());
    }

    public void loadByRange(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
        if(tabla != null) ((ItinerariosTableModel)tabla.getModel()).setItinerarios(dao.getItinerariosByFechaRange(date1, date2));
    }
}
