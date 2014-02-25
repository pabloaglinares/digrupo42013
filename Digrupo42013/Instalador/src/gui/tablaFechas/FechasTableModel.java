/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tablaFechas;

import gui.botonestablas.WraperFila;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Model para contener una fecha y dos botones en un JList
 *
 * @author Andrés Traspuesto Lanza
 */
public class FechasTableModel extends AbstractTableModel {

    private final String[] cabecera;
    private List<WraperFila<Date>> lista;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public FechasTableModel(String[] cabecera, List<WraperFila<Date>> lista) {
        this.cabecera = cabecera;
        this.lista = lista;
    }

    /**
     * Añade una fecha a la lista
     *
     * @param date
     */
    public void addDate(Date date) {
        lista.add(new WraperFila<>(date));
        fireTableRowsInserted(0, lista.size());
    }

    /**
     * Añade todas las fechas pasadas en un objeto iterable a la lista
     *
     * @param dates
     */
    public void setDates(Iterable<Date> dates) {
        lista = new ArrayList<>();
        for (Date date : dates) {
            lista.add(new WraperFila<>(date));
        }
        fireTableRowsInserted(0, lista.size());
    }

    /**
     * Elimina la fecha de la posición indicada
     *
     * @param index
     */
    public void removeDate(int index) {
        if (lista != null && index < lista.size()) {
            lista.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }
    public Date getDateAt(int index) {
        return lista.get(index).getElemento();
    }
    public List<Date> getAllDates(){
        List<Date> dates = new ArrayList<>();
        for(WraperFila<Date> fila: lista) dates.add(fila.getElemento());
        return dates;
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

    @Override
    public int getRowCount() {
        int n = lista != null ? lista.size() : 0;
        return n;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        switch(columnIndex){
            case 0:
                obj = sdf.format(lista.get(rowIndex).getElemento());
                break;
            case 1:
                obj = lista.get(rowIndex).getBtnEliminar();
                break;
            case 2:
                obj = lista.get(rowIndex).getBtnEditar();
                
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
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    /**
     * Devuelve true si la fecha ya está en la lista
     * @param date
     * @return 
     */
    public boolean containsFecha(Date date) {
        return lista.contains(new WraperFila<>(date));
    }
}
