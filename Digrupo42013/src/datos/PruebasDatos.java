/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import datos.daos.ConfiguracionDAO;
import datos.daos.ItinerarioDAO;
import datos.daos.SesionDAO;
import datos.pojos.Configuracion;
import datos.pojos.Itinerario;
import datos.pojos.Sesion;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        UtilesBD logica = UtilesBD.INSTANCE;
//        pruebaConfig(sdf, logica);
        
        
//        pruebaSesion(sdf, logica);
//        pruebaItinerario(sdf, logica);
        System.out.println(logica.calculaRendimiento());
    }
    static void pruebaConfig(SimpleDateFormat sdf, UtilesBD logica) throws ParseException {
        Configuracion cfg = new Configuracion();
        cfg.setApellidos("Perez");
        cfg.setNombre("Juan");
        cfg.setFecha1Intervalo(sdf.parse("12/02/1980 12:35"));
        cfg.setFecha2Intervalo(sdf.parse("12/02/1999 12:35"));
        ConfiguracionDAO.CONFIGURACION_DAO.insertConfiguracion(cfg);
    }
    private static void pruebaSesion(SimpleDateFormat sdf, UtilesBD logica) throws ParseException {
        Sesion sesion = new Sesion();
        sesion.setDescripcion("algo descriptivo1");
        sesion.setTipo(Sesion.TipoSesion.ROCA);
        sesion.setFecha_hora1(sdf.parse("12/02/1989 12:35"));
        sesion.setFecha_hora2(sdf.parse("12/02/1989 14:35"));
        SesionDAO.SESION_DAO.insertSesion(sesion);
        sesion.setDescripcion("algo descriptivo2");
        sesion.setTipo(Sesion.TipoSesion.ROCA);
        sesion.setFecha_hora1(sdf.parse("13/02/1989 12:35"));
        sesion.setFecha_hora2(sdf.parse("13/02/1989 14:35"));
        SesionDAO.SESION_DAO.insertSesion(sesion);
        List<Sesion> sesiones = SesionDAO.SESION_DAO.getSesionBetweenFechas(sdf.parse("1/02/1989 23:35"),sdf.parse("27/02/1989 23:35"));
        for (Sesion ses: sesiones) System.out.println(ses.getDescripcion());
        sesiones = SesionDAO.SESION_DAO.getSesionByFecha(sdf.parse("12/02/1989 12:35"), Comparador.NE);
        for (Sesion ses: sesiones) System.out.println(ses.getDescripcion());
    }
    private static void pruebaItinerario(SimpleDateFormat sdf, UtilesBD logica) throws ParseException {
        Itinerario itinerario = new Itinerario();
        itinerario.setNombre("Naranco Bulnes Cara Sur vía Me refugio en la bebida");
        itinerario.setPathImagen(new File("naranco.jpeg"));
        itinerario.setDifucultad("6c");
        itinerario.addFechaResolucion(sdf.parse("1/2/1989 12:00"));
        ItinerarioDAO.ITINERARIO_DAO.insertItinerario(itinerario);
        itinerario = new Itinerario();
        itinerario.setNombre("Curavacas Vía Ferrata");
        itinerario.setPathImagen(new File("curavacas.jpg"));
        itinerario.setDifucultad("3");
        itinerario.addFechaResolucion(sdf.parse("1/5/1989 12:00"));
        ItinerarioDAO.ITINERARIO_DAO.insertItinerario(itinerario);
        List<Itinerario> itinerarios = ItinerarioDAO.ITINERARIO_DAO.getAllItinerario();
        for (Itinerario iti : itinerarios) System.out.println(iti.getNombre());
    }
}
