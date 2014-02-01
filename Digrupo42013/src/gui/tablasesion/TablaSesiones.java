/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tablasesion;

import datos.daos.SesionDAO;
import datos.pojos.Sesion;
import enlace_datos_gui.BridgeSesion;
import gui.botonestablas.ButtonCellEditor;
import gui.botonestablas.ButtonListener;
import gui.botonestablas.ButtonRender;
import gui.botonestablas.WraperFila;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 * Tabla específica para contener sesiones, con la particularidad de que cada
 * fila además de la sesión contiene 2 botones uno para modificar y otro para
 * eliminar la sesión de dicha fila.
 * @author Andrés Traspuesto Lanza
 */
public class TablaSesiones extends JTable {

    private SesionTableModel model; 
    private final String[] cabecera = {"Fecha", "Hora inicio", "Hora fin", "Tipo", "Descripción", "Borrar", "Editar"};
    private List<WraperFila<Sesion>> filas = new ArrayList<>();
    private final BridgeSesion bridge = BridgeSesion.BRIDGE;
    /**
     * Devuelve un objeto tabla
     */
    public TablaSesiones() {
        
        model = new SesionTableModel(cabecera, filas);

        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
        this.setDefaultRenderer(JButton.class, new ButtonRender("resources/borrar.png"));
        
        //Determino la acción que debe realizarse al pulsar en borrar
        ButtonCellEditor del = new ButtonCellEditor("Borrar");
        del.addListener(new ButtonListener() {

            @Override
            public void handleActionOnCellButton() {
                int row = TablaSesiones.this.getSelectedRow();
                //Borro la sesion de la BD
                SesionDAO.SESION_DAO.deleteSesion(TablaSesiones.this.model.getSesion(row));
                //Borro la sesion de la tabla
                TablaSesiones.this.model.deleteSesion(row);
            }
        });
        this.getColumn("Borrar").setCellEditor(del);
        
        //Determino la acción que debe realizarse al pulsar en editar
            ButtonCellEditor mod = new ButtonCellEditor("Editar");
            mod.addListener(new ButtonListener() {

                @Override
                public void handleActionOnCellButton() {
                    int row = TablaSesiones.this.getSelectedRow();
                    bridge.openToModifySesion(TablaSesiones.this.model.getSesion(row));
                }
            });
            this.getColumn("Editar").setCellEditor(mod);

    }

}
