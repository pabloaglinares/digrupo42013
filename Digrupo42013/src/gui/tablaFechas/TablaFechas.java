/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tablaFechas;

import enlace_datos_gui.BridgeItinerario;
import gui.botonestablas.ButtonRender;
import gui.botonestablas.WraperFila;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class TablaFechas extends JTable{
    private FechasTableModel model;
    String[] cabeceras = {"Fecha",null,null};
    private List<WraperFila<Date>> filas = new ArrayList<>();
    private final BridgeItinerario bridge = BridgeItinerario.BRIDGE;
    
    public TablaFechas() {
        model = new FechasTableModel(cabeceras, filas);
        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
        getColumnModel().getColumn(1).setCellRenderer(new ButtonRender("imagenes/borrar.png"));
        getColumnModel().getColumn(2).setCellRenderer(new ButtonRender("imagenes/editar.png"));
        this.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = TablaFechas.this.getSelectedRow();
                int col = TablaFechas.this.getSelectedColumn();
                switch (col) {
                    case 1: //Si se ha pinchado en el botón borrar
                        TablaFechas.this.model.removeDate(row);
                        bridge.setDateModified();
                        break;
                    case 2: //Si se ha pinchado en editar
                        bridge.setDateModified();
                        break;
                    default:
                    //aquí va mostrar la pantalla de detalles
                }
            }
        });
       
    }

    @Override
    public FechasTableModel getModel() {
        return model; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
