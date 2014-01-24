

package enlace_datos_gui;

import datos.daos.SesionDAO;
import datos.pojos.Sesion;
import gui.tablasesion.SesionTableModel;
import gui.tablasesion.TablaSesiones;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Este singleton es un bridge que enlaza la capa gráfica con la de datos
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeSesion {
    BRIDGE;
    private TablaSesiones tablaSesiones;
    
    /**
     * Añade un objeto TablaSesiones para que sea actualizado si se producen 
     * cambios.
     * Hay que llamar este método si se mantiene abierta la ventana de listado
     * de sesiones a la par que una de alta o modificación de sesiones
     * @param tabla 
     */
    public void addTablaSesiones(TablaSesiones tabla) {
        this.tablaSesiones = tabla;
    }
    /**
     * Indica que no es necesario seguir actualizando la tabla de sesiones
     * Es bueno llamar a este método cuando se cierra la ventana de listado de
     * sesiones
     */
    public void delTablaSesiones() {
        tablaSesiones = null;
    }
    
    
    
    /**
     * Almacena el objeto sesión en la base de datos y actualiza la ventana de 
     * listado de sesiones si se encuentra abierta
     * @param fecha
     * @param horaInicio
     * @param horaFin
     * @param tipo
     * @param descripcion 
     */
    public void saveSesion(Date fecha, String horaInicio, String horaFin, Sesion.TipoSesion tipo, String descripcion) {
        Date fh_1 = setTimeInDate(horaInicio, fecha);
        Date fh_2 = setTimeInDate(horaFin, fecha);
        Sesion s = new Sesion(fecha, fecha, descripcion, tipo);
        SesionDAO.SESION_DAO.insertSesion(s);
        if (tablaSesiones != null) ((SesionTableModel)tablaSesiones.getModel()).addSesion(s);
        
    }
    /**
     * Carga en la tabla todas las sesiones contenidas en la base de datos
     */
    public void loadAllSesion() {
        if (tablaSesiones != null) ((SesionTableModel)tablaSesiones.getModel()).setSesion(SesionDAO.SESION_DAO.getAllSesion());
    }
    /**
     * Devuelve un objeto date tras ajustar las horas y los minutos
     * @param time
     * @param date
     * @return 
     */
    private Date setTimeInDate(String time, Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        String[] h_m = time.split(":");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h_m[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(h_m[1]));
        return cal.getTime();
    }
    
    
}
