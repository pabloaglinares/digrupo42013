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
public enum TipoCampo {
    /**
     * Para comprobar campos del tipo hh:mm
     */
    HORA_MINUTO("[0-23]:[0-59]"),
    /**
     * Para comprobar campos de tipo date en
     * formato día/mes/año
     */
    FECHA("[1-31]/[1-12]/\\d{2,4}"),
    /**
     * Para comprobar el campo de nombre del usuario
     */
    NOMBRE_CONFIGURACION("[A-Z|a-z|0-9]{1,50}"),
    /**
     * Para comprobar el campo de apellido del usuario
     */
    APELLIDOS("\"[A-Z|a-z|0-9]{1,100}\""),
    /**
     * Para comprobar el campo de nombre del itinerario
     */
    NOMBRE_ITINERARIO("\"[A-Z|a-z|0-9]{1,150}\""),
    /**
     * Para comprobar el campo de localización del itinerario
     */
    LOCALIZACION("\"[A-Z|a-z|0-9]{1,200}\""),
    /**
     * Para comprobar el campo de imágen del itinerario
     */
    IMAGEN("\"[A-Z|a-z|0-9]{1,200}\"");
            
    private String patron;

    private TipoCampo(String pat) {
        patron = pat;
    }
    /**
     * Devuelve true si el contenido del String concuerda con el patrón definido
     * para el campo
     * @param str
     * @return 
     */
    public boolean isCampoOk(String str) {
        return str.matches(patron);
    }
}
