/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tablasesion;

import datos.daos.SesionDAO;
import datos.pojos.Sesion;
import gui.botonestablas.ButtonCellEditor;
import gui.botonestablas.ButtonListener;
import gui.botonestablas.ButtonRender;
import gui.botonestablas.Fila;
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

    private ButtonListener handleOnModificar; //Clase que se encarga de manejar el click sobre el botón de editar
    private SesionTableModel model; 
    private final String[] cabecera = {"Fecha", "Hora inicio", "Hora fin", "Dificultad", "Descripción", "Borrar", "Editar"};
    private List<Fila<Sesion>> filas = new ArrayList<>();
    /**
     * Devuelve un objeto tabla
     */
    public TablaSesiones() {
        
        model = new SesionTableModel(cabecera, filas);

        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
        this.setDefaultRenderer(JButton.class, new ButtonRender());
        
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
        if (handleOnModificar != null) {
            ButtonCellEditor mod = new ButtonCellEditor("Editar");
            mod.addListener(handleOnModificar);
            this.getColumn("Editar").setCellEditor(mod);
        }

    }

}
