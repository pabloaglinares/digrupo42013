/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

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
        dtfFechaSesion = new calendario.DateTextField();

        setTitle("Alta/Modif Sesiones");

        labelFechaSesion.setText("Fecha Sesión:");

        labelHoraComienzo.setText("Hora Comienzo:");

        labelHoraFin.setText("Hora Fin:");

        labelTipoSesion.setText("Tipo Sesión:");

        cbTipoSesion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Físico", "Rocódromo", "Roca" }));

        labelDescripcion.setText("Descripción:");

        btVolver.setText("Volver");

        btGuardar.setText("Guardar");

        dtfFechaSesion.setText("dateTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescripcion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTipoSesion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelHoraFin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelHoraComienzo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelFechaSesion, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtfFechaSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfHoraComienzo)
                            .addComponent(tfHoraFin)
                            .addComponent(cbTipoSesion, 0, 141, Short.MAX_VALUE)
                            .addComponent(tfDescripcion))))
                .addGap(23, 23, 23))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTipoSesion, dtfFechaSesion, tfDescripcion, tfHoraComienzo, tfHoraFin});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaSesion)
                    .addComponent(dtfFechaSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGuardar)
                    .addComponent(btVolver))
                .addGap(23, 23, 23))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbTipoSesion, dtfFechaSesion, tfDescripcion, tfHoraComienzo, tfHoraFin});

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btVolver;
    private javax.swing.JComboBox cbTipoSesion;
    private calendario.DateTextField dtfFechaSesion;
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
