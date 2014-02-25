package gui.botonestablas;

import java.awt.Component;
import java.net.URLClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Clase que determina como se debe mostrar un botón contenido en una celda
 *
 * @author Andrés Traspuesto Lanza
 */
public class ButtonRender extends JButton implements TableCellRenderer{
    private String img;

    public ButtonRender(String img) {
        this.img = img;
        URLClassLoader urlLoader=(URLClassLoader)this.getClass().getClassLoader();
        setIcon(new ImageIcon(urlLoader.findResource(img)));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        return this;

    }

}
