/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.ToggleEvent;
import py.com.ping.administracionBase.ejb.AccesosGruposEJB;
import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswModulos;
import py.com.ping.administracionBase.jpa.BswTipoForma;
import py.com.ping.administracionBase.util.ApplicationConstant;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gdb <dominbla@gmail.com>
 */
@Named
@ViewScoped
public class BswGrupoUsuarioControlador extends AbstractControllerGenerico<BswGruposUsuarios> {

    private static final Logger log =
            LoggerFactory.getLogger(BswGrupoUsuarioControlador.class);

    @EJB
    AccesosGruposEJB accesosGruposEJB;

    private boolean esAgregarPermisos;

    @Override
    public void setParametros() {
        this.formName = ApplicationConstant.nombreFormaGruposBase;
        this.clase = BswGruposUsuarios.class;
        this.paginaActual = "BswGrupoUsuarios.xhtml";

        if (getAcercaDe().isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma: BSGRUPOS");
            getAcercaDe().add("- Controlador: BswGrupoUsuarioControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswGruposUsuarios.java");
        }

        if (getAyuda().isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Grupos de usuarios.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacer un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }
    }

    @Override
    protected void antesABM() {
        getActual().setCodUsuarioAud(getUsuarioLogueado().getCodUsuario());
        actual.setBswEmpresas(empresaLogueada);
    }

//<editor-fold defaultstate="collapsed" desc="rowTogle">
    private BswModulos moduloSelecc;
    private BswTipoForma tipoFormaSelecc;
    private List<BswFormas> formaList;

    private List<BswTipoForma> bswTipoFormaList;

    private HashMap campos;

    public void onModuloRowToggle(ToggleEvent event) {
        moduloSelecc = (BswModulos) event.getComponent().getAttributes().get("modulo");
        obtieneTipoFormaFromModulo();
    }

    public void onTipoFormaRowToggle(ToggleEvent event) {
        tipoFormaSelecc = (BswTipoForma) event.getComponent().getAttributes().get("tipoForma");
        obtieneFormaFromModuloTform();
    }

    public List<BswTipoForma> getBswTipoFormaList() {
        return bswTipoFormaList;
    }

    public void setBswTipoFormaList(List<BswTipoForma> bswTipoFormaList) {
        this.bswTipoFormaList = bswTipoFormaList;
    }

    private void obtieneTipoFormaFromModulo() {

        bswTipoFormaList = accesosGruposEJB.getTiposFormaByModulo(moduloSelecc);

    }

    public List<BswFormas> getFormaList() {
        return formaList;
    }

    public void setFormaList(List<BswFormas> formaList) {
        this.formaList = formaList;
    }

    private void obtieneFormaFromModuloTform() {
        try {
            campos = new HashMap();      
            campos.put("bswModulos.codModulo", moduloSelecc.getCodModulo());
            campos.put("bswTipoForma.id", tipoFormaSelecc.getId());
            //campos.put("bswModulos.bswEmpresas.id", getEmpresaLogueada().getId());

            campos.put("-1.operador:id:  BY ", " asc");

            //getEm().getEntityManagerFactory().getCache().evict(BswFormas.class);

            formaList = getGenericQuery().getSearchResultList(campos, BswFormas.class);

            addPermisoUsuario(formaList);
        } catch (Exception e) {

        }
    }

    public void onPermisoChangeEvent(BswModulos modulo, BswTipoForma tipoForma, BswFormas forma) {
        BswAccesosGrupos bag = getBswAccesoGrupo(modulo, forma, actual);

        bag.setPuedeConsultarABM(forma.isPuedeConsultarABM());
        bag.setPuedeInsertarABM(forma.isPuedeInsertarABM());
        bag.setPuedeModificarABM(forma.isPuedeModificarABM());
        bag.setPuedeEliminarABM(forma.isPuedeEliminarABM());
        bag.setBswEmpresas(empresaLogueada);
        if (bag.getId() == null) {
            accesosGruposEJB.insertar(bag);
        } else {
            accesosGruposEJB.actualizar(bag);
        }

    }

//</editor-fold>
    private BswAccesosGrupos getBswAccesoGrupo(BswModulos modulo, BswFormas forma, BswGruposUsuarios grupoUsuario) {
        
        BswAccesosGrupos bag = accesosGruposEJB.getAccesoGrupoByProps(modulo, forma, grupoUsuario, empresaLogueada);
        
        if (bag == null) {
            bag = new BswAccesosGrupos();
            bag.setBswModulos(modulo);
            bag.setBswFormas(forma);
            bag.setBswGruposUsuarios(grupoUsuario);
        }
        return bag;
    }

    private void addPermisoUsuario(List<BswFormas> formaList) {
        BswAccesosGrupos bag;
        if (formaList != null) {
            for (BswFormas forma : formaList) {
                bag = accesosGruposEJB.getAccesoGrupoByProps(moduloSelecc, forma, actual, empresaLogueada);
                if (bag != null) {
                    forma.setPuedeConsultarABM(bag.isPuedeConsultarABM());
                    forma.setPuedeInsertarABM(bag.isPuedeInsertarABM());
                    forma.setPuedeModificarABM(bag.isPuedeModificarABM());
                    forma.setPuedeEliminarABM(bag.isPuedeEliminarABM());
                }
            }
        }

    }

    public boolean isEsAgregarPermisos() {
        return esAgregarPermisos;
    }

    public void setEsAgregarPermisos(boolean esAgregarPermisos) {
        this.esAgregarPermisos = esAgregarPermisos;
    }

    @Override
    public void despuesABM() {
        if (esAgregarPermisos) {
            setEsABMPanel(true);
            setEsModificarDetalle(true);
        } else {
            super.despuesABM();
        }
    }

    @Override
    public void limpiarDatosLocales() {
        esAgregarPermisos = false;
    }

}
