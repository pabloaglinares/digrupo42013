

package enlace_datos_gui;

import datos.daos.SesionDAO;
import datos.pojos.Sesion;
import gui.tablasesion.SesionTableModel;
import gui.tablasesion.TablaSesiones;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Este singleton es un bridge que enlaza la capa gráfica con la de datos Sesion
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeSesion {
    BRIDGE;
    private TablaSesiones tablaSesiones;
    private SesionDAO dao = SesionDAO.SESION_DAO;
    private int currentTab = 0;
    private Date date1;
    private Date date2;
    private Sesion.TipoSesion tipo;
    /**
     * Asigna la pestaña actual
     * @param currentTab
     */
    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }
    
    
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
        System.out.println(tipo.toString());
        Sesion s = new Sesion(fh_1, fh_2, descripcion, tipo);
        SesionDAO.SESION_DAO.insertSesion(s);
        loadSesionProperly();
        
    }
    /**
     * Se encarga de decidir con que sesiones hay que cargar la tabla en función
     * de la pestaña en la que se encuentra la pantalla de listado
     */
    private void loadSesionProperly() {
        switch (currentTab) {
            case 0:
                loadAllSesion();
                break;
            case 1:
                loadByRange(date1, date2);
                break;
            case 2:
                loadByTipo(tipo);
                break;
        }
    }
    /**
     * Carga en la tabla todas las sesiones contenidas en la base de datos
     */
    public void loadAllSesion() {
        if (tablaSesiones != null) ((SesionTableModel)tablaSesiones.getModel()).setSesion(dao.getAllSesion());
    }
    /**
     * Carga en la tabla las sesiones cuyas fechas están entre date1 y date 2
     * @param date1
     * @param date2 
     */
    public void loadByRange(Date date1, Date date2) {
        if(tablaSesiones != null && date1.before(date2)) {
            this.date1 = date1;
            this.date2 = date2;
            ((SesionTableModel)tablaSesiones.getModel()).setSesion(dao.getSesionBetweenFechas(date1, date2));
        }
    }
    public void loadByTipo(Sesion.TipoSesion tipo) {
        if(tablaSesiones != null && tipo != null) {
            this.tipo = tipo;
            ((SesionTableModel)tablaSesiones.getModel()).setSesion(dao.getSesionByTipo(tipo));
        }
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
//       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        System.out.println(sdf.format(cal.getTime()));
        return cal.getTime();
    }
    
    
}
