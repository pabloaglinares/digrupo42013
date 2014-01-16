/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.pojos;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class Usuario {

    private String nombre;

    private String apellidos;

    private int rendimiento;

    /**
     * Get the value of rendimiento
     *
     * @return the value of rendimiento
     */
    public int getRendimiento() {
        return rendimiento;
    }

    /**
     * Set the value of rendimiento
     *
     * @param rendimiento new value of rendimiento
     */
    public void setRendimiento(int rendimiento) {
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
