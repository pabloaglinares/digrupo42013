
package datos.logica;

/**
 * Define los operadores lógicos.
 * <ul>
 * <li>GT: mayor</li>
 * <li>GE: mayor o igual</li>
 * <li>E: igual</li>
 * <li>LE: menor o igual</li>
 * <li>LT: menor</li>
 * <li>NE: distinto</li>
 * </ul>
 *
 * @author Andrés Traspuesto Lanza
 */
public enum Comparador {

    /**
     * GT: mayor
     */
    GT(">"),
    /**
     * GE: mayor o igual
     */
    GE(">="), 
    /**
     * E: igual
     */
    E("="), 
    /**
     * LE: menor o igual
     */
    LE("<="), 
    /**
     * LT: menor
     */
    LT("<"), 
    /**
     * NE: distinto
     */
    NE("!=");
    String operator;

    private Comparador(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }

}
