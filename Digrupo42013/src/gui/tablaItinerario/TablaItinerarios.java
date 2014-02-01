package gui.tablaItinerario;

import datos.daos.ItinerarioDAO;
import datos.pojos.Itinerario;
import enlace_datos_gui.BridgeItinerario;
import gui.botonestablas.ButtonCellEditor;
import gui.botonestablas.ButtonListener;
import gui.botonestablas.ButtonRender;
import gui.botonestablas.WraperFila;
import javax.swing.JButton;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tabla específica para contener Itinerarios, con la particularidad de que cada
 * fila además del Itinerario contiene 2 botones uno para modificar y otro para
 * eliminar el Itinerario de dicha fila.
 *
 * @author Andrés Traspuesto Lanza
 */
public class TablaItinerarios extends JTable {

    private ButtonListener handleOnModificar; //Clase que se encarga de manejar el click sobre el botón de editar
    private ItinerariosTableModel model;
    private final String[] cabecera = {"Nombre", "Localización", "Dificultad", "Borrar", "Editar"};
    private List<WraperFila<Itinerario>> filas = new ArrayList<>();

    /**
     * Devuelve un objeto tabla
     */
    public TablaItinerarios() {

        model = new ItinerariosTableModel(cabecera, filas);

        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
        this.setDefaultRenderer(JButton.class, new ButtonRender("resources/borrar.png"));

        //Determino la acción que debe realizarse al pulsar en borrar
        ButtonCellEditor del = new ButtonCellEditor("Borrar");
        del.addListener(new ButtonListener() {

            @Override
            public void handleActionOnCellButton() {
                int row = TablaItinerarios.this.getSelectedRow();
                ItinerarioDAO.ITINERARIO_DAO.deleteItinerario(TablaItinerarios.this.model.getItinerario(row));
                TablaItinerarios.this.model.deleteItinerario(row);
            }
        });
        this.getColumn("Borrar").setCellEditor(del);

        //Determino la acción que debe realizarse al pulsar en editar
        ButtonCellEditor mod = new ButtonCellEditor("Editar");
        mod.addListener(new ButtonListener() {

            @Override
            public void handleActionOnCellButton() {
                int row = TablaItinerarios.this.getSelectedRow();
                BridgeItinerario.BRIDGE.opneForUpdateItinerario(TablaItinerarios.this.model.getItinerario(row));
            }
        });
        this.getColumn("Editar").setCellEditor(mod);

    }

}
