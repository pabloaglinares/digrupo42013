package gui.tablaItinerario;


import datos.pojos.Itinerario;
import gui.botonestablas.WraperFila;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 * TableModel específico para contener objetos Itinerario, encargandose de mapear
 * dichos objetos  colocando cada atributo en la columna correspondiente
 * @author Andrés Traspuesto Lanza
 */
public class ItinerariosTableModel extends AbstractTableModel {


    /**
     * Nombre de las columnas.
     */
    private String[] cabecera;
    /**
     * Datos.
     */
    private List<WraperFila<Itinerario>> filas = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param columnNames Nombres de las columnas
     * @param filas
     */
    public ItinerariosTableModel(String[] columnNames, List<WraperFila<Itinerario>> filas) {
        this.cabecera = columnNames;
        this.filas = filas;
    }
    /**
     * Devuelve el nombre de la columna
     * @param column
     * @return 
     */
    @Override
    public String getColumnName(int column) {
        return cabecera[column];
    }
    /**
     * Devuelve el número de filas
     * @return 
     */
    @Override
    public int getRowCount() {
        return filas != null? filas.size(): 0;
    }
    /**
     * Devuelve el número de columnas
     * @return 
     */
    @Override
    public int getColumnCount() {
        return cabecera != null? cabecera.length : 0;
    }

    /**
     * Nos devolverá la clase que contiene cada columna, es necesario para
     * trabajar correctamente con los componentes que mostraremos en la tabla.
     * @return 
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        Class clase = Object.class;

        Object aux = getValueAt(0, columnIndex);
        if (aux != null) {
            clase = aux.getClass();
        }

        return clase;
    }
    /**
     * Devuelve el objeto contenido en la celda indicada por los parámetros
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        switch (columnIndex) {
            case 0:
                obj = filas.get(rowIndex).getElemento().getNombre();
                break;
            case 1:
                obj = filas.get(rowIndex).getElemento().getLocalizacion();
                break;
            case 2:
                obj = filas.get(rowIndex).getElemento().getDifucultad();
                break;
            case 3:
                obj = filas.get(rowIndex).getBtnEliminar();
                break;
            case 4:
                obj = filas.get(rowIndex).getBtnEditar();
                break;
                
        }
        return obj;
    }
    /**
     * Se sobreescribe para que puedan llegar los click a los botones
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 2;
    }

    /**
     * Elimina la fila indicada
     *
     * @param row
     */
    public void deleteItinerario(int row) {
        filas.remove(row);
        fireTableRowsDeleted(row, row);
    }
    /**
     * Devuelve el itinerario indicado
     * @param row
     * @return 
     */
    public Itinerario getItinerario(int row) {
        return filas.get(row).getElemento();
    }
    /**
     * Añade el itinerario al final de la tabla
     * @param it 
     */
    public void addItinerario(Itinerario it) {
        filas.add(new WraperFila<>(it));
        fireTableDataChanged();
    }
    /**
     * Asigna a la tabla los itinerarios contenidos en la lista
     * @param its 
     */
    public void setItinerarios(List<Itinerario> its) {
        filas = new ArrayList<>();
        for(Itinerario it: its) {
            filas.add(new WraperFila<>(it));
        }
        fireTableDataChanged();
    }
    /**
     * Devuelve una lista con todos los itinerarios de la tabla
     * @return 
     */
    public List<Itinerario> getItinerarios() {
        List<Itinerario> its = new ArrayList<>();
        for(WraperFila<Itinerario> fit: filas) {
            its.add(fit.getElemento());
        }
        return its;
    }

}
