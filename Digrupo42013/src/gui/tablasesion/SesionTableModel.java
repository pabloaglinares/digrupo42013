
package gui.tablasesion;

import datos.pojos.Sesion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class SesionTableModel extends AbstractTableModel{
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
    /**
     * Nombre de las columnas.
     */
    private String[] cabecera;
    /**
     * Datos.
     */
    private List<FilaSesion> filas = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param columnNames Nombres de las columnas
     * @param filas
     */
    public SesionTableModel(String[] columnNames, List<FilaSesion> filas) {
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        switch (columnIndex) {
            case 0:
                obj = sdf.format(filas.get(rowIndex).getSesion().getFecha_hora1());
                break;
            case 1:
                obj = sdf2.format(filas.get(rowIndex).getSesion().getFecha_hora1());
                break;
            case 2:
                obj = sdf2.format(filas.get(rowIndex).getSesion().getFecha_hora2());
                break;
            case 3:
                obj = filas.get(rowIndex).getSesion().getTipo().getNombre();
                break;
            case 4:
                obj = filas.get(rowIndex).getSesion().getDescripcion();
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
@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
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
     * @param row
     * @return 
     */
    public Sesion getSesion(int row) {
        return filas.get(row).getSesion();
    }
    /**
     * Añade la sesion al final de la tabla
     * @param s 
     */
    public void addSesion(Sesion s) {
        filas.add(new FilaSesion(s));
        fireTableDataChanged();
    }
    /**
     * Asigna a la tabla las sesiones contenidas en la lista
     * @param sesiones 
     */
    public void setSesion(List<Sesion> sesiones) {
        filas = new ArrayList<>();
        for(Sesion s: sesiones) {
            filas.add(new FilaSesion(s));
        }
        fireTableDataChanged();
    }
    /**
     * Devuelve una lista con todas las sesiones de la tabla
     * @return 
     */
    public List<Sesion> getSesions() {
        List<Sesion> sesiones = new ArrayList<>();
        for(FilaSesion fs: filas) {
            sesiones.add(fs.getSesion());
        }
        return sesiones;
    }
}
