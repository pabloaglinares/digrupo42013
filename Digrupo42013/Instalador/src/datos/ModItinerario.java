
package datos;

/**
 * Representa el tipo de modificación realizada en un itinerario. Esta puede
 * ser:
 * <ul>
 * <li>TOTAL: se debe actualizar todo</li>
 * <li>SOLO_NOMBRE_Y_DIFICULTAD: solo se deben actualizar el nombre y la
 * dificultad. esta es la opción que debe elegirse en caso de que solo se
 * modifique uno de ellos.</li>
 * <li>SOLO_IMAGEN: solo se debe actualizar la imagen</li>
 * <li>SOLO_FECHAS: solo se deben actualizar las fechas de resolución, NO DEBE
 * USARSE PARA AÑADIR UNA FECHA DE RESOLUCIÓN, para eso se usa el método
 * insertaFechaItinerario de la clase LogicaDatos</li>
 * </ul>
 *
 * @author Andrés Traspuesto Lanza
 */
public enum ModItinerario {

    /**
     * TOTAL: se debe actualizar todo
     */
    TOTAL,
    /**
     * SOLO_IMAGEN: solo se debe actualizar la imagen
     */
    SOLO_IMAGEN,
    
    /**
     * SOLO_FECHAS: solo se deben actualizar las fechas de resolución, NO DEBE
     * USARSE PARA AÑADIR UNA FECHA DE RESOLUCIÓN, para eso se usa el método
     * insertaFechaItinerario de la clase LogicaDatos
     */
    SOLO_FECHAS,
    /**
     * SOLO_NOMBRE_LOCALIZACION_DIFICULTAD: solo se deben actualizar el nombre 
     * la localización y la dificultad. esta es la opción que debe elegirse en 
     * caso de que solo se modifique uno de ellos.
     */
    SOLO_NOMBRE_LOCALIZACION_DIFICULTAD;
}
