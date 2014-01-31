/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 * Contiene la configuración de la aplicación, datos del usuario y periodo
 * 
 * @author Andrés Traspuesto Lanza
 */
public class Configuracion implements Serializable{

    private String nombre;

    private String apellidos;
    private Date fecha1Intervalo;
    private Date fecha2Intervalo;
    private float rendimiento;

    /**
     * Constructor vacío
     */
    public Configuracion() {
        super();
    }
    
    
    /**
     * Contructor con todos los parámetros
     * @param nombre
     * @param apellidos
     * @param fecha1Intervalo
     * @param fecha2Intervalo
     * @param rendimiento 
     */
    public Configuracion(String nombre, String apellidos, Date fecha1Intervalo, Date fecha2Intervalo, float rendimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha1Intervalo = fecha1Intervalo;
        this.fecha2Intervalo = fecha2Intervalo;
        this.rendimiento = rendimiento;
    }

    
    
    public Date getFecha1Intervalo() {
        return fecha1Intervalo;
    }

    public void setFecha1Intervalo(Date fecha1Intervalo) {
        this.fecha1Intervalo = fecha1Intervalo;
    }

    public Date getFecha2Intervalo() {
        return fecha2Intervalo;
    }

    public void setFecha2Intervalo(Date fecha2Intervalo) {
        this.fecha2Intervalo = fecha2Intervalo;
    }

    /**
     * Get the value of rendimiento
     *
     * @return the value of rendimiento
     */
    public float getRendimiento() {
        return rendimiento;
    }

    /**
     * Set the value of rendimiento
     *
     * @param rendimiento new value of rendimiento
     */
    public void setRendimiento(float rendimiento) {
        this.rendimiento = rendimiento;
    }

    /**
     * Get the value of apellidos
     *
     * @return the value of apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Set the value of apellidos
     *
     * @param apellidos new value of apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
