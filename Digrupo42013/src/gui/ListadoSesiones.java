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

public class ListadoSesiones extends javax.swing.JInternalFrame {

    /**
     * Creates new form Itinerarios
     */
    public ListadoSesiones() {

        initComponents();
        this.setIconifiable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        labelTipoSesion.setVisible(false);
        labelFecha1.setVisible(false);
        labelFecha2.setVisible(false);
        dtfFecha1.setVisible(false);
        dtfFecha2.setVisible(false);
        cbTipoSesion.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btVolver = new javax.swing.JButton();
        cbListadoSesiones = new javax.swing.JComboBox();
        btVer = new javax.swing.JButton();
        labelListadoSesiones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListado = new javax.swing.JTable();
        labelTipoSesion = new javax.swing.JLabel();
        cbTipoSesion = new javax.swing.JComboBox();
        dtfFecha1 = new calendario.DateTextField();
        dtfFecha2 = new calendario.DateTextField();
        labelFecha1 = new javax.swing.JLabel();
        labelFecha2 = new javax.swing.JLabel();

        setTitle("Listado sesiones");

        btVolver.setText("Volver");
        btVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverActionPerformed(evt);
            }
        });

        cbListadoSesiones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Rango de fechas", "Fecha", "Tipo" }));
        cbListadoSesiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListadoSesionesActionPerformed(evt);
            }
        });

        btVer.setText("Ver");
        btVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerActionPerformed(evt);
            }
        });

        labelListadoSesiones.setText("Listado sesiones");

        tbListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "SESIÓN", "FECHA INICIO", "FECHA FIN", "TIPO", "DESCRIPCIÓN"
            }
        ));
        jScrollPane1.setViewportView(tbListado);

        labelTipoSesion.setText("Tipo sesión");

        cbTipoSesion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Físico", "Rocódromo", "Roca" }));

        dtfFecha1.setText("21/01/2014");

        dtfFecha2.setText("21/01/2014");

        labelFecha1.setText("Fecha 1");

        labelFecha2.setText("Fecha 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbListadoSesiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelListadoSesiones))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btVer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipoSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTipoSesion))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFecha1))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFecha2)
                            .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVolver)
                    .addComponent(labelListadoSesiones)
                    .addComponent(labelTipoSesion)
                    .addComponent(labelFecha1)
                    .addComponent(labelFecha2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbListadoSesiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVer)
                    .addComponent(cbTipoSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtfFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtfFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        // TODO add your handling code here:
        this.hide();
    }//GEN-LAST:event_btVolverActionPerformed

    private void cbListadoSesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListadoSesionesActionPerformed
        // TODO add your handling code here:
        String opcion = (String) cbListadoSesiones.getSelectedItem();
        if (opcion.equalsIgnoreCase("Tipo")) {
            labelTipoSesion.setVisible(true);
            cbTipoSesion.setVisible(true);
            labelFecha1.setVisible(false);
            dtfFecha1.setVisible(false);
            labelFecha2.setVisible(false);
            dtfFecha2.setVisible(false);


        } else if (opcion.equalsIgnoreCase("Rango de fechas")) {
            labelTipoSesion.setVisible(false);
            cbTipoSesion.setVisible(false);
            labelFecha1.setVisible(true);
            dtfFecha1.setVisible(true);
            labelFecha2.setVisible(true);
            dtfFecha2.setVisible(true);

        } else if (opcion.equalsIgnoreCase("Fecha")) {
            labelTipoSesion.setVisible(false);
            cbTipoSesion.setVisible(false);
            labelFecha1.setVisible(true);
            dtfFecha1.setVisible(true);
            labelFecha2.setVisible(false);
            dtfFecha2.setVisible(false);

        } else {
            labelTipoSesion.setVisible(false);
            cbTipoSesion.setVisible(false);
            labelFecha1.setVisible(false);
            dtfFecha1.setVisible(false);
            labelFecha2.setVisible(false);
            dtfFecha2.setVisible(false);

        }
    }//GEN-LAST:event_cbListadoSesionesActionPerformed

    private void btVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btVerActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVer;
    private javax.swing.JButton btVolver;
    private javax.swing.JComboBox cbListadoSesiones;
    private javax.swing.JComboBox cbTipoSesion;
    private calendario.DateTextField dtfFecha1;
    private calendario.DateTextField dtfFecha2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFecha1;
    private javax.swing.JLabel labelFecha2;
    private javax.swing.JLabel labelListadoSesiones;
    private javax.swing.JLabel labelTipoSesion;
    private javax.swing.JTable tbListado;
    // End of variables declaration//GEN-END:variables
}
