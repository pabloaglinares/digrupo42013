/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListado = new javax.swing.JTable();
        btVolver = new javax.swing.JButton();
        dtfFecha1 = new calendario.DateTextField();
        dtfFecha2 = new calendario.DateTextField();

        setTitle("Listado itinerarios");

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

        tbListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ITINERARIO", "NOMBRE", "LOCALIZACION", "DIFICULTAD", "IMAGEN"
            }
        ));
        jScrollPane1.setViewportView(tbListado);

        btVolver.setText("Volver");
        btVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverActionPerformed(evt);
            }
        });

        dtfFecha1.setText("21/01/2014");

        dtfFecha2.setText("21/01/2014");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbListadoItinerarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btVer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFecha1)
                            .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFecha2)
                            .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btVolver)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelFecha1)
                        .addComponent(labelFecha2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbListadoItinerarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVer)
                    .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFecha1;
    private javax.swing.JLabel labelFecha2;
    private javax.swing.JTable tbListado;
    // End of variables declaration//GEN-END:variables
}