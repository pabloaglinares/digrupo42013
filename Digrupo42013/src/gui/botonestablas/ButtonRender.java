package gui.botonestablas;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Clase que determina como se debe mostrar un botón contenido en una celda
 *
 * @author Andrés Traspuesto Lanza
 */
public class ButtonRender implements TableCellRenderer {
    private String img;

    public ButtonRender(String img) {
        this.img = img;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            switch(btn.getText()){
                case "Borrar":
                    btn.setIcon(new ImageIcon("resources/borrar.png"));
                    break;
                case "Editar":
                    break;
            }
            return btn;
        }

        return null;

    }

}
