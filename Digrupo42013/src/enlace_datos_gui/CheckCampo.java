/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enlace_datos_gui;

/**
 * Este singleton sirve para comprobar si el campo es correcto
 * @author Andrés Traspuesto Lanza
 */
public enum CheckCampo {
    /**
     * Para comprobar campos del tipo hh:mm
     */
    HORA_MINUTO("[0-23]?[0-9]:[0-5][0-9]"),
    /**
     * Para comprobar campos de tipo date en
     * formato día/mes/año
     */
    FECHA("[1-3]?[0-9]/[0-1]?[0-9]/\\d{2,4}"),
    /**
     * Para comprobar el campo de nombre del usuario
     */
    NOMBRE_CONFIGURACION("(\\w|\\s){1,50}"),
    /**
     * Para comprobar el campo de apellido del usuario
     */
    APELLIDOS("(\\w|\\s){1,100}"),
    /**
     * Para comprobar el campo de nombre del itinerario
     */
    NOMBRE_ITINERARIO("(\\w|\\s){1,150}"),
    /**
     * Para comprobar el campo de localización del itinerario
     */
    LOCALIZACION("(\\w|\\s){1,200}"),
    /**
     * Para comprobar el campo de imágen del itinerario
     */
    IMAGEN("(\\w|\\s){1,200}"),
    /**
     * Para comprobar el campo de descripción de la sesión
     */
    DESCRIPCION("(\\w|\\s){1,200}");
            
    private String patron;

    private CheckCampo(String pat) {
        patron = pat;
    }
    /**
     * Devuelve true si el contenido del String concuerda con el patrón definido
     * para el campo
     * @param str
     * @return 
     */
    public boolean isCampoOk(String str) {
        System.out.println(patron+" encaja? "+str+" = "+str.matches(patron));
        return str.matches(patron);
    }
    
    /**
     * Devuelve true si todos los elementos del array son true
     * @param campos
     * @return 
     */
    public static boolean allOk(boolean[] campos) {
        boolean ok = true;
        for(boolean isOk: campos) ok = ok && isOk;
        return ok;
    }
}
