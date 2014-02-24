/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import calendario.CambioFechaListener;
import enlace_datos_gui.BridgeItinerario;
import gui.tablaItinerario.TablaItinerarios;
import javax.swing.JTabbedPane;


public class ListadoItinerarios extends javax.swing.JInternalFrame {
private BridgeItinerario bridge = BridgeItinerario.BRIDGE;
    /**
     * Creates new form Itinerarios
     */
    public ListadoItinerarios() {
        
        initComponents();
        this.setIconifiable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        
        bridge.addTablaItinerario(tbListado);
        bridge.loadAllItinerario();
        
    }

    public TablaItinerarios getTbListado() {
        return tbListado;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListado = new gui.tablaItinerario.TablaItinerarios();
        tbpFiltroBusqueda = new javax.swing.JTabbedPane();
        pnlVacio = new javax.swing.JPanel();
        pnlRangoFechas = new gui.PanelRangoFechas();

        setResizable(true);
        setTitle("Listado itinerarios");
        setMinimumSize(new java.awt.Dimension(575, 380));

        btVolver.setText("Volver");
        btVolver.setToolTipText("Botón volver");
        btVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverActionPerformed(evt);
            }
        });

        tbListado.setRowHeight(25);
        tbListado.getColumnModel().getColumn(2).setMaxWidth(100);
        tbListado.getColumnModel().getColumn(3).setMaxWidth(30);
        tbListado.getColumnModel().getColumn(4).setMaxWidth(30);
        tbListado.setToolTipText("Carga los datos de los itinerarios");
        jScrollPane2.setViewportView(tbListado);

        tbpFiltroBusqueda.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpFiltroBusquedaStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlVacioLayout = new javax.swing.GroupLayout(pnlVacio);
        pnlVacio.setLayout(pnlVacioLayout);
        pnlVacioLayout.setHorizontalGroup(
            pnlVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        pnlVacioLayout.setVerticalGroup(
            pnlVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        tbpFiltroBusqueda.addTab("Ver todos", pnlVacio);

        pnlRangoFechas.addCambioFechaListener(new CambioFechaListener(){
            @Override
            public void onCambioFecha() {
                bridge.loadByRange(pnlRangoFechas.getFecha1(), pnlRangoFechas.getFecha2());
            }
        });
        tbpFiltroBusqueda.addTab("Ver por fecha", pnlRangoFechas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbpFiltroBusqueda)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbpFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btVolver)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción generada al puslar sobre volver
     * @param evt 
     */
    private void btVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverActionPerformed
        bridge.closeLista();
    }//GEN-LAST:event_btVolverActionPerformed
    
    private void tbpFiltroBusquedaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpFiltroBusquedaStateChanged
        JTabbedPane tab = (JTabbedPane) evt.getSource();
        bridge.setCurrentTab(tab.getSelectedIndex());
        switch (tab.getSelectedIndex()) {
            case 0:
                bridge.loadAllItinerario();
                break;
            case 1:
                bridge.loadByRange(pnlRangoFechas.getFecha1(), pnlRangoFechas.getFecha2());
                break;
        }
    }//GEN-LAST:event_tbpFiltroBusquedaStateChanged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVolver;
    private javax.swing.JScrollPane jScrollPane2;
    private gui.PanelRangoFechas pnlRangoFechas;
    private javax.swing.JPanel pnlVacio;
    private gui.tablaItinerario.TablaItinerarios tbListado;
    private javax.swing.JTabbedPane tbpFiltroBusqueda;
    // End of variables declaration//GEN-END:variables
}
