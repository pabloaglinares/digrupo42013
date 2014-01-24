package gui.botonestablas;


import javax.swing.JButton;


/**
 * Wraper de objetos genéricos que permite mostrarlos en una tabla con botones
 * de editar y eliminar
 * @author Andrés Traspuesto Lanza
 * @param <T>
 */
public class Fila<T> {
    
    private T elemento;//Itinerario contenido en la fila
    private final static JButton btnEliminar = new JButton("Borrar");
    private final static JButton btnEditar = new JButton("Editar");

    public Fila(T elemento) {
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
    

}
