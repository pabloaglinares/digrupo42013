package enlace_datos_gui;

import datos.daos.SesionDAO;
import datos.pojos.Sesion;
import gui.AltaSesion;
import gui.Main;
import gui.tablasesion.SesionTableModel;
import gui.tablasesion.TablaSesiones;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Este singleton es un bridge que enlaza la capa gráfica con la de datos Sesion
 *
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeSesion {

    BRIDGE;
    private Main main;
    private AltaSesion alta;
    private BridgeRendimiento bridgeRendimiento = BridgeRendimiento.RENDIMIENTO;
    private TablaSesiones tablaSesiones;
    private SesionDAO dao = SesionDAO.SESION_DAO;
    private int currentTab = 0;
    private Date date1;
    private Date date2;
    private Sesion.TipoSesion tipo;
    private int currentPSesion;

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Asigna la pestaña actual
     *
     * @param currentTab
     */
    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }

    public void setAlta(AltaSesion alta) {
        this.alta = alta;
    }

    /**
     * Añade un objeto TablaSesiones para que sea actualizado si se producen
     * cambios. Hay que llamar este método si se mantiene abierta la ventana de
     * listado de sesiones a la par que una de alta o modificación de sesiones
     *
     * @param tabla
     */
    public void addTablaSesiones(TablaSesiones tabla) {
        this.tablaSesiones = tabla;
    }

    /**
     * Indica que no es necesario seguir actualizando la tabla de sesiones Es
     * bueno llamar a este método cuando se cierra la ventana de listado de
     * sesiones
     */
    public void delTablaSesiones() {
        tablaSesiones = null;
    }

    /**
     * Almacena el objeto sesión en la base de datos, creado a partir del
     * contenido de la pantalla de alta de sesiones y actualiza la ventana de
     * listado de sesiones si se encuentra abierta
     */
    public void saveSesion() {
        Date fh_1 = setTimeInDate(alta.getTfHoraComienzo().getText(), alta.getDtfFecha().getDate());
        Date fh_2 = setTimeInDate(alta.getTfHoraFin().getText(), alta.getDtfFecha().getDate());
        Sesion s = new Sesion(fh_1, fh_2, alta.getTfDescripcion().getText(),
                (Sesion.TipoSesion) alta.getCbTipoSesion().getSelectedItem());
        if(alta.isModoAlta()) {
            dao.insertSesion(s);
        } else {
            s.setpSesion(currentPSesion);
            dao.updateSesion(s);
        }
        loadSesionProperly();
        bridgeRendimiento.setRendimiento();

    }

    /**
     * Abre la ventana de alta de sesiones para crear una nueva sesión
     */
    public void openToInsertNewSesion() {
        showAlta();
        alta.setModoAlta(true);
    }
    /**
     * Abre la ventana de alta en modo modificación
     * @param sesion 
     */
    public void openToModifySesion(Sesion sesion) {
        showAlta();
        currentPSesion = sesion.getpSesion();
        alta.getCbTipoSesion().setSelectedItem(sesion.getTipo());
        alta.getDtfFecha().setDate(sesion.getFecha_hora1());
        alta.getTfHoraComienzo().setText(getTimeFromDate(sesion.getFecha_hora1()));
        alta.getTfHoraFin().setText(getTimeFromDate(sesion.getFecha_hora2()));
        alta.getTfDescripcion().setText(sesion.getDescripcion());
        alta.setModoAlta(false);
        alta.checkAll();
    }

    private void showAlta() {
        if (alta == null) {
            alta = new AltaSesion();
            main.getDpEscritorio().add(alta);
        }
        alta.show();
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
        if (tablaSesiones != null) {
            ((SesionTableModel) tablaSesiones.getModel()).setSesion(dao.getAllSesion());
        }
    }

    /**
     * Carga en la tabla las sesiones cuyas fechas están entre date1 y date 2
     *
     * @param date1
     * @param date2
     */
    public void loadByRange(Date date1, Date date2) {
        if (tablaSesiones != null && date1.before(date2)) {
            this.date1 = date1;
            this.date2 = date2;
            ((SesionTableModel) tablaSesiones.getModel()).setSesion(dao.getSesionBetweenFechas(date1, date2));
        }
    }

    public void loadByTipo(Sesion.TipoSesion tipo) {
        if (tablaSesiones != null && tipo != null) {
            this.tipo = tipo;
            ((SesionTableModel) tablaSesiones.getModel()).setSesion(dao.getSesionByTipo(tipo));
        }
    }

    /**
     * Devuelve un objeto date tras ajustar las horas y los minutos
     *
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
    /**
     * Devuelve un String que contiene la hora en formato 24h
     * @param date
     * @return 
     */
    private String getTimeFromDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
}
