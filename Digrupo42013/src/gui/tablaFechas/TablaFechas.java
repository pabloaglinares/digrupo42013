/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tablaFechas;

import enlace_datos_gui.BridgeItinerario;
import gui.botonestablas.ButtonCellEditor;
import gui.botonestablas.ButtonListener;
import gui.botonestablas.ButtonRender;
import gui.botonestablas.WraperFila;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class TablaFechas extends JTable{
    private FechasTableModel model;
    String[] cabeceras = {"Fecha","Borrar","Editar"};
    private List<WraperFila<Date>> filas = new ArrayList<>();
    private final BridgeItinerario bridge = BridgeItinerario.BRIDGE;
    
    public TablaFechas() {
        model = new FechasTableModel(cabeceras, filas);
        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
        this.setDefaultRenderer(JButton.class, new ButtonRender());
        
        //Determino la acción que debe realizarse al pulsar en borrar
        ButtonCellEditor del = new ButtonCellEditor("Borrar");
        
        del.addListener(new ButtonListener() {

            @Override
            public void handleActionOnCellButton() {
                int row = TablaFechas.this.getSelectedRow();
                TablaFechas.this.model.removeDate(row);
                bridge.setDateModified();
            }
        });
        this.getColumn("Borrar").setCellEditor(del);
        
        //Determino la acción que debe realizarse al pulsar en editar
            ButtonCellEditor mod = new ButtonCellEditor("Editar");
            mod.addListener(new ButtonListener() {

                @Override
                public void handleActionOnCellButton() {
                    int row = TablaFechas.this.getSelectedRow();
                    bridge.setDateModified();
                }
            });
            this.getColumn("Editar").setCellEditor(mod);

    }

    @Override
    public FechasTableModel getModel() {
        return model; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
