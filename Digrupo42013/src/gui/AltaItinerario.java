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
public class AltaItinerario extends javax.swing.JInternalFrame {

    /**
     * Creates new form AltaItinerario
     */
    public AltaItinerario() {
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

        labelNombreItinerario = new javax.swing.JLabel();
        tfNombreItinerario = new javax.swing.JTextField();
        labelLocalizacion = new javax.swing.JLabel();
        tfLocalizacionItinerario = new javax.swing.JTextField();
        labelTipoItinerario = new javax.swing.JLabel();
        cbTipoItineracio = new javax.swing.JComboBox();
        labelDificultad = new javax.swing.JLabel();
        spDificultadNumero = new javax.swing.JSpinner();
        spDificultadLetra = new javax.swing.JSpinner();
        spDificultadMasMenos = new javax.swing.JSpinner();
        labelFechaResolucion = new javax.swing.JLabel();
        labelFoto = new javax.swing.JLabel();
        tfUrlFoto = new javax.swing.JTextField();
        btVolver = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        dtfFechaResolucion = new calendario.DateTextField();

        setTitle("Alta/Modif Itinerarios");
        setMinimumSize(new java.awt.Dimension(300, 335));
        setPreferredSize(new java.awt.Dimension(300, 335));

        labelNombreItinerario.setText("Nombre:");

        labelLocalizacion.setText("Localización:");

        labelTipoItinerario.setText("Tipo:");

        cbTipoItineracio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Via de escalada", "Boulder" }));

        labelDificultad.setText("Dificultad:");

        spDificultadLetra.setModel(new javax.swing.SpinnerListModel(new String[] {"a", "b", "c"}));

        spDificultadMasMenos.setModel(new javax.swing.SpinnerListModel(new String[] {" ", "-", "+"}));

        labelFechaResolucion.setText("Fecha Resolución:");

        labelFoto.setText("Foto:");

        btVolver.setText("Volver");
        btVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverActionPerformed(evt);
            }
        });

        btGuardar.setText("Guardar");

        dtfFechaResolucion.setText("dateTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFechaResolucion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelFoto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDificultad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTipoItinerario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLocalizacion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNombreItinerario, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spDificultadNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(spDificultadLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(spDificultadMasMenos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dtfFechaResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfUrlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoItineracio, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLocalizacionItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNombreItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btVolver)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTipoItineracio, dtfFechaResolucion, tfLocalizacionItinerario, tfNombreItinerario, tfUrlFoto});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombreItinerario)
                    .addComponent(tfNombreItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLocalizacion)
                    .addComponent(tfLocalizacionItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipoItinerario)
                    .addComponent(cbTipoItineracio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDificultad)
                    .addComponent(spDificultadNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spDificultadLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spDificultadMasMenos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaResolucion)
                    .addComponent(dtfFechaResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUrlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVolver)
                    .addComponent(btGuardar))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbTipoItineracio, dtfFechaResolucion, spDificultadLetra, spDificultadMasMenos, spDificultadNumero, tfLocalizacionItinerario, tfNombreItinerario, tfUrlFoto});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        this.hide();
    }//GEN-LAST:event_btVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btVolver;
    private javax.swing.JComboBox cbTipoItineracio;
    private calendario.DateTextField dtfFechaResolucion;
    private javax.swing.JLabel labelDificultad;
    private javax.swing.JLabel labelFechaResolucion;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelLocalizacion;
    private javax.swing.JLabel labelNombreItinerario;
    private javax.swing.JLabel labelTipoItinerario;
    private javax.swing.JSpinner spDificultadLetra;
    private javax.swing.JSpinner spDificultadMasMenos;
    private javax.swing.JSpinner spDificultadNumero;
    private javax.swing.JTextField tfLocalizacionItinerario;
    private javax.swing.JTextField tfNombreItinerario;
    private javax.swing.JTextField tfUrlFoto;
    // End of variables declaration//GEN-END:variables
}
