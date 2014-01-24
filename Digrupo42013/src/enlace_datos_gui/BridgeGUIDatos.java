

package enlace_datos_gui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Este singleton es un bridge que enlaza la capa gráfica con la de datos
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeGUIDatos {
    BRIDGE;
    /**
     * Devuelve un objeto date tras ajustar las horas y los minutos
     * @param time
     * @param date
     * @return 
     */
    public Date setTimeInDate(String time, Date date) {
        if (!time.matches("[0-23]:[0-59]")) throw new IllegalArgumentException("El parámetro time debe tener el formato hh:mm");
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        String[] h_m = time.split(":");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h_m[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(h_m[1]));
        return cal.getTime();
    }
    
    
}
