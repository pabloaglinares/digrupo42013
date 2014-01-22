package gui.botonestablas;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Clase que determina como se debe mostrar un botón contenido en una celda
 *
 * @author Andrés Traspuesto Lanza
 */
public class ButtonRender implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (value instanceof JButton) {
            return (JButton) value;
        }

        return null;

    }

}
