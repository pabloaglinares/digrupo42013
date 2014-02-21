/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import datos.pojos.Sesion;
import enlace_datos_gui.BridgeSesion;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class PanelTipoSesion extends javax.swing.JPanel {
private final BridgeSesion bridge = BridgeSesion.BRIDGE;
    /**
     * Creates new form PanelTipoSesion
     */
    public PanelTipoSesion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTipoSesion = new javax.swing.JComboBox();

        cbTipoSesion.setModel(new javax.swing.DefaultComboBoxModel(Sesion.TipoSesion.values()));
        cbTipoSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(cbTipoSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbTipoSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoSesionActionPerformed
        bridge.loadByTipo((Sesion.TipoSesion)cbTipoSesion.getSelectedItem());
    }//GEN-LAST:event_cbTipoSesionActionPerformed
    public Sesion.TipoSesion getTipoSesion() {
        return (Sesion.TipoSesion) cbTipoSesion.getSelectedItem();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTipoSesion;
    // End of variables declaration//GEN-END:variables
}
