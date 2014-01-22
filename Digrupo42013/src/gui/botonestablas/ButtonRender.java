package gui.botonestablas;


import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class ButtonRender implements TableCellRenderer{

    @Override
 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
   int row, int column) {
 
  // Devolvemos el botón tal cual
  if (value instanceof JButton) {
   return (JButton) value;
  }
 
  return null;
 
 }

}
