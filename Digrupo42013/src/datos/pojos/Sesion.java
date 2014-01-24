/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 * Contiene los datos de la sesión de entrenamiento.
 * <ul>
 * <li>pSesion (int): clave primaria de la sesión en la base de datos, solo
 * toma valor mayor que cero cuando ya ha sido almacenado en la BD</li>
 * <li>fecha_hora1 (Date): fecha y hora a la que se inicia la sesión</li>
 * <li>fecha_hora2 (Date): fecha y hora a la que termina la sesión</li>
 * <li>descripcion (String): descripción de la sesión</li>
 * <li>tipo (TipoSesion): enum que puede tomar los valores rocódromo, físico y
 * roca</li>
 * </ul>
 *
 * @author Andrés Traspuesto Lanza
 */
public class Sesion implements Serializable{

    /**
     * Tipos posibles de sesiones de entrenamiento
     */
    public enum TipoSesion {

        FISICO("Físico"), ROCODROMO("Rocódromo"), ROCA("Roca");
        private String nombre;

        private TipoSesion(String nombre) {
            this.nombre = nombre;
        }

        /**
         * Devuelve el nombre del tipo de sesión. Se ha implementado por el tema
         * de los acentos
         *
         * @return
         */
        public String getNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
        

    }
    private Date fecha_hora1;

    private Date fecha_hora2;

    private String descripcion;

    private TipoSesion tipo;

    private int pSesion = Integer.MIN_VALUE;
    /**
     * Constructor sin parámetros
     */
    public Sesion() {
        super();
    }
    
    /**
     * Constructor con todos los parámetros, es el que se debe usar para cargar
     * sesiones desde la base de datos
     * @param fecha_hora1
     * @param fecha_hora2
     * @param descripcion
     * @param tipo
     * @param pSesion 
     */
    public Sesion(Date fecha_hora1, Date fecha_hora2, String descripcion, TipoSesion tipo, int pSesion) {
        this.fecha_hora1 = fecha_hora1;
        this.fecha_hora2 = fecha_hora2;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.pSesion = pSesion;
    }
    /**
     * Constructor sin el parámetro de pSesion, es el que se debe usar para crear
     * una sesión mediante interacción con el usuario a través del formulario 
     * de altas
     * @param fecha_hora1
     * @param fecha_hora2
     * @param descripcion
     * @param tipo 
     */
    public Sesion(Date fecha_hora1, Date fecha_hora2, String descripcion, TipoSesion tipo) {
        this.fecha_hora1 = fecha_hora1;
        this.fecha_hora2 = fecha_hora2;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }
    
    
    /**
     * Get the value of pSesion
     *
     * @return the value of pSesion
     */
    public int getpSesion() {
        return pSesion;
    }

    /**
     * Set the value of pSesion
     *
     * @param pSesion new value of pSesion
     */
    public void setpSesion(int pSesion) {
        this.pSesion = pSesion;
    }

    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public TipoSesion getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(TipoSesion tipo) {
        this.tipo = tipo;
    }

    /**
     * Get the value of descripcion
     *
     * @return the value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the value of descripcion
     *
     * @param descripcion new value of descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Get the value of fecha_hora2
     *
     * @return the value of fecha_hora2
     */
    public Date getFecha_hora2() {
        return fecha_hora2;
    }

    /**
     * Set the value of fecha_hora2
     *
     * @param fecha_hora2 new value of fecha_hora2
     */
    public void setFecha_hora2(Date fecha_hora2) {
        this.fecha_hora2 = fecha_hora2;
    }

    /**
     * Get the value of fecha_hora1
     *
     * @return the value of fecha_hora1
     */
    public Date getFecha_hora1() {
        return fecha_hora1;
    }

    /**
     * Set the value of fecha_hora1
     *
     * @param fecha_hora1 new value of fecha_hora1
     */
    public void setFecha_hora1(Date fecha_hora1) {
        this.fecha_hora1 = fecha_hora1;
    }

}
