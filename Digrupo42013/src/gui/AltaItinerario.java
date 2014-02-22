
package gui;

import calendario.DateTextField;
import enlace_datos_gui.BridgeItinerario;
import enlace_datos_gui.CheckCampo;
import gui.tablaFechas.FechasTableModel;
import gui.tablaFechas.TablaFechas;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class AltaItinerario extends javax.swing.JInternalFrame {
    
    private boolean modoAlta;
    private BridgeItinerario bridge = BridgeItinerario.BRIDGE;
    /**
     * El elmento 0 contiene true si el campo nombre es válido, el elemento 1
     * contiene true si la localización es válida
     */
    private boolean[] camposOk = new boolean[2];
    private File imagen = new File("imagenes/sinImagen.jpg");
    /**
     * <ul>
     * <li>0: nombre</li>
     * <li>1: localizacion</li>
     * <li>2: tipo</li>
     * <li>3: dificultad</li>
     * <li>4: fechas</li>
     * <li>5: imagen</li>
     * </ul>
     */
    private boolean[] camposModificados = new boolean[6];

    /**
     * Creates new form ModificacionItinerario
     */
    public AltaItinerario() {
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

        btnLoadImg = new javax.swing.JButton();
        btVolver = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        edpImagen = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        spDificultadNumero = new javax.swing.JSpinner();
        labelNombreItinerario = new javax.swing.JLabel();
        tfNombreItinerario = new javax.swing.JTextField();
        labelTipoItinerario = new javax.swing.JLabel();
        spDificultadMasMenos = new javax.swing.JSpinner();
        spDificultadLetra = new javax.swing.JSpinner();
        labelLocalizacion = new javax.swing.JLabel();
        labelDificultad = new javax.swing.JLabel();
        cbTipoItineracio = new javax.swing.JComboBox();
        tfLocalizacion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAddDate = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblFechas = new gui.tablaFechas.TablaFechas();
        dtfNewDate = new calendario.DateTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(760, 460));
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

        btnLoadImg.setText("Seleccionar imagen");
        btnLoadImg.setToolTipText("Botón para selecccionar  la imagen");
        btnLoadImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadImgActionPerformed(evt);
            }
        });

        btVolver.setText("Volver");
        btVolver.setToolTipText("Botón para volver");
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

        edpImagen.setEditable(false);
        edpImagen.setContentType("text/html"); // NOI18N
        edpImagen.setText("");
        recargaImagen();
        edpImagen.setToolTipText("Carga Imagen");
        edpImagen.setMaximumSize(new java.awt.Dimension(324, 324));
        edpImagen.setMinimumSize(new java.awt.Dimension(324, 324));
        edpImagen.setPreferredSize(new java.awt.Dimension(324, 324));
        jScrollPane2.setViewportView(edpImagen);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Itinerario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.darkGray));
        jPanel1.setToolTipText("Datos del itinerario");

        spDificultadNumero.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spDificultadNumero.setToolTipText("Dificultad");
        spDificultadNumero.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spDificultadNumeroStateChanged(evt);
            }
        });

        labelNombreItinerario.setText("Nombre:");

        tfNombreItinerario.setToolTipText("Inserte el nombre");
        tfNombreItinerario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNombreItinerarioKeyReleased(evt);
            }
        });

        labelTipoItinerario.setText("Tipo:");

        spDificultadMasMenos.setModel(new javax.swing.SpinnerListModel(new String[] {" -", "+", " "}));
        spDificultadMasMenos.setToolTipText("Dificultad");
        spDificultadMasMenos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spDificultadMasMenosStateChanged(evt);
            }
        });

        spDificultadLetra.setModel(new javax.swing.SpinnerListModel(new String[] {"a", "b", "c"}));
        spDificultadLetra.setToolTipText("Dificultad");
        spDificultadLetra.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spDificultadLetraStateChanged(evt);
            }
        });

        labelLocalizacion.setText("Localización:");

        labelDificultad.setText("Dificultad:");

        cbTipoItineracio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Via de escalada", "Boulder" }));
        cbTipoItineracio.setToolTipText("Eliga Via de escalada o Boulder");
        cbTipoItineracio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItineracioItemStateChanged(evt);
            }
        });

        tfLocalizacion.setToolTipText("Inserte la localización");
        tfLocalizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfLocalizacionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombreItinerario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelLocalizacion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTipoItinerario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDificultad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfNombreItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTipoItineracio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(spDificultadNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(spDificultadLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spDificultadMasMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tfLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTipoItineracio, tfLocalizacion, tfNombreItinerario});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombreItinerario)
                    .addComponent(tfNombreItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLocalizacion)
                    .addComponent(tfLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoItinerario)
                    .addComponent(cbTipoItineracio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spDificultadNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spDificultadLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spDificultadMasMenos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDificultad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbTipoItineracio, tfLocalizacion, tfNombreItinerario});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fechas de resolución", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.darkGray));

        btnAddDate.setText("Añadir fecha");
        btnAddDate.setToolTipText("Botón para añadir fecha");
        btnAddDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDateActionPerformed(evt);
            }
        });

        tblFechas.setRowHeight(30);
        tblFechas.getColumnModel().getColumn(1).setMaxWidth(30);
        tblFechas.getColumnModel().getColumn(2).setMaxWidth(30);
        tblFechas.setToolTipText("Tabla que carga las fechas añadidas");
        jScrollPane3.setViewportView(tblFechas);

        dtfNewDate.setToolTipText("Inserte la fecha de resolucion para añadir los itinerarios");
        dtfNewDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtfNewDateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dtfNewDate, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddDate)
                    .addComponent(dtfNewDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLoadImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btGuardar, btVolver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoadImg))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVolver)
                    .addComponent(btGuardar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadImgActionPerformed
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            imagen = fc.getSelectedFile();
            recargaImagen();
            
        }
    }//GEN-LAST:event_btnLoadImgActionPerformed
    
    public boolean isModoAlta() {
        return modoAlta;
    }
    
    public void setModoAlta(boolean modoAlta) {
        this.modoAlta = modoAlta;
    }
    
    public JComboBox getCbTipoItineracio() {
        return cbTipoItineracio;
    }
    
    public File getImagen() {
        return imagen;
    }
    
    public void setImagen(File imagen) {
        this.imagen = imagen;
        recargaImagen();
    }
    
    public DateTextField getDtfNewDate() {
        return dtfNewDate;
    }
    
    public void setDtfNewDate(DateTextField dtfNewDate) {
        this.dtfNewDate = dtfNewDate;
    }
    
    public JSpinner getSpDificultadLetra() {
        return spDificultadLetra;
    }
    
    public void setSpDificultadLetra(JSpinner spDificultadLetra) {
        this.spDificultadLetra = spDificultadLetra;
    }
    
    public JSpinner getSpDificultadMasMenos() {
        return spDificultadMasMenos;
    }
    
    public void setSpDificultadMasMenos(JSpinner spDificultadMasMenos) {
        this.spDificultadMasMenos = spDificultadMasMenos;
    }
    
    public JSpinner getSpDificultadNumero() {
        return spDificultadNumero;
    }
    
    public void setSpDificultadNumero(JSpinner spDificultadNumero) {
        this.spDificultadNumero = spDificultadNumero;
    }
    
    public TablaFechas getTblFechas() {
        return tblFechas;
    }
    
    public void setTblFechas(TablaFechas tblFechas) {
        this.tblFechas = tblFechas;
    }
    
    public JTextField getTfLocalizacion() {
        return tfLocalizacion;
    }
    
    public void setTfLocalizacion(JTextField tfLocalizacion) {
        this.tfLocalizacion = tfLocalizacion;
    }
    
    public JTextField getTfNombreItinerario() {
        return tfNombreItinerario;
    }
    
    public void setTfNombreItinerario(JTextField tfNombreItinerario) {
        this.tfNombreItinerario = tfNombreItinerario;
    }
    
    public boolean[] getCamposModificados() {
        return camposModificados;
    }
    
    public void setCamposModificados(boolean[] camposModificados) {
        this.camposModificados = camposModificados;
    }

    /**
     * Redibuja la imagen
     */
    private void recargaImagen() {
        edpImagen.setText("<html >"
                + "<head></head>"
                + "<body style=\"padding:0;margin:0\">"
                + "<img src = \"file:" + imagen.getAbsolutePath() + "\"  height=\"295\" width=\"340\" style=\"margin:0 0\"/>"
                + "</body>\n</html>\n");
    }

    /**
     * Restaura el estado de los campos a los valores iniciales
     */
    private void cleanAll() {
        Arrays.fill(camposOk, false);
        imagen = new File("imagenes/sinImagen.jpg");
        recargaImagen();
        tfLocalizacion.setText("");
        tfNombreItinerario.setText("");
        cbTipoItineracio.setSelectedIndex(0);
        dtfNewDate.setDate(new Date());
        tblFechas.getModel().setDates(new ArrayList<Date>());
        Arrays.fill(camposModificados, false);
    }

    /**
     * Comprueba el estado de los campos y asigna el estado al botón guardar
     */
    public void checkAll() {
        camposOk[0] = CheckCampo.NOMBRE_ITINERARIO.isCampoOk(tfNombreItinerario.getText());
        camposOk[1] = CheckCampo.LOCALIZACION.isCampoOk(tfLocalizacion.getText());
        btGuardar.setEnabled(CheckCampo.allOk(camposOk));
    }
    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btVolverActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        bridge.saveItinerario();
        if (modoAlta) {
            cleanAll();
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_btGuardarActionPerformed

    private void tfNombreItinerarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNombreItinerarioKeyReleased
        camposOk[0] = CheckCampo.NOMBRE_ITINERARIO.isCampoOk(tfNombreItinerario.getText());
        btGuardar.setEnabled(CheckCampo.allOk(camposOk));
        camposModificados[0] = true;
    }//GEN-LAST:event_tfNombreItinerarioKeyReleased

    private void tfLocalizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLocalizacionKeyReleased
        camposOk[1] = CheckCampo.LOCALIZACION.isCampoOk(tfLocalizacion.getText());
        btGuardar.setEnabled(CheckCampo.allOk(camposOk));
        camposModificados[1] = true;
    }//GEN-LAST:event_tfLocalizacionKeyReleased

    private void btnAddDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDateActionPerformed
        if (((FechasTableModel) tblFechas.getModel()).containsFecha(dtfNewDate.getDate())) {
            JOptionPane.showMessageDialog(rootPane, "La fecha ya está registrada", "Error fecha duplicada", JOptionPane.ERROR_MESSAGE);
        } else {
            tblFechas.getModel().addDate(dtfNewDate.getDate());
            camposModificados[4] = true;
        }
    }//GEN-LAST:event_btnAddDateActionPerformed

    private void cbTipoItineracioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItineracioItemStateChanged
        camposModificados[2] = true;
    }//GEN-LAST:event_cbTipoItineracioItemStateChanged

    private void spDificultadNumeroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spDificultadNumeroStateChanged
        camposModificados[3] = true;
    }//GEN-LAST:event_spDificultadNumeroStateChanged

    private void spDificultadLetraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spDificultadLetraStateChanged
        camposModificados[3] = true;
    }//GEN-LAST:event_spDificultadLetraStateChanged

    private void spDificultadMasMenosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spDificultadMasMenosStateChanged
        camposModificados[3] = true;
    }//GEN-LAST:event_spDificultadMasMenosStateChanged

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        bridge.closeAlta();
    }//GEN-LAST:event_formInternalFrameClosed

    private void dtfNewDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtfNewDateMouseClicked
        
    }//GEN-LAST:event_dtfNewDateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btVolver;
    private javax.swing.JButton btnAddDate;
    private javax.swing.JButton btnLoadImg;
    private javax.swing.JComboBox cbTipoItineracio;
    private calendario.DateTextField dtfNewDate;
    private javax.swing.JEditorPane edpImagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelDificultad;
    private javax.swing.JLabel labelLocalizacion;
    private javax.swing.JLabel labelNombreItinerario;
    private javax.swing.JLabel labelTipoItinerario;
    private javax.swing.JSpinner spDificultadLetra;
    private javax.swing.JSpinner spDificultadMasMenos;
    private javax.swing.JSpinner spDificultadNumero;
    private gui.tablaFechas.TablaFechas tblFechas;
    private javax.swing.JTextField tfLocalizacion;
    private javax.swing.JTextField tfNombreItinerario;
    // End of variables declaration//GEN-END:variables
}
