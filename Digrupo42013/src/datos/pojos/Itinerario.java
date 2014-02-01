/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.pojos;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Contiene los datos de itinerarios de escalada.
 * <ul>
 * <li>pItinerario (int): contiene la clave primaria del itinerario en la BD,
 * solo toma valor positivo si ya ha sido almacenado en esta</li>
 * <li>nombre (String): contiene el nombre del itinerario</li>
 * <li>dificultad (String): contiene el código representativo de la dificultad
 * del itinerario</li>
 * <li>pathImagen (File): es la ruta donde se almacena la imágen, si el
 * itinerario ya ha sido almacenado en la base de datos el archivo se encontrará
 * en la carpeta imagenes del directorio de la aplicación</li>
 * <li>fechasResolucion (List&lt;Date&gt;): es la lista de las fechas en las que
 * se ha resuelto el itinerario</li>
 * </ul>
 *
 * @author Andrés Traspuesto Lanza
 */
public class Itinerario implements Serializable {

    private int pItinerario;

    private String nombre;

    private String localizacion;

    private String difucultad;

    private File pathImagen;
    private String tipo;

    private final List<Date> fechasResolucion = new ArrayList<>();

    /**
     * Constructor vacío
     */
    public Itinerario() {
        super();
    }

    /**
     * Constructor con parámetros,
     *
     * @param nombre
     * @param localizacion
     * @param difucultad
     * @param pathImagen
     */
    public Itinerario(String nombre, String localizacion, String tipo, String difucultad, File pathImagen) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.difucultad = difucultad;
        this.pathImagen = pathImagen;
        this.tipo = tipo;
    }

    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Añade una fecha la lista de fechas de resolución del itinerario
     *
     * @param fecha
     */
    public void addFechaResolucion(Date fecha) {
        fechasResolucion.add(fecha);
    }

    /**
     * Añade una lista de fechas a la lista de fechas de resolución del
     * itinerario
     *
     * @param fechas
     */
    public void addAllFechas(List<Date> fechas) {
        fechasResolucion.addAll(fechas);
    }

    /**
     * Devuelve una copia inmutable de la lista de fechas de resolución
     *
     * @return the value of fechasResolucion
     */
    public List<Date> getFechasResolucion() {
        return fechasResolucion;
    }

    /**
     * Devuelve la localización
     *
     * @return
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Asigna la localización
     *
     * @param localizacion
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Get the value of pathImagen
     *
     * @return the value of pathImagen
     */
    public File getPathImagen() {
        return pathImagen;
    }

    /**
     * Set the value of pathImagen
     *
     * @param pathImagen new value of pathImagen
     */
    public void setPathImagen(File pathImagen) {
        this.pathImagen = pathImagen;
    }

    /**
     * Get the value of difucultad
     *
     * @return the value of difucultad
     */
    public String getDifucultad() {
        return difucultad;
    }

    /**
     * Set the value of difucultad
     *
     * @param difucultad new value of difucultad
     */
    public void setDifucultad(String difucultad) {
        this.difucultad = difucultad;
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

    /**
     * Get the value of pItinerario
     *
     * @return the value of pItinerario
     */
    public int getpItinerario() {
        return pItinerario;
    }

    /**
     * Set the value of pItinerario
     *
     * @param pItinerario new value of pItinerario
     */
    public void setpItinerario(int pItinerario) {
        this.pItinerario = pItinerario;
    }

}
