

package gui.tablasesion;

import datos.pojos.Sesion;
import javax.swing.JButton;

/**
 * Wrapper e objetos Sesion que permite mostrarlos en una tabla con botones
 * de editar y eliminar
 * @author Andrés Traspuesto Lanza
 */
public class FilaSesion {
    private Sesion sesion;
    private final JButton btnEliminar = new JButton("Borrar");
    private final JButton btnEditar = new JButton("Editar");

    public FilaSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }
    
}
