/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
    @Override
    public void onCambioFecha() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 * and open the template in the editor.
 */

package gui;

import calendario.CambioFechaListener;
import enlace_datos_gui.BridgeSesion;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class PanelRangoFechas extends javax.swing.JPanel {
    /**
     * Creates new form PanelRangoFechas
     */
    public PanelRangoFechas() {
        initComponents();
    }
    public void addCambioFechaListener(CambioFechaListener listener) {
        dtfFecha1.addCambioFechaListener(listener);
        dtfFecha2.addCambioFechaListener(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFecha1 = new javax.swing.JLabel();
        dtfFecha1 = new calendario.DateTextField();
        dtfFecha2 = new calendario.DateTextField();
        labelFecha2 = new javax.swing.JLabel();

        labelFecha1.setText("Primer día:");

        dtfFecha1.setText("21/01/2014");

        dtfFecha2.setText("21/01/2014");

        labelFecha2.setText("Último día:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFecha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelFecha2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFecha2)
                    .addComponent(labelFecha1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
public Date getFecha1() {
    return dtfFecha1.getDate();
}
public Date getFecha2() {
    return dtfFecha2.getDate();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private calendario.DateTextField dtfFecha1;
    private calendario.DateTextField dtfFecha2;
    private javax.swing.JLabel labelFecha1;
    private javax.swing.JLabel labelFecha2;
    // End of variables declaration//GEN-END:variables
}
