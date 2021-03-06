/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import calendario.DateTextField;
import datos.pojos.Sesion;
import enlace_datos_gui.BridgeSesion;
import enlace_datos_gui.CheckCampo;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public final class AltaSesion extends javax.swing.JInternalFrame {

    /**
     * Este array almacena el estado de los campos, que será true si el
     * contenido es válido. La correspondencia con cada campo es la siguiente: 
     * <ul>
     * <li>0 tfHoraComienzo</li>
     * <li>1 tfHoraFin</li>
     * <li>2 tfDescripcion</li>
     * </ul>
     *   
     */
    private boolean[] camposCorrectos = {false, false, false};
    private BridgeSesion bridge = BridgeSesion.BRIDGE;
     //true si la ventana está abierta en modo alta, false en modo editar
    private volatile boolean modoAlta;
    /**
     * Creates new form AltaSesion
     */
    public AltaSesion() {
        initComponents();
        this.setIconifiable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.checkAll();
    }

    public boolean isModoAlta() {
        return modoAlta;
    }

    public void setModoAlta(boolean modoAlta) {
        this.modoAlta = modoAlta;
    }

    
    public JComboBox getCbTipoSesion() {
        return cbTipoSesion;
    }

    public DateTextField getDtfFecha() {
        return dtfFecha;
    }

    public JTextField getTfDescripcion() {
        return tfDescripcion;
    }

    public JTextField getTfHoraComienzo() {
        return tfHoraComienzo;
    }

    public JTextField getTfHoraFin() {
        return tfHoraFin;
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
        dtfFecha = new calendario.DateTextField();

        setTitle("Alta/Modif Sesiones");
        setMinimumSize(new java.awt.Dimension(300, 335));
        setPreferredSize(new java.awt.Dimension(300, 335));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        labelFechaSesion.setText("Fecha Sesión:");

        labelHoraComienzo.setText("Hora Comienzo:");

        tfHoraComienzo.setToolTipText("Inserte la hora en comienzo HH:mm");
        tfHoraComienzo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfHoraComienzoKeyReleased(evt);
            }
        });

        labelHoraFin.setText("Hora Fin:");

        tfHoraFin.setToolTipText("Inserte la hora fin en formato HH:mm");
        tfHoraFin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfHoraFinKeyReleased(evt);
            }
        });

        labelTipoSesion.setText("Tipo Sesión:");

        cbTipoSesion.setModel(new javax.swing.DefaultComboBoxModel(Sesion.TipoSesion.values()));
        cbTipoSesion.setToolTipText("Eliga entre Fisico, Rocodromo o Roca");

        labelDescripcion.setText("Descripción:");

        tfDescripcion.setToolTipText("Inserte la descripción");
        tfDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDescripcionKeyReleased(evt);
            }
        });

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
        btGuardar.setEnabled(false);

        dtfFecha.setText("dateTextField1");
        dtfFecha.setToolTipText("Inserte la fecha sesión en formato dd/MM/yyyy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(tfHoraComienzo)
                            .addComponent(tfHoraFin)
                            .addComponent(cbTipoSesion, 0, 141, Short.MAX_VALUE)
                            .addComponent(tfDescripcion)
                            .addComponent(dtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTipoSesion, tfDescripcion, tfHoraComienzo, tfHoraFin});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaSesion)
                    .addComponent(dtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGuardar)
                    .addComponent(btVolver))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbTipoSesion, tfDescripcion, tfHoraComienzo, tfHoraFin});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btVolverActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        bridge.saveSesion();
        if(modoAlta){
            cleanAll();
        } else {
            this.dispose();
        }


    }//GEN-LAST:event_btGuardarActionPerformed
    /**
     * maneja los eventos key released en el elemento tfHoraComienzo
     *
     * @param evt
     */
    private void tfHoraComienzoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHoraComienzoKeyReleased
        camposCorrectos[0] = CheckCampo.HORA_MINUTO.isCampoOk(tfHoraComienzo.getText());
        btGuardar.setEnabled(CheckCampo.allOk(camposCorrectos));
    }//GEN-LAST:event_tfHoraComienzoKeyReleased
   
   /**
     * Maneja los eventos key released en el elemento tfHoraFin
     *
     * @param evt
     */
    private void tfHoraFinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHoraFinKeyReleased
        camposCorrectos[1] = CheckCampo.HORA_MINUTO.isCampoOk(tfHoraFin.getText());
        btGuardar.setEnabled(CheckCampo.allOk(camposCorrectos));
    }//GEN-LAST:event_tfHoraFinKeyReleased
    /**
     * Maneja los eventos key released en el elemento tfDescripcion
     *
     * @param evt
     */
    private void tfDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDescripcionKeyReleased
        camposCorrectos[2] = CheckCampo.DESCRIPCION.isCampoOk(tfDescripcion.getText());
        btGuardar.setEnabled(CheckCampo.allOk(camposCorrectos));
    }//GEN-LAST:event_tfDescripcionKeyReleased

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        bridge.closeAlta();
    }//GEN-LAST:event_formInternalFrameClosed
    private void cleanAll() {
        Arrays.fill(camposCorrectos,0, camposCorrectos.length, false);
        tfHoraComienzo.setText("");
        tfHoraFin.setText("");
        tfDescripcion.setText("");
        cbTipoSesion.setSelectedIndex(0);
        dtfFecha.setDate(new Date());
        checkAll();
    }
    /**
     * Comprueba si todos los campos son correctos
     */
    public void checkAll(){
        camposCorrectos[0] = CheckCampo.HORA_MINUTO.isCampoOk(tfHoraComienzo.getText());
        camposCorrectos[1] = CheckCampo.HORA_MINUTO.isCampoOk(tfHoraFin.getText());
        camposCorrectos[2] = CheckCampo.DESCRIPCION.isCampoOk(tfDescripcion.getText());
        btGuardar.setEnabled(CheckCampo.allOk(camposCorrectos));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btVolver;
    private javax.swing.JComboBox cbTipoSesion;
    private calendario.DatePropertyEditorSupport datePropertyEditorSupport1;
    private calendario.DateTextFieldBeanInfo dateTextFieldBeanInfo1;
    private calendario.DateTextFieldBeanInfo dateTextFieldBeanInfo2;
    private calendario.DateTextField dtfFecha;
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
