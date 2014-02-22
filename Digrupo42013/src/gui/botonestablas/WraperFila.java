package gui.botonestablas;

import java.util.Objects;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Wraper de objetos genéricos que permite mostrarlos en una tabla con botones
 * de editar y eliminar
 *
 * @author Andrés Traspuesto Lanza
 * @param <T>
 */
public class WraperFila<T> {

    private T elemento;//Itinerario contenido en la fila
    private final static JButton btnEliminar = new JButton("Borrar");
    private final static JButton btnEditar = new JButton("Editar");

    static {
        btnEliminar.setToolTipText("Eliminar el elemento correspondiente a la fila de este botón");
        btnEditar.setToolTipText("Editar el elemento correspondiente a la fila de este botón");
    }

    public WraperFila(T elemento) {
        this.elemento = elemento;
    }

    /**
     * Get the value of elemento
     *
     * @return the value of elemento
     */
    public T getElemento() {
        return elemento;
    }

    /**
     * Set the value of elemento
     *
     * @param elemento new value of elemento
     */
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.elemento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WraperFila<?> other = (WraperFila<?>) obj;
        if (!Objects.equals(this.elemento, other.elemento)) {
            return false;
        }
        return true;
    }
    
}
