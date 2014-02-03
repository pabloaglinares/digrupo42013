/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tablasesion;

import datos.daos.SesionDAO;
import datos.pojos.Sesion;
import enlace_datos_gui.BridgeSesion;
import gui.botonestablas.ButtonRender;
import gui.botonestablas.WraperFila;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

/**
 * Tabla específica para contener sesiones, con la particularidad de que cada
 * fila además de la sesión contiene 2 botones uno para modificar y otro para
 * eliminar la sesión de dicha fila.
 *
 * @author Andrés Traspuesto Lanza
 */
public class TablaSesiones extends JTable {

    private SesionTableModel model;
    private final String[] cabecera = {"Fecha", "Hora inicio", "Hora fin", "Tipo", "Descripción", "", ""};
    private List<WraperFila<Sesion>> filas = new ArrayList<>();
    private final BridgeSesion bridge = BridgeSesion.BRIDGE;

    /**
     * Devuelve un objeto tabla
     */
    public TablaSesiones() {

        model = new SesionTableModel(cabecera, filas);

        this.setModel(model);
        //Determino quién debe encargarse de mostrar las celdas con botones
        getColumnModel().getColumn(5).setCellRenderer(new ButtonRender("imagenes/borrar.png"));
        getColumnModel().getColumn(6).setCellRenderer(new ButtonRender("imagenes/editar.png"));
        this.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = TablaSesiones.this.getSelectedRow();
                int col = TablaSesiones.this.getSelectedColumn();
                switch (col) {
                    case 5: //Si se ha pinchado en el botón borrar
                        SesionDAO.SESION_DAO.deleteSesion(TablaSesiones.this.model.getSesion(row));
                        TablaSesiones.this.model.deleteSesion(row);
                        break;
                    case 6: //Si se ha pinchado en editar
                        bridge.openToModifySesion(TablaSesiones.this.model.getSesion(row));
                        break;
                    default:
                    //aquí va mostrar la pantalla de detalles
                }
            }
        });

    }

}
