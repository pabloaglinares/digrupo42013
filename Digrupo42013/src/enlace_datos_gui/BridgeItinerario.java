package enlace_datos_gui;

import datos.ModItinerario;
import datos.daos.ItinerarioDAO;
import datos.pojos.Itinerario;
import gui.AltaItinerario;
import gui.DetallesItinerario;
import gui.ListadoItinerarios;
import gui.Main;
import gui.tablaItinerario.ItinerariosTableModel;
import gui.tablaItinerario.TablaItinerarios;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Este singleton es un bridge que enlaza la capa gráfica con la de datos
 * Itinerario
 *
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeItinerario {

    BRIDGE;
    private AltaItinerario alta;
    private Main main;
    private BridgeRendimiento bridgeRendimiento = BridgeRendimiento.RENDIMIENTO;
    private AyudaUtil ayuda = AyudaUtil.AYUDA;
    private ListadoItinerarios listado;
    private TablaItinerarios tabla;
    private ItinerarioDAO dao = ItinerarioDAO.ITINERARIO_DAO;
    private int currentTab = 0;
    private Date date1;
    private Date date2;
    private int pItinerario;
    private File oldImg;
    /**
     * Abre la ventana de listado de itinerarios
     * @param main 
     */
    public void openListado(Main main){
        this.main = main;
        listado = new ListadoItinerarios();
        ayuda.asociateF1ToJFrame(listado, AyudaUtil.PantallaAyuda.LISTADO_ITINERARIO);
        main.getDpEscritorio().add(listado);
        listado.show();
    }
    /**
     * Abre la ventana de alta de itinerarios
     * @param main 
     */
    public void openForInsertNewITinerario(Main main) {
        this.main = main;
        showAlta();
        alta.checkAll();
        alta.setModoAlta(true);
    }

    /**
     * Abre la ventana de alta en modo update
     *
     * @param iti
     */
    public void opneForUpdateItinerario(Itinerario iti) {
        showAlta();
        alta.getTfNombreItinerario().setText(iti.getNombre());
        alta.getTfLocalizacion().setText(iti.getLocalizacion());
        alta.getCbTipoItineracio().setSelectedItem(iti.getTipo());
        String[] difs = getDificultyGroups(iti.getDifucultad());
        alta.getSpDificultadNumero().setValue(Integer.parseInt(difs[0]));
        alta.getSpDificultadLetra().setValue(difs[1]);
        if (difs[2].equals("+")) {
            alta.getSpDificultadMasMenos().setValue(difs[2]);
        }
        alta.getTblFechas().getModel().setDates(iti.getFechasResolucion());
        alta.setImagen(iti.getPathImagen());
        pItinerario = iti.getpItinerario();
        alta.setModoAlta(false);
        oldImg = iti.getPathImagen();
    }

    /**
     * Devuelve un array de String con los diferentes componentes de la
     * dificultad
     *
     * @param dificultad
     * @return
     */
    private String[] getDificultyGroups(String dificultad) {
        String[] groups = new String[3];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dificultad);
        if (matcher.find()) {
            groups[0] = matcher.group();
        }
        pattern = Pattern.compile("[abc]");
        matcher = pattern.matcher(dificultad);
        if (matcher.find()) {
            groups[1] = matcher.group();
        }
        pattern = Pattern.compile("\\+|-");
        matcher = pattern.matcher(dificultad);
        if (matcher.find()) {
            groups[2] = matcher.group();
        }
        return groups;
    }

    /**
     * Muestra la ventana de alta
     */
    private void showAlta() {
        alta = new AltaItinerario();
        ayuda.asociateF1ToJFrame(alta, AyudaUtil.PantallaAyuda.ALTA_ITINERARIO);
        main.getDpEscritorio().add(alta);
        alta.show();
    }
    
    /**
     * Cierra la ventana de alta
     */
    public void closeAlta() {
        alta = null;
    }
    /**
     * Cierra la ventana de listado
     */
    public void closeLista() {
        listado.dispose();
        tabla = null;
        listado = null;
    }

    /**
     * Asigna la referencia a la ventana de alta
     *
     * @param alta
     */
    public void setAlta(AltaItinerario alta) {
        this.alta = alta;
    }

    /**
     * indica que se ha modificado la fecha
     */
    public void setDateModified() {
        alta.getCamposModificados()[5] = true;
    }

    /**
     * Asigna la pestaña actual
     *
     * @param currentTab
     */
    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }

    /**
     * Añade un objeto TablaItinerarios para que sea actualizado si se producen
     * cambios. Hay que llamar este método si se mantiene abierta la ventana de
     * listado de itinerarios a la par que una de alta o modificación de
     * sesiones
     *
     * @param tabla
     */
    public void addTablaItinerario(TablaItinerarios tabla) {
        this.tabla = tabla;
    }

    /**
     * Indica que no es necesario seguir actualizando la tabla de itinerarios Es
     * bueno llamar a este método cuando se cierra la ventana de listado de
     * sesiones
     */
    public void delTablaItinerarios() {
        this.tabla = null;
    }

    /**
     * Guarda el itinerario en la base de datos
     */
    public void saveItinerario() {
        String dificultad = "" + alta.getSpDificultadNumero().getValue() + alta.getSpDificultadLetra().getValue()
                + alta.getSpDificultadMasMenos().getValue();
        Itinerario it = new Itinerario(alta.getTfNombreItinerario().getText(),
                alta.getTfLocalizacion().getText(), alta.getCbTipoItineracio().getSelectedItem().toString(), dificultad, alta.getImagen());
        it.addAllFechas(alta.getTblFechas().getModel().getAllDates());
        if (alta.isModoAlta()) {
            if(!dao.insertItinerario(it))JOptionPane.showMessageDialog(alta, "No se pudo guardar el itinerario tal vez ya esté registrado", 
                    "Error no se pudo guardar", JOptionPane.ERROR_MESSAGE);
        } else {
            it.setpItinerario(pItinerario);
            it.setPathImagen(oldImg);
            dao.updateItinerario(it, ModItinerario.TOTAL, alta.getImagen());
        }
        loadItinerarioProperly(it);
        bridgeRendimiento.setRendimiento();

    }

    /**
     * Determina el tipo de actualización que se debe realizar sobre la BD
     * <ul>
     * <li>0: nombre</li>
     * <li>1: localizacion</li>
     * <li>2: tipo</li>
     * <li>3: dificultad</li>
     * <li>4: fechas</li>
     * <li>5: imagen</li>
     * </ul>
     */
    private ModItinerario getTypeOfMod() {
        boolean[] mods = alta.getCamposModificados();
        if ((mods[0] || mods[1] || mods[2] || mods[3]) && (mods[4] || mods[5])) {
            return ModItinerario.TOTAL;
        } else if (mods[0] || mods[1] || mods[2] || mods[3]) {
            return ModItinerario.SOLO_NOMBRE_LOCALIZACION_DIFICULTAD;
        } else if (mods[4]) {
            return ModItinerario.SOLO_FECHAS;
        }
        return ModItinerario.SOLO_IMAGEN;
    }

    /**
     * Obtiene los itinerarios que resulten convenientes en función del filtro
     * aplicado a la tabla
     *
     * @param it
     */
    private void loadItinerarioProperly(Itinerario it) {
        switch (currentTab) {
            case 0:
                loadAllItinerario();
                break;
            case 1:
                loadByRange(date1, date2);
                break;
        }
    }

    /**
     * Carga todos los itinerarios en la tabla
     */
    public void loadAllItinerario() {
        if (tabla != null) {
            ((ItinerariosTableModel) tabla.getModel()).setItinerarios(dao.getAllItinerario());
        }
    }

    /**
     * Carga en la tabla los itinerarios resueltos entre las fechas
     *
     * @param date1
     * @param date2
     */
    public void loadByRange(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
        if (tabla != null) {
            ((ItinerariosTableModel) tabla.getModel()).setItinerarios(dao.getItinerariosByFechaRange(date1, date2));
        }
    }
    /**
     * Carga los detalles en la ventana de detalles
     * @param it
     * @return 
     */
    public String loadDetalis(Itinerario it) {
        /*
        string 1 nombre
        string 2 localizacion
        string 3 dificultad
        string 4 todas las fechas en forma <li>fecha</li>
        */
        String html = "<html padding=0>\n"
                + "<head>\n"
                + "</head>\n"
                + "<body bgcolor=#e4f4cf padding=0 margin=0>\n"
                + "  <h1 align=center>%1$s</h1>"
                + "  <table><tr>"
                + "<td><img width=350 height=300 src ='file:%5$s' style='float:left'/></td>"
                + "<td valign='top'>El itinerario<b> %1$s</b> que esta localizado en <b>%2$s</b>  y cuya dificultad  "
                + "según el sistema francés tiene el valor <b>%3$s</b> "
                +"se ha logrado resolver en las siguientes fechas:</p><ul>%4$s</ul><td>"
                +"<tr></table>"
                + "</body>\n"
                + "</html>";
        StringBuilder sbFechas = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(Date date: it.getFechasResolucion()) sbFechas.append("<li>").append(sdf.format(date)).append("</li>");
        return String.format(html, it.getNombre(),it.getLocalizacion(),it.getDifucultad(),sbFechas.toString(),it.getPathImagen().getAbsolutePath());
    }
    /**
     * Muestra la pantalla de detalles de itinerario
     * @param it 
     */
    public void showItinerario(Itinerario it) {
        DetallesItinerario dt = new DetallesItinerario();
        dt.getEdpImagen().setText(loadDetalis(it));
        ayuda.asociateF1ToJFrame(dt, AyudaUtil.PantallaAyuda.DETALLE_ITINERARIO);
        main.getDpEscritorio().add(dt);
        dt.setVisible(true);
    }
}
