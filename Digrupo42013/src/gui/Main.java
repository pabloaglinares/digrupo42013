/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Gemo
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        
    }
    
    
    @Override
    public Image getIconImage() {
            Image retValue = Toolkit.getDefaultToolkit().
            getImage(ClassLoader.getSystemResource("resources/escalador_peq.png"));
            return retValue;
    }
    
     public void mostrarAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();
            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            // Pone ayuda a item de menu al pulsarlo.
            hb.enableHelpOnButton(miAyuda, "portada", helpset);
            hb.enableHelpKey(this.getRootPane(),"portada", helpset);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Se ha producido un error "
                    + "intentando mostrar la ayuda.\n" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE, null);
            e.printStackTrace();

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpEscritorio = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lRendimiento = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        mItinerarios = new javax.swing.JMenu();
        miAltaItinerario = new javax.swing.JMenuItem();
        miListadoItinerarios = new javax.swing.JMenuItem();
        mSesiones = new javax.swing.JMenu();
        miAltaSesion = new javax.swing.JMenuItem();
        miListadoSesiones = new javax.swing.JMenuItem();
        mInformes = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        mConfiguracion = new javax.swing.JMenu();
        miEditar = new javax.swing.JMenuItem();
        mAyuda = new javax.swing.JMenu();
        miAyuda = new javax.swing.JMenuItem();
        miAcerca = new javax.swing.JMenuItem();
        miSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Escalador");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));

        dpEscritorio.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rendimiento:");

        lRendimiento.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lRendimiento.setForeground(new java.awt.Color(255, 255, 255));
        lRendimiento.setText("0");

        javax.swing.GroupLayout dpEscritorioLayout = new javax.swing.GroupLayout(dpEscritorio);
        dpEscritorio.setLayout(dpEscritorioLayout);
        dpEscritorioLayout.setHorizontalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpEscritorioLayout.createSequentialGroup()
                .addContainerGap(408, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lRendimiento)
                .addContainerGap())
        );
        dpEscritorioLayout.setVerticalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpEscritorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lRendimiento)
                    .addComponent(jLabel1))
                .addContainerGap(416, Short.MAX_VALUE))
        );
        dpEscritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpEscritorio.setLayer(lRendimiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mItinerarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/itinerario.png"))); // NOI18N
        mItinerarios.setText("Itinerarios");

        miAltaItinerario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miAltaItinerario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        miAltaItinerario.setText("Alta Itinerario...");
        miAltaItinerario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAltaItinerarioActionPerformed(evt);
            }
        });
        mItinerarios.add(miAltaItinerario);

        miListadoItinerarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miListadoItinerarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lista.png"))); // NOI18N
        miListadoItinerarios.setText("Listado Itinerarios...");
        miListadoItinerarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miListadoItinerariosActionPerformed(evt);
            }
        });
        mItinerarios.add(miListadoItinerarios);

        menu.add(mItinerarios);

        mSesiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sesion.png"))); // NOI18N
        mSesiones.setText("Sesiones");

        miAltaSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miAltaSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        miAltaSesion.setText("Alta Sesión...");
        miAltaSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAltaSesionActionPerformed(evt);
            }
        });
        mSesiones.add(miAltaSesion);

        miListadoSesiones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miListadoSesiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lista.png"))); // NOI18N
        miListadoSesiones.setText("Listado Sesiones...");
        miListadoSesiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miListadoSesionesActionPerformed(evt);
            }
        });
        mSesiones.add(miListadoSesiones);

        menu.add(mSesiones);

        mInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/informe.png"))); // NOI18N
        mInformes.setText("Informes");

        jMenuItem6.setText("Crear Informes...");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mInformes.add(jMenuItem6);

        menu.add(mInformes);

        mConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/herramientas.png"))); // NOI18N
        mConfiguracion.setText("Configuracion");
        mConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mConfiguracionActionPerformed(evt);
            }
        });

        miEditar.setText("Editar escalador...");
        miEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditarActionPerformed(evt);
            }
        });
        mConfiguracion.add(miEditar);

        menu.add(mConfiguracion);

        mAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ayuda1.png"))); // NOI18N
        mAyuda.setText("Ayuda");

        miAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        miAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ayuda2.png"))); // NOI18N
        miAyuda.setText("Ayuda...");
        miAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAyudaActionPerformed(evt);
            }
        });
        mAyuda.add(miAyuda);

        miAcerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/acerca.png"))); // NOI18N
        miAcerca.setText("Acerca...");
        miAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAcercaActionPerformed(evt);
            }
        });
        mAyuda.add(miAcerca);

        miSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        miSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cerrar.png"))); // NOI18N
        miSalir.setText("Salir");
        miSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSalirActionPerformed(evt);
            }
        });
        mAyuda.add(miSalir);

        menu.add(mAyuda);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpEscritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAyudaActionPerformed
        // TODO add your handling code here:
        //mostrarAyuda();
    }//GEN-LAST:event_miAyudaActionPerformed

    private void miSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_miSalirActionPerformed

    private void miAltaItinerarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAltaItinerarioActionPerformed
        AltaItinerario alta = new AltaItinerario();
        dpEscritorio.add(alta);
        alta.show();
    }//GEN-LAST:event_miAltaItinerarioActionPerformed

    private void miAltaSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAltaSesionActionPerformed
        AltaSesion alta = new AltaSesion();
        dpEscritorio.add(alta);
        alta.show();
    }//GEN-LAST:event_miAltaSesionActionPerformed

    private void miAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAcercaActionPerformed
        Acerca acerca = new Acerca();
        dpEscritorio.add(acerca);
        acerca.show();
    }//GEN-LAST:event_miAcercaActionPerformed

    private void miListadoItinerariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListadoItinerariosActionPerformed
        // TODO add your handling code here:
        ListadoItinerarios listado = new ListadoItinerarios();
        dpEscritorio.add(listado);
        listado.show();
    }//GEN-LAST:event_miListadoItinerariosActionPerformed

    private void miListadoSesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListadoSesionesActionPerformed
        // TODO add your handling code here:
        ListadoSesiones listadosesiones = new ListadoSesiones();
        dpEscritorio.add(listadosesiones);
        listadosesiones.show();
    }//GEN-LAST:event_miListadoSesionesActionPerformed

    private void mConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mConfiguracionActionPerformed
        
    }//GEN-LAST:event_mConfiguracionActionPerformed

    private void miEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditarActionPerformed
        Configuracion config = new Configuracion();
        dpEscritorio.add(config);
        config.show();
    }//GEN-LAST:event_miEditarActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Informes informes = new Informes();
        dpEscritorio.add(informes);
        informes.show();
               
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dpEscritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JLabel lRendimiento;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenu mConfiguracion;
    private javax.swing.JMenu mInformes;
    private javax.swing.JMenu mItinerarios;
    private javax.swing.JMenu mSesiones;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem miAcerca;
    private javax.swing.JMenuItem miAltaItinerario;
    private javax.swing.JMenuItem miAltaSesion;
    private javax.swing.JMenuItem miAyuda;
    private javax.swing.JMenuItem miEditar;
    private javax.swing.JMenuItem miListadoItinerarios;
    private javax.swing.JMenuItem miListadoSesiones;
    private javax.swing.JMenuItem miSalir;
    // End of variables declaration//GEN-END:variables
}
