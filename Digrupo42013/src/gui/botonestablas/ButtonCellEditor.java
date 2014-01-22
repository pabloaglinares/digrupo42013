package gui.botonestablas;


import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor {

    /**
     * Componente que estamos editando.
     */
    private Component currentValue;
    private final String accion;
    private ButtonListener lis;
    public void addListener(ButtonListener lis) {
        this.lis = lis;
    }

    public ButtonCellEditor(String accion) {
        this.accion = accion;
    }
    
    @Override
    public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, final int row, int column) {

        JButton button = null;

        if (value instanceof JButton) {
            button = (JButton) value;
            // Action que permite "limpiar" los valores de una fila
            button.setAction(new AbstractAction(accion) {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (lis != null) lis.doSomething();
                }
            });
        }

        currentValue = button;

        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

}
