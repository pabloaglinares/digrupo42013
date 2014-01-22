package gui.tablaItinerario;


import gui.botonestablas.ButtonCellEditor;
import gui.botonestablas.ButtonListener;
import gui.botonestablas.ButtonRender;
import javax.swing.JButton;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.List;


/**
 * Tabla que muestra los itinerarios
 * @author Andrés Traspuesto Lanza
 */
public class TablaItinerarios extends JTable {

    private ButtonListener handleOnModificar; //Clase que se encarga de manejar el click sobre el botón de editar
    private ItinerariosTableModel model; 
    private final String[] cabecera = {"Nombre", "Localización", "Dificultad", "Borrar", "Editar"};
    private List<FilaItinerario> filas = new ArrayList<>();
    /**
     * Devuelve un objeto tabla
     */
    public TablaItinerarios() {
        
        model = new ItinerariosTableModel(cabecera, filas);

        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
        this.setDefaultRenderer(JButton.class, new ButtonRender());
        
        //Determino la acción que debe realizarse al pulsar en borrar
        ButtonCellEditor del = new ButtonCellEditor("Borrar");
        del.addListener(new ButtonListener() {

            @Override
            public void doSomething() {
                int row = TablaItinerarios.this.getSelectedRow();
                TablaItinerarios.this.model.deleteItinerario(row);
            }
        });
        this.getColumn("Borrar").setCellEditor(del);
        
        //Determino la acción que debe realizarse al pulsar en editar
        if (handleOnModificar != null) {
            ButtonCellEditor mod = new ButtonCellEditor("Editar");
            mod.addListener(handleOnModificar);
            this.getColumn("Editar").setCellEditor(mod);
        }

    }

}
