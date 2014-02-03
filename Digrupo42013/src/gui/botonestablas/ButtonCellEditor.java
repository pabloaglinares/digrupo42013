package gui.botonestablas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor {

    private Component currentValue; //componente en edición
    private final String accion; //nombre de la acción que ejecuta
    private ButtonListener listener; //encargado de manejar la acción sobre el botón
    private JButton btn = null;

    /**
     * Devuelve un objeto ButtonCellEditor
     *
     * @param accion
     */
    public ButtonCellEditor(String accion) {
        this.accion = accion;
    }

    /**
     * Asigna el encargado de manejar los eventos sobre el botón
     *
     * @param listener
     */
    public void addListener(ButtonListener listener) {
        this.listener = listener;
    }

    /**
     * Define que componente contiene la celda y se le asigna la acción a
     * realizar
     *
     * @param table
     * @param value
     * @param isSelected
     * @param row
     * @param column
     * @return
     */
    @Override
    public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, final int row, int column) {
        if (value instanceof JButton) {
            btn = (JButton) value;
            
            btn.setAction(new AbstractAction(accion) {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (listener != null) {
                        listener.handleActionOnCellButton();
                    }
                    
                }
            });
        }
        currentValue = btn;
        return btn;
    }
    /**
     * Devuelve el componente actual
     * @return 
     */
    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

}
