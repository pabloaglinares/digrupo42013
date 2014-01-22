package gui.tablaItinerario;


import datos.pojos.Itinerario;
import javax.swing.JButton;


/**
 * Wraper de objetos Itinerario que permite mostrarlos en una tabla con botones
 * de editar y eliminar
 * @author Andrés Traspuesto Lanza
 */
public class FilaItinerario {
    
    private Itinerario itinerario;
    private final JButton btnEliminar = new JButton("Borrar");
    private final JButton btnEditar = new JButton("Editar");

    public FilaItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }
    
    /**
     * Get the value of itinerario
     *
     * @return the value of itinerario
     */
    public Itinerario getItinerario() {
        return itinerario;
    }

    /**
     * Set the value of itinerario
     *
     * @param itinerario new value of itinerario
     */
    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }
    

}
