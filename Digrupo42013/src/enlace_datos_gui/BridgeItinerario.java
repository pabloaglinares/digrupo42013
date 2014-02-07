
package enlace_datos_gui;

import datos.ModItinerario;
import datos.daos.ItinerarioDAO;
import datos.pojos.Itinerario;
import gui.AltaItinerario;
import gui.Main;
import gui.tablaItinerario.ItinerariosTableModel;
import gui.tablaItinerario.TablaItinerarios;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Este singleton es un bridge que enlaza la capa gráfica con la de datos Itinerario
 * @author Andrés Traspuesto Lanza
 */
public enum BridgeItinerario {
    BRIDGE;
    private AltaItinerario alta;
    private Main main;
    private BridgeRendimiento bridgeRendimiento = BridgeRendimiento.RENDIMIENTO;
    private TablaItinerarios tabla;
    private ItinerarioDAO dao = ItinerarioDAO.ITINERARIO_DAO;
    private int currentTab = 0;
    private Date date1;
    private Date date2;
    private int pItinerario;
    
    public void openForInsertNewITinerario() {
        showAlta();
        alta.checkAll();
        alta.setModoAlta(true);
    }
    
    public void opneForUpdateItinerario(Itinerario iti) {
        showAlta();
        alta.getTfNombreItinerario().setText(iti.getNombre());
        alta.getTfLocalizacion().setText(iti.getLocalizacion());
        alta.getCbTipoItineracio().setSelectedItem(iti.getTipo());
        String[] difs = getDificultyGroups(iti.getDifucultad());
        alta.getSpDificultadNumero().setValue(Integer.parseInt(difs[0]));
        alta.getSpDificultadLetra().setValue(difs[1]);
        if(difs[2].equals("+"))alta.getSpDificultadMasMenos().setValue(difs[2]);
        alta.getTblFechas().getModel().setDates(iti.getFechasResolucion());
        alta.setImagen(iti.getPathImagen());
        pItinerario = iti.getpItinerario();
    }

    public String[] getDificultyGroups(String dificultad) {
        String[] groups = new String[3];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dificultad);
        if(matcher.find()) groups[0] = matcher.group();
        pattern = Pattern.compile("[abc]");
        matcher = pattern.matcher(dificultad);
        if(matcher.find()) groups[1] = matcher.group();
        pattern = Pattern.compile("\\+|-");
        matcher = pattern.matcher(dificultad);
        if(matcher.find()) groups[2] = matcher.group();
        return groups;
    }
    private void showAlta() {
        alta = new AltaItinerario();
        main.getDpEscritorio().add(alta);
        alta.show();
    }
    public void closeAlta() {
        alta = null;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    
    
    public void setAlta(AltaItinerario alta) {
        this.alta = alta;
    }
    public void setDateModified(){
        alta.getCamposModificados()[5] = true;
    }
    /**
     * Asigna la pestaña actual
     * @param currentTab
     */
    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }
    /**
     * Añade un objeto TablaItinerarios para que sea actualizado si se producen 
     * cambios.
     * Hay que llamar este método si se mantiene abierta la ventana de listado
     * de itinerarios a la par que una de alta o modificación de sesiones
     * @param tabla 
     */
    public void addTablaItinerario(TablaItinerarios tabla) {
        this.tabla = tabla;
    }
    /**
     * Indica que no es necesario seguir actualizando la tabla de itinerarios
     * Es bueno llamar a este método cuando se cierra la ventana de listado de
     * sesiones
     */
    public void delTablaItinerarios() {
        this.tabla = null;
    }
    /**
     * Guarda el itinerario en la base de datos
     */
    public void saveItinerario() {
        String dificultad = ""+alta.getSpDificultadNumero().getValue() + alta.getSpDificultadLetra().getValue()+
                alta.getSpDificultadMasMenos().getValue();
        Itinerario it = new Itinerario(alta.getTfNombreItinerario().getText(),
                alta.getTfLocalizacion().getText(), alta.getCbTipoItineracio().getSelectedItem().toString(), dificultad, alta.getImagen());
        it.addAllFechas(alta.getTblFechas().getModel().getAllDates());
        if(alta.isModoAlta()){
            dao.insertItinerario(it);
            loadItinerarioProperly(it);
        } else {
            it.setpItinerario(pItinerario);
//            dao.updateItinerario(it, getTypeOfMod(), alta.getImagen());
            dao.updateItinerario(it, ModItinerario.TOTAL, alta.getImagen());
        }
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
        if((mods[0]||mods[1]||mods[2]||mods[3]) && (mods[4] || mods[5])) {
            return ModItinerario.TOTAL;
        } else if(mods[0]||mods[1]||mods[2]||mods[3]) {
            return ModItinerario.SOLO_NOMBRE_LOCALIZACION_DIFICULTAD;
        } else if(mods[4]) {
            return ModItinerario.SOLO_FECHAS;
        }
        return ModItinerario.SOLO_IMAGEN;
    }
    /**
     * Obtiene los itinerarios que resulten convenientes en función del filtro
     * aplicado a la tabla
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
        if(tabla != null) ((ItinerariosTableModel)tabla.getModel()).setItinerarios(dao.getAllItinerario());
    }
    /**
     * Carga en la tabla los itinerarios resueltos entre las fechas
     * @param date1
     * @param date2 
     */
    public void loadByRange(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
        if(tabla != null) ((ItinerariosTableModel)tabla.getModel()).setItinerarios(dao.getItinerariosByFechaRange(date1, date2));
    }
}