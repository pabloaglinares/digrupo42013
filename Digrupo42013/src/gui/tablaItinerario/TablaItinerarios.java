package gui.tablaItinerario;

import datos.daos.ItinerarioDAO;
import datos.pojos.Itinerario;
import enlace_datos_gui.BridgeItinerario;
import gui.botonestablas.ButtonRender;
import gui.botonestablas.WraperFila;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;

/**
 * Tabla específica para contener Itinerarios, con la particularidad de que cada
 * fila además del Itinerario contiene 2 botones uno para modificar y otro para
 * eliminar el Itinerario de dicha fila.
 *
 * @author Andrés Traspuesto Lanza
 */
public class TablaItinerarios extends JTable {

    private ItinerariosTableModel model;
    private final String[] cabecera = {"Nombre", "Localización", "Dificultad", null, null};
    private List<WraperFila<Itinerario>> filas = new ArrayList<>();
    private final BridgeItinerario bridge = BridgeItinerario.BRIDGE;
    /**
     * Devuelve un objeto tabla
     */
    public TablaItinerarios() {

        model = new ItinerariosTableModel(cabecera, filas);

        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
         String borrar = "resources/borrar.png";
        String editar ="resources/editar.png";
        //Determino quién debe encargarse de mostrar las celdas con botones
        getColumnModel().getColumn(3).setCellRenderer(new ButtonRender(borrar));
        getColumnModel().getColumn(4).setCellRenderer(new ButtonRender(editar));
        this.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = TablaItinerarios.this.getSelectedRow();
                int col = TablaItinerarios.this.getSelectedColumn();
                Itinerario it = TablaItinerarios.this.model.getItinerario(row);
                switch (col) {
                    case 3: //Si se ha pinchado en el botón borrar
                        ItinerarioDAO.ITINERARIO_DAO.deleteItinerario(it);
                        TablaItinerarios.this.model.deleteItinerario(row);
                        break;
                    case 4: //Si se ha pinchado en editar
                        bridge.opneForUpdateItinerario(it);
                        break;
                    default:
                        bridge.showItinerario(it);
                    //aquí va mostrar la pantalla de detalles
                }
            }
        });
    }

}
