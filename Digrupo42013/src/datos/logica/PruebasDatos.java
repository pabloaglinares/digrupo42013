/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.logica;

import datos.pojos.Sesion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Andrés Traspuesto Lanza
 */
public class PruebasDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        LogicaDatos logica = LogicaDatos.INSTANCE;
        Sesion sesion = new Sesion();
        sesion.setDescripcion("algo descriptivo");
        sesion.setTipo(Sesion.TipoSesion.ROCA);
        sesion.setFecha_hora1(sdf.parse("11/02/1989 12:35"));
        sesion.setFecha_hora2(sdf.parse("11/02/1989 14:35"));
        //logica.insertSesion(sesion);
        List<Sesion> sesiones = logica.getSesionBetweenFechas(sdf.parse("1/02/1989 23:35"),sdf.parse("27/02/1989 23:35"));
        for (Sesion ses: sesiones) System.out.println(ses.getDescripcion());
    }
    
}
