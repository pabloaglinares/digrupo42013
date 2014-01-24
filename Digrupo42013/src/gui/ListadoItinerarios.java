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


public class ListadoItinerarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form Itinerarios
     */
    public ListadoItinerarios() {
        
        initComponents();
        this.setIconifiable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        
        dtfFecha1.setVisible(false);
        dtfFecha2.setVisible(false);
        labelFecha1.setVisible(false);
        labelFecha2.setVisible(false);
        tbListado.setVisible(false);
        
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbListadoItinerarios = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btVer = new javax.swing.JButton();
        labelFecha1 = new javax.swing.JLabel();
        labelFecha2 = new javax.swing.JLabel();
        btVolver = new javax.swing.JButton();
        dtfFecha1 = new calendario.DateTextField();
        dtfFecha2 = new calendario.DateTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListado = new gui.tablaItinerario.TablaItinerarios();

        setTitle("Listado itinerarios");
        setMinimumSize(new java.awt.Dimension(575, 380));
        setPreferredSize(new java.awt.Dimension(575, 380));

        cbListadoItinerarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Dificultad", "Rango de fechas", "Fecha" }));
        cbListadoItinerarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListadoItinerariosActionPerformed(evt);
            }
        });

        jLabel1.setText("Listado itinerarios");

        btVer.setText("Ver");
        btVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerActionPerformed(evt);
            }
        });

        labelFecha1.setText("Fecha 1");

        labelFecha2.setText("Fecha 2");

        btVolver.setText("Volver");
        btVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverActionPerformed(evt);
            }
        });

        dtfFecha1.setText("21/01/2014");

        dtfFecha2.setText("21/01/2014");

        tbListado.setRowHeight(25);
        jScrollPane2.setViewportView(tbListado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btVolver)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cbListadoItinerarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(63, 63, 63)
                                    .addComponent(btVer, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1))
                            .addGap(53, 53, 53)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelFecha1)
                                .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(54, 54, 54)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelFecha2)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFecha1)
                    .addComponent(labelFecha2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbListadoItinerarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVer)
                    .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btVolver)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbListadoItinerariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListadoItinerariosActionPerformed
        // TODO add your handling code here:
        String opcion = (String) cbListadoItinerarios.getSelectedItem();
        if (opcion.equalsIgnoreCase("Fecha") ) {
            labelFecha1.setVisible(true);
            dtfFecha1.setVisible(true);
            labelFecha2.setVisible(false);
            dtfFecha2.setVisible(false);
        }else if (opcion.equalsIgnoreCase("Rango de fechas") ) {
            labelFecha1.setVisible(true);
            dtfFecha1.setVisible(true);
            labelFecha2.setVisible(true);
           dtfFecha2.setVisible(true);
        }
        else{
            labelFecha1.setVisible(false);
            dtfFecha1.setVisible(false);
            labelFecha2.setVisible(false);
            dtfFecha2.setVisible(false);
        }
    }//GEN-LAST:event_cbListadoItinerariosActionPerformed

    private void btVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerActionPerformed
        // TODO add your handling code here:
   
   
     
    }//GEN-LAST:event_btVerActionPerformed

    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        // TODO add your handling code here:
         this.hide();
    }//GEN-LAST:event_btVolverActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVer;
    private javax.swing.JButton btVolver;
    private javax.swing.JComboBox cbListadoItinerarios;
    private calendario.DateTextField dtfFecha1;
    private calendario.DateTextField dtfFecha2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelFecha1;
    private javax.swing.JLabel labelFecha2;
    private gui.tablaItinerario.TablaItinerarios tbListado;
    // End of variables declaration//GEN-END:variables
}
