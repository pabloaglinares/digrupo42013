package gui.tablasesion;

import datos.pojos.Sesion;
import gui.botonestablas.Fila;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TableModel específico para contener objetos Sesion, encargandose de mapear
 * dichos objetos colocando cada atributo en la columna correspondiente
 *
 * @author Andrés Traspuesto Lanza
 */
public class SesionTableModel extends AbstractTableModel {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
   // private TablaSesiones tabla;
    /**
     * Nombre de las columnas.
     */
    private String[] cabecera;
    /**
     * Datos.
     */
    private List<Fila<Sesion>> filas = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param columnNames Nombres de las columnas
     * @param filas
     */
    public SesionTableModel(String[] columnNames, List<Fila<Sesion>> filas) {
        this.cabecera = columnNames;
        this.filas = filas;
    }

   
    
    /**
     * Devuelve el nombre de la columna
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        return cabecera[column];
    }

    /**
     * Devuelve el número de filas
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return filas != null ? filas.size() : 0;
    }

    /**
     * Devuelve el número de columnas
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return cabecera != null ? cabecera.length : 0;
    }

    /**
     * Devuelve el objeto contenido en la celda indicada
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        switch (columnIndex) {
            case 0:
                obj = sdf.format(filas.get(rowIndex).getElemento().getFecha_hora1());
                break;
            case 1:
                obj = sdf2.format(filas.get(rowIndex).getElemento().getFecha_hora1());
                break;
            case 2:
                obj = sdf2.format(filas.get(rowIndex).getElemento().getFecha_hora2());
                break;
            case 3:
                obj = filas.get(rowIndex).getElemento().getTipo().getNombre();
                break;
            case 4:
                obj = filas.get(rowIndex).getElemento().getDescripcion();
                break;
            case 5:
                obj = filas.get(rowIndex).getBtnEliminar();
                break;
            case 6:
                obj = filas.get(rowIndex).getBtnEditar();
                break;

        }
        return obj;
    }

    /**
     * Devuelve la clase que contiene cada columna
     *
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
     * Se sobreescribe para que se pueda hacer click en los botones
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 4;
    }

    /**
     * Elimina la fila indicada
     *
     * @param row
     */
    public void deleteSesion(int row) {
        filas.remove(row);
        fireTableRowsDeleted(row, row);
    }

    /**
     * Devuelve la sesión contenida en la fila indicada
     *
     * @param row
     * @return
     */
    public Sesion getSesion(int row) {
        return filas.get(row).getElemento();
    }

    /**
     * Añade la sesion al final de la tabla
     *
     * @param s
     */
    public void addSesion(Sesion s) {
        filas.add(new Fila(s));
        fireTableDataChanged();
    }

    /**
     * Asigna a la tabla las sesiones contenidas en la lista
     *
     * @param sesiones
     */
    public void setSesion(List<Sesion> sesiones) {
        filas = new ArrayList<>();
        for (Sesion s : sesiones) {
            filas.add(new Fila(s));
        }
        fireTableDataChanged();
    }

    /**
     * Devuelve una lista con todas las sesiones de la tabla
     *
     * @return
     */
    public List<Sesion> getSesions() {
        List<Sesion> sesiones = new ArrayList<>();
        for (Fila<Sesion> fs : filas) {
            sesiones.add(fs.getElemento());
        }
        return sesiones;
    }
}
