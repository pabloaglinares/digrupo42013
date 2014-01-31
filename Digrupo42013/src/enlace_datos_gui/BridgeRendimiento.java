/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enlace_datos_gui;

import datos.UtilesBD;
import javax.swing.JLabel;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeRendimiento {
    RENDIMIENTO;
    private final UtilesBD utiles = UtilesBD.INSTANCE;
    /**
     * Etiqueta donde debe mostrarse el rendimiento
     */
    private JLabel lblRendimiento;
    /**
     * Asigna la etiqueta donde se debe mostrar el rendimiento
     * @param lblRendimiento 
     */
    public void setLblRendimiento(JLabel lblRendimiento) {
        this.lblRendimiento = lblRendimiento;
    }
    /**
     * Escribe el rendimiento en el label correspondiente
     */
    public void setRendimiento() {
        lblRendimiento.setText(String.format("%.2f", utiles.calculaRendimiento()));
    }
    
}
