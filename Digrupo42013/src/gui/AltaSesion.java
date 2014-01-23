/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.File;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class AltaSesion extends javax.swing.JInternalFrame {

    
    
    /**
     * Creates new form AltaSesion
     */
    public AltaSesion() {
        initComponents();
        this.setIconifiable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        
        
        
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateTextFieldBeanInfo1 = new calendario.DateTextFieldBeanInfo();
        dateTextFieldBeanInfo2 = new calendario.DateTextFieldBeanInfo();
        datePropertyEditorSupport1 = new calendario.DatePropertyEditorSupport();
        labelFechaSesion = new javax.swing.JLabel();
        labelHoraComienzo = new javax.swing.JLabel();
        tfHoraComienzo = new javax.swing.JTextField();
        labelHoraFin = new javax.swing.JLabel();
        tfHoraFin = new javax.swing.JTextField();
        labelTipoSesion = new javax.swing.JLabel();
        cbTipoSesion = new javax.swing.JComboBox();
        labelDescripcion = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        btVolver = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        dateTextField1 = new calendario.DateTextField();

        setTitle("Alta/Modif Sesiones");
        setMinimumSize(new java.awt.Dimension(300, 335));
        setPreferredSize(new java.awt.Dimension(300, 335));

        labelFechaSesion.setText("Fecha Sesión:");

        labelHoraComienzo.setText("Hora Comienzo:");

        labelHoraFin.setText("Hora Fin:");

        labelTipoSesion.setText("Tipo Sesión:");

        cbTipoSesion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Físico", "Rocódromo", "Roca" }));

        labelDescripcion.setText("Descripción:");

        btVolver.setText("Volver");
        btVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverActionPerformed(evt);
            }
        });

        btGuardar.setText("Guardar");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        dateTextField1.setText("dateTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescripcion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTipoSesion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelHoraFin, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelHoraComienzo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelFechaSesion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfHoraComienzo)
                            .addComponent(tfHoraFin)
                            .addComponent(cbTipoSesion, 0, 141, Short.MAX_VALUE)
                            .addComponent(tfDescripcion))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTipoSesion, tfDescripcion, tfHoraComienzo, tfHoraFin});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaSesion)
                    .addComponent(dateTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHoraComienzo)
                    .addComponent(tfHoraComienzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHoraFin)
                    .addComponent(tfHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoSesion)
                    .addComponent(cbTipoSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescripcion)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGuardar)
                    .addComponent(btVolver))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbTipoSesion, tfDescripcion, tfHoraComienzo, tfHoraFin});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        this.hide();
    }//GEN-LAST:event_btVolverActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        // TODO add your handling code here:
  
                                                   
    }//GEN-LAST:event_btGuardarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btVolver;
    private javax.swing.JComboBox cbTipoSesion;
    private calendario.DatePropertyEditorSupport datePropertyEditorSupport1;
    private calendario.DateTextField dateTextField1;
    private calendario.DateTextFieldBeanInfo dateTextFieldBeanInfo1;
    private calendario.DateTextFieldBeanInfo dateTextFieldBeanInfo2;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelFechaSesion;
    private javax.swing.JLabel labelHoraComienzo;
    private javax.swing.JLabel labelHoraFin;
    private javax.swing.JLabel labelTipoSesion;
    private javax.swing.JTextField tfDescripcion;
    private javax.swing.JTextField tfHoraComienzo;
    private javax.swing.JTextField tfHoraFin;
    // End of variables declaration//GEN-END:variables
}
