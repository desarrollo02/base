/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.slf4j.LoggerFactory;
import py.com.ping.administracionBase.cdi.GenericLazyListNew;
import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
import py.com.ping.administracionBase.jpa.BswBarrios;
import py.com.ping.administracionBase.jpa.BswCiudades;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswFormas;
import py.com.ping.administracionBase.jpa.BswGrupoEmpresasUsuarios;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswIdentificaciones;
import py.com.ping.administracionBase.jpa.BswModulos;
import py.com.ping.administracionBase.jpa.BswMonedas;
import py.com.ping.administracionBase.jpa.BswNivelEstudios;
import py.com.ping.administracionBase.jpa.BswOpcionesParametros;
import py.com.ping.administracionBase.jpa.BswPaises;
import py.com.ping.administracionBase.jpa.BswParametrosGenerales;
import py.com.ping.administracionBase.jpa.BswPersonas;
import py.com.ping.administracionBase.jpa.BswProvincias;
import py.com.ping.administracionBase.jpa.BswRolUsuarioSucursal;
import py.com.ping.administracionBase.jpa.BswSectoresEcon;
import py.com.ping.administracionBase.jpa.BswSession;
import py.com.ping.administracionBase.jpa.BswSucursales;
import py.com.ping.administracionBase.jpa.BswTipoForma;
import py.com.ping.administracionBase.jpa.BswUsuarios;
import py.com.ping.utilitarios.interfaces.GenericQuery;

/**
 *
 * @author gdb <dominbla@gmail.com>
 *
 */
@Named
@ViewScoped
public class ComponentesVarios implements Serializable {

    private static final org.slf4j.Logger logger
            = LoggerFactory.getLogger(ComponentesVarios.class);

    private LazyDataModel lazyListCowBienesUso;

    @PersistenceContext
    private EntityManager em;
    private BswEmpresas empresaLogueada;
    private BswSucursales sucursalActual;
    private String codUser;
    private BswUsuarios usuarioLogueado;
    @EJB
    private GenericQuery genericQuery;
    private HashMap campos = new HashMap();
    private HashMap camposFiltros = new HashMap();

    public void cargarDatos(EntityManager em, BswEmpresas empresa,
            BswSucursales sucursal, String codUser,
            BswUsuarios usuarioLogueado) {
        this.sucursalActual = sucursal;
        this.empresaLogueada = empresa;
        this.em = em;
        this.codUser = codUser;
        this.usuarioLogueado = usuarioLogueado;
    }

    public GenericQuery getGenericQuery() {

        return genericQuery;
    }

    public BswSucursales getSucursalActual() {
        return sucursalActual;
    }

    public void setSucursalActual(BswSucursales sucursalActual) {
        this.sucursalActual = sucursalActual;
    }

    /**
     * @param genericQuery the genericQuery to set
     */
    public void setGenericQuery(GenericQuery genericQuery) {
        this.genericQuery = genericQuery;
    }

    //<editor-fold defaultstate="collapsed" desc="BswEmpresas">
    private GenericLazyListNew genericlazyListEmpresa;
    private LazyDataModel lazyListEmpresas;
    private BswEmpresas empresasAbm;

    public LazyDataModel getLazyListEmpresas() {
        if (lazyListEmpresas == null) {
            obtieneLazyListEmpresas();
        }

        return lazyListEmpresas;
    }

    public void setLazyListEmpresas(LazyDataModel lazyListEmpresas) {
        this.lazyListEmpresas = lazyListEmpresas;
    }

    public GenericLazyListNew getGenericlazyListEmpresa() {
        if (genericlazyListEmpresa == null) {
            genericlazyListEmpresa = new GenericLazyListNew(em, BswEmpresas.class);
        }
        return genericlazyListEmpresa;
    }

    public void setGenericlazyListEmpresa(GenericLazyListNew genericlazyListEmpresa) {
        this.genericlazyListEmpresa = genericlazyListEmpresa;
    }

    public void obtieneLazyListEmpresas() {
        campos = new HashMap();
        if (!getUsuarioLogueado().isSuperUsuario()) {
            if (getEmpresaLogueada().getBswGrupoEmpresas() != null && getUsuarioLogueado().getIndVerificaMenu() != null && getUsuarioLogueado().getIndVerificaMenu().equals("S")) {
                campos.put("WHERE", "1=1 and (b.bswGrupoEmpresas.id=" + getEmpresaLogueada().getBswGrupoEmpresas().getId() + ")");
            } else {
                campos.put("WHERE", "1=1 and (b.id=" + getEmpresaLogueada().getId() + ")");
            }
        }

        lazyListEmpresas = this.getGenericlazyListEmpresa().getLazyDataModel(BswEmpresas.class, campos);

    }

    /**
     * @return the empresaLogueada
     */
    public BswEmpresas getEmpresaLogueada() {
        if (empresaLogueada == null) {
            empresaLogueada = new BswEmpresas();
        }

        return empresaLogueada;
    }

    /**
     * @param empresaLogueada the empresaLogueada to set
     */
    public void setEmpresaLogueada(BswEmpresas empresaLogueada) {
        this.empresaLogueada = empresaLogueada;
    }

    public BswEmpresas getEmpresasAbm() {
        if (empresasAbm == null) {
            empresasAbm = new BswEmpresas();
        }
        return empresasAbm;
    }

    public void setEmpresasAbm(BswEmpresas empresasAbm) {
        this.empresasAbm = empresasAbm;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswUsuarios">
    private LazyDataModel lazyListUsuarios;
    private GenericLazyListNew gLL;
    private BswUsuarios usuariosABM;

    public BswUsuarios getUsuarioLogueado() {
        if (usuarioLogueado == null) {
            usuarioLogueado = new BswUsuarios();
        }
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(BswUsuarios usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public LazyDataModel getLazyListUsuarios() {
        if (lazyListUsuarios == null) {
            lazyListUsuarios = obtieneLazyListUsuarios();
        }

        return lazyListUsuarios;
    }

    public void setLazyListUsuarios(LazyDataModel lpb) {
        this.lazyListUsuarios = lpb;
    }

    public LazyDataModel obtieneLazyListUsuarios() {

        HashMap camposReservaArt = new HashMap();

        camposReservaArt.put("bswEmpresas.id", getEmpresaLogueada().getId().toString());
        // camposReservaArt.put("-1.operador:indVerificaMenu: is null ", " ");
        return this.getgLL().getLazyDataModel(BswUsuarios.class, camposReservaArt);

    }

    public List<BswUsuarios> getListaBswUsuariosPorEmpresa() {
        try {
            campos = new HashMap();
            campos.put("bswEmpresas.id", getEmpresaLogueada().getId());
            campos.put("estado", "A");
            getGenericQuery().setEm(em);
            return getGenericQuery().getSearchResultList(campos, BswUsuarios.class);
        } catch (Exception e) {
        }
        return null;
    }

    public void usuarioChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                usuariosABM = (BswUsuarios) em.createNamedQuery("BswUsuarios.findByCodUsuario", BswUsuarios.class).setParameter("codUsuario", event.getNewValue().toString()).getSingleResult();

            } catch (Exception ex) {
                sucursalABM = null;
                mensajeAlerta("Codigo de sucursal especificado no existe...");

                logger.error("Error al consultar sucursales", ex);
            }
        }
    }

    /**
     * @return the usuariosABM
     */
    public BswUsuarios getUsuariosABM() {
        if (usuariosABM == null) {
            usuariosABM = new BswUsuarios();
            if (usuariosABM.getBswPersonas() == null) {
                usuariosABM.setBswPersonas(new BswPersonas());
            }
        }
        return usuariosABM;
    }

    /**
     * @param usuariosABM the usuariosABM to set
     */
    public void setUsuariosABM(BswUsuarios usuariosABM) {
        this.usuariosABM = usuariosABM;
    }

    public void usuariosChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                usuariosABM = (BswUsuarios) em.createNativeQuery("select * from bsw_usuarios where id_empresa = ?  and cod_usuario= ? ",
                        BswUsuarios.class).setParameter(1, getEmpresaLogueada().getId()).setParameter(2, event.getNewValue()).getSingleResult();

            } catch (Exception ex) {
                usuariosABM = null;
                mensajeAlerta("Codigo de usuario especificado no existe...");

                logger.error("Error al consultar sucursales", ex);
            }
        }
    }

    public GenericLazyListNew getgLL() {
        if (gLL == null) {
            gLL = new GenericLazyListNew(em, BswUsuarios.class);
        }
        return gLL;
    }

    public void setgLL(GenericLazyListNew gLL) {
        this.gLL = gLL;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswInstituciones">
    
    public List<BswEmpresas> obtieneListaEmpresasViculadas(BswGrupoEmpresasUsuarios grupoEmpresa) {
        campos = new HashMap();
        if (grupoEmpresa != null) {
            campos.put("bswGrupoEmpresas.id", grupoEmpresa.getId());
            getGenericQuery().setEm(em);
            return getGenericQuery().getSearchResultList(campos, BswEmpresas.class);
        } else {
            return null;
        }

    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswBarrios">
    private GenericLazyListNew genericLazyListBarrio;
    private BswBarrios bswBarriosABM;
    private LazyDataModel lazyListBarrio;

    /**
     * @return the bswModulosABM
     */
    public BswBarrios getBswBarriosABM() {
        if (bswBarriosABM == null) {
            bswBarriosABM = new BswBarrios();
        }
        return bswBarriosABM;
    }

    /**
     * @param bswModulosABM the bswModulosABM to set
     */
    public void setBswBarriosABM(BswBarrios bswBarriosABM) {
        this.bswBarriosABM = bswBarriosABM;
    }

    public GenericLazyListNew getGenericLazyListBarrio() {
        if (genericLazyListBarrio == null) {
            genericLazyListBarrio = new GenericLazyListNew(em, BswBarrios.class);
        }
        return genericLazyListBarrio;
    }

    public void setGenericLazyListBarrio(GenericLazyListNew genericLazyListBarrio) {
        this.genericLazyListBarrio = genericLazyListBarrio;
    }

    public LazyDataModel getLazyListBarrio() {
        if (lazyListBarrio == null) {
            obtieneLazyListBarrio();
        }

        return lazyListBarrio;
    }

    public void setLazyListBarrio(LazyDataModel lazyListBarrio) {
        this.lazyListBarrio = lazyListBarrio;
    }

    public void obtieneLazyListBarrio() {
        campos = new HashMap();
        lazyListBarrio = this.getGenericLazyListBarrio().getLazyDataModel(BswBarrios.class, campos);
    }

    public void barrioChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                bswBarriosABM = (BswBarrios) em.createNamedQuery("BswBarrios.findByCodBarrio",
                        BswBarrios.class).setParameter("codBarrio", event.getNewValue().toString()).getSingleResult();
            } catch (Exception ex) {
                bswBarriosABM = null;
                mensajeAlerta("Codigo de Barrio especificado no existe...");
                logger.error("Error al consultar Barrio", ex);
            }
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswProvincias">
    private GenericLazyListNew genericLazyListProvincia;
    private LazyDataModel lazyListProvincia;
    private BswProvincias bswProvinciasABM;

    public BswProvincias getBswProvinciasABM() {
        if (bswProvinciasABM == null) {
            bswProvinciasABM = new BswProvincias();
        }
        return bswProvinciasABM;
    }

    public void setBswProvinciasABM(BswProvincias bswProvinciasABM) {
        this.bswProvinciasABM = bswProvinciasABM;
    }

    public GenericLazyListNew getGenericLazyListProvincia() {
        if (genericLazyListProvincia == null) {
            genericLazyListProvincia = new GenericLazyListNew(em, BswProvincias.class);
        }
        return genericLazyListProvincia;
    }

    public void setGenericLazyListProvincia(GenericLazyListNew genericLazyListProvincia) {
        this.genericLazyListProvincia = genericLazyListProvincia;
    }

    public List<BswProvincias> obtieneListaBswProvincias() {
        try {
            genericQuery.setEm(em);
            campos = new HashMap();
            return genericQuery.getSearchResultList(campos, BswProvincias.class);
        } catch (Exception e) {
        }
        return null;
    }

    public LazyDataModel getLazyListProvincia() {
        if (lazyListProvincia == null) {
            obtieneLazyListProvincia();
        }

        return lazyListProvincia;
    }

    public void setLazyListProvincia(LazyDataModel lazyListProvincia) {
        this.lazyListProvincia = lazyListProvincia;
    }

    public void obtieneLazyListProvincia() {
        campos = new HashMap();
        lazyListProvincia = this.getGenericLazyListProvincia().getLazyDataModel(BswProvincias.class, campos);
    }

    public void provinciaChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                bswProvinciasABM = (BswProvincias) em.createNamedQuery("BswProvincias.findByCodProvincia",
                        BswProvincias.class).setParameter("codProvincia", event.getNewValue().toString()).getSingleResult();
            } catch (Exception ex) {
                bswProvinciasABM = null;
                mensajeAlerta("Codigo de persona especificado no existe...");
                logger.error("Error al consultar personas", ex);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswPaises">
    private GenericLazyListNew<BswPaises> genericLazyListPais;
    private LazyDataModel lazyListPais;
    private BswPaises bswPaisesABM;

    public GenericLazyListNew<BswPaises> getGenericLazyListPais() {
        if (genericLazyListPais == null) {
            genericLazyListPais = new GenericLazyListNew(em, BswPaises.class);
        }
        return genericLazyListPais;
    }

    public void setGenericLazyListPais(GenericLazyListNew<BswPaises> genericLazyListPais) {
        this.genericLazyListPais = genericLazyListPais;
    }

    /**
     * @return the bswModulosABM
     */
    public BswPaises getBswPaisesABM() {
        if (bswPaisesABM == null) {
            bswPaisesABM = new BswPaises();
        }
        return bswPaisesABM;
    }

    /**
     * @param bswModulosABM the bswModulosABM to set
     */
    public void setBswPaisesABM(BswPaises bswPaisesABM) {
        this.bswPaisesABM = bswPaisesABM;
    }

    public LazyDataModel getLazyListPais() {
        if (lazyListPais == null) {
            obtieneLazyListPais();
        }

        return lazyListPais;
    }

    public void setLazyListPais(LazyDataModel lazyListPais) {
        this.lazyListPais = lazyListPais;
    }

    public void obtieneLazyListPais() {
        campos = new HashMap();
        lazyListPais = this.getGenericLazyListPais().getLazyDataModel(BswPaises.class, campos);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswModulos">
    private BswModulos bswModulosABM;
    private BswModulos bswModulosAux;
    private List<BswModulos> listaBswModulos;
    private GenericLazyListNew genericLazyListModulo;
    private LazyDataModel lazyListModulo;
    private LazyDataModel lazyListModuloTodos;

    public BswModulos getBswModulosAux() {
        if (bswModulosAux == null) {
            bswModulosAux = new BswModulos();
        }
        return bswModulosAux;
    }

    public void setBswModulosAux(BswModulos bswModulosAux) {
        this.bswModulosAux = bswModulosAux;
    }

    /**
     * @return the bswModulosABM
     */
    public BswModulos getBswModulosABM() {
        if (bswModulosABM == null) {
            bswModulosABM = new BswModulos();
        }
        return bswModulosABM;
    }

    /**
     * @param bswModulosABM the bswModulosABM to set
     */
    public void setBswModulosABM(BswModulos bswModulosABM) {
        this.bswModulosABM = bswModulosABM;
    }

    public List<BswModulos> obtieneListaBswModulos() {
        try {
            genericQuery.setEm(em);
            campos = new HashMap();
            campos.put("bswEmpresas.id", getEmpresaLogueada().getId());
            campos.put("estado", "A");
            campos.put("-1.operador:manejaCierre: is null ", " ");

            return genericQuery.getSearchResultList(campos, BswModulos.class);
        } catch (Exception e) {
        }
        return null;
    }

    public LazyDataModel getLazyListModulo() {
        if (lazyListModulo == null) {
            obtieneLazyListModulo();
        }

        return lazyListModulo;
    }

    public void setLazyListModulo(LazyDataModel lazyListModulo) {
        this.lazyListModulo = lazyListModulo;
    }

    public GenericLazyListNew getGenericLazyListModulo() {
        if (genericLazyListModulo == null) {
            genericLazyListModulo = new GenericLazyListNew(em, BswModulos.class);
        }
        return genericLazyListModulo;
    }

    public void setGenericLazyListModulo(GenericLazyListNew genericLazyListModulo) {
        this.genericLazyListModulo = genericLazyListModulo;
    }

    public void obtieneLazyListModulo() {
        campos = new HashMap();
        campos.put("bswEmpresas.id", getEmpresaLogueada().getId().toString());
        campos.put("-1.operador:manejaCierre: is null ", " ");
        lazyListModulo = this.getGenericLazyListModulo().getLazyDataModel(BswModulos.class, campos);
    }

    /**
     * @return the listaBswModulos
     */
    public List<BswModulos> getListaBswModulos() {
        if (listaBswModulos == null) {
            listaBswModulos = obtieneListaBswModulos();
        }
        return listaBswModulos;
    }

    /**
     * @param listaBswModulos the listaBswModulos to set
     */
    public void setListaBswModulos(List<BswModulos> listaBswModulos) {
        this.listaBswModulos = listaBswModulos;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswFormas">
    private GenericLazyListNew genericDetalleFormasList;
    private LazyDataModel lazyListDetalleFormas;
    private BswFormas bswFormasABM;

    public BswFormas getBswFormasABM() {

        if (bswFormasABM == null) {
            bswFormasABM = new BswFormas();
        }
        return bswFormasABM;
    }

    public void setBswFormasABM(BswFormas bswFormasABM) {
        this.bswFormasABM = bswFormasABM;
    }

    public LazyDataModel getLazyListDetalleFormas() {
        if (lazyListDetalleFormas == null) {
            obtieneLazyListFormas();
        }

        return lazyListDetalleFormas;
    }

    public void setLazyListDetalleFormas(LazyDataModel lazyListDetalleFormas) {
        this.lazyListDetalleFormas = lazyListDetalleFormas;
    }

    public GenericLazyListNew getGenericDetalleFormasList() {
        if (genericDetalleFormasList == null) {
            genericDetalleFormasList = new GenericLazyListNew(em, BswFormas.class);
        }
        return genericDetalleFormasList;
    }

    public void setGenericDetalleFormasList(GenericLazyListNew genericDetalleFormasList) {
        this.genericDetalleFormasList = genericDetalleFormasList;
    }

    public void obtieneLazyListFormas() {
        campos = new HashMap();
        lazyListDetalleFormas = this.getGenericDetalleFormasList().getLazyDataModel(BswFormas.class, campos);

    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswGruposUsuarios">
    private GenericLazyListNew genericGrupoUsuarioList;
    private LazyDataModel lazyListGrupoUsuario;
    private BswGruposUsuarios bswGruposUsuariosABM;

    public LazyDataModel getLazyListGrupoUsuario() {
        if (lazyListGrupoUsuario == null) {
            obtieneLazyListGrupoUsuario();
        }

        return lazyListGrupoUsuario;
    }

    public void setLazyListGrupoUsuario(LazyDataModel lazyListDetalleFormas) {
        this.lazyListGrupoUsuario = lazyListDetalleFormas;
    }

    public GenericLazyListNew getGenericGrupoUsuarioList() {
        if (genericGrupoUsuarioList == null) {
            genericGrupoUsuarioList = new GenericLazyListNew(em, BswGruposUsuarios.class);
        }
        return genericGrupoUsuarioList;
    }

    public void setGenericGrupoUsuarioList(GenericLazyListNew genericGrupoUsuarioList) {
        this.genericGrupoUsuarioList = genericGrupoUsuarioList;
    }

    public void obtieneLazyListGrupoUsuario() {
        campos = new HashMap();
//        if(getUsuarioLogueado().getIndVerificaMenu()==null ||  !getUsuarioLogueado().getIndVerificaMenu().equals("S"))
//            campos.put("-1.operador:codGrupo: != ", "'ADMIN'");
        campos.put("bswEmpresas.id", getEmpresaLogueada().getId().toString());
        lazyListGrupoUsuario = this.getGenericGrupoUsuarioList().getLazyDataModel(BswGruposUsuarios.class, campos);

    }

    public BswGruposUsuarios getBswGruposUsuariosABM() {
        if (bswGruposUsuariosABM == null) {
            bswGruposUsuariosABM = new BswGruposUsuarios();
        }
        return bswGruposUsuariosABM;
    }

    public void setBswGruposUsuariosABM(BswGruposUsuarios bswGruposUsuariosABM) {
        this.bswGruposUsuariosABM = bswGruposUsuariosABM;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswCiudades">
    private GenericLazyListNew genericLazyListCiudad;
    private List<BswCiudades> listaBswCiudades;
    private BswCiudades bswCiudadesABM;
    private LazyDataModel lazyListCiudad;
    protected BswCiudades ciudadSeleccionado;

    public GenericLazyListNew getGenericLazyListCiudad() {
        if (genericLazyListCiudad == null) {
            genericLazyListCiudad = new GenericLazyListNew(em, BswCiudades.class);
        }
        return genericLazyListCiudad;
    }

    public void setGenericLazyListCiudad(GenericLazyListNew genericLazyListCiudad) {
        this.genericLazyListCiudad = genericLazyListCiudad;
    }

    public BswCiudades getCiudadSeleccionado() {
        if (ciudadSeleccionado == null) {
            ciudadSeleccionado = new BswCiudades();
        }
        return ciudadSeleccionado;
    }

    public void setCiudadSeleccionado(BswCiudades ciudadSeleccionado) {
        this.ciudadSeleccionado = ciudadSeleccionado;
    }

    public LazyDataModel getLazyListCiudad() {
        if (lazyListCiudad == null) {
            obtieneLazyListCiudad();
        }

        return lazyListCiudad;
    }

    public void setLazyListCiudad(LazyDataModel lazyListCiudad) {
        this.lazyListCiudad = lazyListCiudad;
    }

    public void obtieneLazyListCiudad() {
        campos = new HashMap();
        lazyListCiudad = this.getGenericLazyListCiudad().getLazyDataModel(BswCiudades.class, campos);
    }

    public BswCiudades getBswCiudadesABM() {
        if (bswCiudadesABM == null) {
            bswCiudadesABM = new BswCiudades();
        }
        return bswCiudadesABM;
    }

    /**
     * @param bswModulosABM the bswModulosABM to set
     */
    public void setBswCiudadesABM(BswCiudades bswCiudadesABM) {
        this.bswCiudadesABM = bswCiudadesABM;
    }

    public List<BswCiudades> obtieneListaBswCiudades() {
        try {
            genericQuery.setEm(em);
            campos = new HashMap();
            return genericQuery.getSearchResultList(campos, BswCiudades.class);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * @return the listaBswCiudades
     */
    public List<BswCiudades> getListaBswCiudades() {
        if (listaBswCiudades == null) {
            listaBswCiudades = obtieneListaBswCiudades();
        }
        return listaBswCiudades;
    }

    /**
     * @param listaBswCiudades the listaBswCiudades to set
     */
    public void setListaBswCiudades(List<BswCiudades> listaBswCiudades) {
        this.listaBswCiudades = listaBswCiudades;
    }

    public void ciudadesChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                bswCiudadesABM = (BswCiudades) em.createNamedQuery("BswCiudades.findByCodCiudad",
                        BswCiudades.class).setParameter("codCiudad", event.getNewValue().toString()).getSingleResult();
            } catch (Exception ex) {
                bswCiudadesABM = null;
                mensajeAlerta("Codigo de persona especificado no existe...");
                logger.error("Error al consultar personas", ex);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswMonedas">
    private BswMonedas monedasABM;
    private List<BswMonedas> listaMonedas;
    private GenericLazyListNew genericLazyListMonedas;
    private LazyDataModel lazyListMoneda;

    public GenericLazyListNew getGenericLazyListMonedas() {
        if (genericLazyListMonedas == null) {
            this.genericLazyListMonedas = new GenericLazyListNew(em, BswMonedas.class);
        }
        return genericLazyListMonedas;
    }

    public void setGenericLazyListMonedas(GenericLazyListNew genericLazyListMonedas) {
        this.genericLazyListMonedas = genericLazyListMonedas;
    }

    public LazyDataModel getLazyListMoneda() {
        if (lazyListMoneda == null) {
            obtieneLazyListMoneda();
        }
        return lazyListMoneda;
    }

    public void setLazyListMoneda(LazyDataModel lazyListMoneda) {
        this.lazyListMoneda = lazyListMoneda;
    }

    public void obtieneLazyListMoneda() {
        campos = new HashMap();
        lazyListMoneda = getGenericLazyListMonedas().getLazyDataModel(BswMonedas.class, campos);
    }

    public void monedaChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            try {
                campos = new HashMap();
                campos.put("codMoneda", event.getNewValue().toString());
                genericQuery.setEm(em);
                monedasABM = (BswMonedas) genericQuery.getSearchSingleResult(campos, BswMonedas.class, null);

            } catch (Exception ex) {
                setMonedasABM(null);
                mensajeAlerta("No existe el codigo de Moneda");
                logger.error("Error al consultar moneda", ex);
            }
        }
    }

    public BswMonedas obtieneMonedaCodigo(String cod) {
        campos = new HashMap();
        campos.put("codMoneda", cod);
        genericQuery.setEm(em);
        return (BswMonedas) genericQuery.getSearchSingleResult(campos, BswMonedas.class, null);

    }

    /**
     * @return the motivoESABM
     */
    public BswMonedas getMonedasABM() {
        if (monedasABM == null) {
            monedasABM = new BswMonedas();
        }
        return monedasABM;
    }

    /**
     * @param monedaABM
     */
    public void setMonedasABM(BswMonedas monedasABM) {

        this.monedasABM = monedasABM;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswPersonas">
    private BswPersonas bswPersonas;

    /**
     * @return the bswPersonas
     */
    public BswPersonas getBswPersonas() {
        if (bswPersonas == null) {
            bswPersonas = new BswPersonas();
        }
        return bswPersonas;
    }

    /**
     * @param bswPersonas the bswPersonas to set
     */
    public void setBswPersonas(BswPersonas bswPersonas) {
        this.bswPersonas = bswPersonas;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Mensajes">
    protected FacesMessage facesMsg;

    protected void mensajeExito(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeAlerta(String mensaje) {

        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    protected void mensajeError(String mensaje) {
        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Sucursales">
    private BswSucursales sucursalListaSeleccionado;
    private BswSucursales sucursalFiltro;
    private BswSucursales sucursalFiltroC;
    private List<BswSucursales> sucursalesList;
    private BswSucursales sucursalABM;
    private BswSucursales establecimientoABM;
    private BswSucursales sucursalTodos = new BswSucursales();
    private GenericLazyListNew genericlazyListSucursal;
    private LazyDataModel lazyListSucursal;

    public GenericLazyListNew getGenericlazyListSucursal() {
        if (genericlazyListSucursal == null) {
            genericlazyListSucursal = new GenericLazyListNew(em, BswSucursales.class);
        }
        return genericlazyListSucursal;
    }

    public void setGenericlazyListSucursal(GenericLazyListNew genericlazyListSucursal) {
        this.genericlazyListSucursal = genericlazyListSucursal;
    }

    public LazyDataModel getlazyListSucursal() {
        if (lazyListSucursal == null) {
            obtienelazyListSucursal();
        }

        return lazyListSucursal;
    }

    public void setlazyListSucursal(LazyDataModel lazyListSucursal) {
        this.lazyListSucursal = lazyListSucursal;
    }

    public void obtienelazyListSucursal() {
        campos = new HashMap();
        lazyListSucursal = this.getGenericlazyListSucursal().getLazyDataModel(BswSucursales.class, campos);
    }

    /**
     * @return the sucursalABM
     */
    public BswSucursales getSucursalABM() {

        if (sucursalABM == null) {
            sucursalABM = new BswSucursales();
        }
        return sucursalABM;
    }

    /**
     * @param sucursalABM the sucursalABM to set
     */
    public void setSucursalABM(BswSucursales sucursalABM) {
        this.sucursalABM = sucursalABM;
    }

    public BswSucursales getEstablecimientoABM() {
        if (establecimientoABM == null) {
            establecimientoABM = new BswSucursales();
        }
        return establecimientoABM;
    }

    public void setEstablecimientoABM(BswSucursales establecimientoABM) {
        this.establecimientoABM = establecimientoABM;
    }

    public BswSucursales getSucursalFiltroC() {
        return sucursalFiltroC;
    }

    public void setSucursalFiltroC(BswSucursales sucursalFiltroC) {
        this.sucursalFiltroC = sucursalFiltroC;
    }

    /**
     * @return the sucursalListaSeleccionado
     */
    public BswSucursales getSucursalListaSeleccionado() {
        return sucursalListaSeleccionado;
    }

    /**
     * @param sucursalListaSeleccionado the sucursalListaSeleccionado to set
     */
    public void setSucursalListaSeleccionado(BswSucursales sucursalListaSeleccionado) {
        if (sucursalListaSeleccionado != null) {
            sucursalABM = sucursalListaSeleccionado;
        }
        this.sucursalListaSeleccionado = sucursalListaSeleccionado;
    }
    private List<BswSucursales> establecimientoList;
    private List<BswSucursales> financieroList;

    public List<BswSucursales> getEstablecimientoList() {
        try {
            establecimientoList = em.createNamedQuery("BswSucursales.findAllByEmpAndFinaAndsucH").setParameter("emp", getEmpresaLogueada()).getResultList();
        } catch (Exception e) {
            logger.error("Error al listar sucursales", e);
            setEstablecimientoList(new ArrayList<BswSucursales>());
        }
        return establecimientoList;
    }

    public void setEstablecimientoList(List<BswSucursales> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    /**
     * @return the sucursalesList
     */
    public List<BswSucursales> getSucursalesList() {
        try {
            sucursalesList = em.createNamedQuery("BswSucursales.findAllByEmpresa").setParameter("emp", getEmpresaLogueada()).getResultList();
        } catch (Exception e) {
            logger.error("Error al listar sucursales", e);
            setSucursalesList(new ArrayList<BswSucursales>());
        }
        return sucursalesList;
    }

    /**
     * @param sucursalesList the sucursalesList to set
     */
    public void setSucursalesList(List<BswSucursales> sucursalesList) {
        this.sucursalesList = sucursalesList;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Lazy Opcion Parametro">
    private LazyDataModel lazyListParametro;
    private GenericLazyListNew gLLParametro;
    private BswOpcionesParametros bswOpcionesParametrosABM;

    public GenericLazyListNew getgLLParametro() {
        if (gLLParametro == null) {
            gLLParametro = new GenericLazyListNew(em, BswOpcionesParametros.class);
        }
        return gLLParametro;
    }

    public void setgLLParametro(GenericLazyListNew gLLParametro) {
        this.gLLParametro = gLLParametro;
    }

    public LazyDataModel getLazyListParametro() {
        HashMap campos = new HashMap();
        return lazyListParametro = getgLLParametro().getLazyDataModel(BswOpcionesParametros.class, campos);
    }

    public void setLazyListParametro(LazyDataModel lpb) {
        this.lazyListParametro = lpb;
    }

    public BswOpcionesParametros getBswOpcionesParametrosABM() {
        if (bswOpcionesParametrosABM == null) {
            bswOpcionesParametrosABM = new BswOpcionesParametros();
        }
        return bswOpcionesParametrosABM;
    }

    public void setBswOpcionesParametrosABM(BswOpcionesParametros parametro) {

        this.bswOpcionesParametrosABM = parametro;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswIdentificaciones">
    private GenericLazyListNew genericlazyListIdentificaciones;
    private LazyDataModel lazyListIdentificaciones;
    private BswIdentificaciones bswIdentificacionesAbm;

    public BswIdentificaciones getBswIdentificacionesAbm() {
        if (bswIdentificacionesAbm == null) {
            bswIdentificacionesAbm = new BswIdentificaciones();
        }
        return bswIdentificacionesAbm;
    }

    public void setBswIdentificacionesAbm(BswIdentificaciones bswIdentificacionesAbm) {
        this.bswIdentificacionesAbm = bswIdentificacionesAbm;
    }

    public GenericLazyListNew getGenericlazyListIdentificaciones() {
        if (genericlazyListIdentificaciones == null) {
            genericlazyListIdentificaciones = new GenericLazyListNew(em, BswIdentificaciones.class);
        }
        return genericlazyListIdentificaciones;
    }

    public void setGenericlazyListIdentificaciones(GenericLazyListNew genericlazyListIdentificaciones) {
        this.genericlazyListIdentificaciones = genericlazyListIdentificaciones;
    }

    public LazyDataModel getLazyListIdentificaciones() {
        if (lazyListIdentificaciones == null) {
            obtieneLazyListIdentificaciones();
        }

        return lazyListIdentificaciones;
    }

    public void setLazyListIdentificaciones(LazyDataModel lazyListIdentificaciones) {
        this.lazyListIdentificaciones = lazyListIdentificaciones;
    }

    public void obtieneLazyListIdentificaciones() {
        campos = new HashMap();
        lazyListIdentificaciones = this.getGenericlazyListIdentificaciones().getLazyDataModel(BswIdentificaciones.class, campos);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswRolUsuarioSucursal">
    private LazyDataModel lazyListRolUsuarioSucursal;
    private GenericLazyListNew genericRolUsuarioSucursalList;

    public LazyDataModel getLazyListRolUsuarioSucursal() {
        if (lazyListRolUsuarioSucursal == null) {
            obtieneLazyListRolUsuarioSucursal();
        }
        return lazyListRolUsuarioSucursal;
    }

    public void obtieneLazyListRolUsuarioSucursal() {
        HashMap camposReservaArt = new HashMap();
        lazyListRolUsuarioSucursal = this.genericRolUsuarioSucursalList.getLazyDataModel(BswRolUsuarioSucursal.class, camposReservaArt);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswSession">
    private GenericLazyListNew<BswSession> genericLazyListSession;
    private LazyDataModel lazyListSession;
    private BswSession sessionABM;

    public GenericLazyListNew<BswSession> getGenericLazyListSession() {
        if (genericLazyListSession == null) {
            genericLazyListSession = new GenericLazyListNew(em, BswSession.class);
        }
        return genericLazyListSession;
    }

    public void setGenericLazyListSession(GenericLazyListNew<BswSession> genericLazyListSession) {
        this.genericLazyListSession = genericLazyListSession;
    }

    public BswSession getBswSessionABM() {
        if (sessionABM == null) {
            sessionABM = new BswSession();
        }
        return sessionABM;
    }

    public void setBswSessionABM(BswSession bswSessionesABM) {
        this.sessionABM = bswSessionesABM;
    }

    public LazyDataModel getLazyListSession() {
        if (lazyListSession == null) {
            obtieneLazyListSession();
        }

        return lazyListSession;
    }

    public void setLazyListSession(LazyDataModel lazyListSession) {
        this.lazyListSession = lazyListSession;
    }

    public void obtieneLazyListSession() {
        campos = new HashMap();
        campos.put("WHERE", "1=1");
        campos.put("-1.operador:id:  BY ", " desc");
        lazyListSession = this.getGenericLazyListSession().getLazyDataModel(BswSession.class, campos);
    }

    public void sessionChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
            campos = new HashMap();
            try {
                campos.put("codSession", event.getNewValue().toString());
                sessionABM = (BswSession) genericQuery.getSearchSingleResult(campos, BswSession.class, null);
                if (sessionABM == null) {
                    mensajeAlerta("Codigo Session especificado no existe...");
                }

            } catch (Exception ex) {
                sessionABM = null;
                mensajeAlerta("Codigo de Session especificado no existe...");
                logger.error("Error al consultar Session", ex);
            }
        }
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="BswParametrosGenerales">
    private BswParametrosGenerales bswParametrosGeneralesABM;
    private List<BswParametrosGenerales> listaBswParametrosGenerales;
    private GenericLazyListNew genericLazyListParametrosGenerales;
    private LazyDataModel lazyListParametrosGenerales;

    public GenericLazyListNew getGenericLazyListParametrosGenerales() {
        if (genericLazyListParametrosGenerales == null) {
            genericLazyListParametrosGenerales = new GenericLazyListNew(em, BswParametrosGenerales.class);
        }
        return genericLazyListParametrosGenerales;
    }

    public void setGenericLazyListParametrosGenerales(GenericLazyListNew genericLazyListParametrosGenerales) {
        this.genericLazyListParametrosGenerales = genericLazyListParametrosGenerales;
    }

    public LazyDataModel getLazyListParametrosGenerales() {
        if (lazyListParametrosGenerales == null) {
            obtieneLazyListParametrosGenerales();
        }
        return lazyListParametrosGenerales;
    }

    public void setLazyListParametrosGenerales(LazyDataModel lazyListParametrosGenerales) {
        this.lazyListParametrosGenerales = lazyListParametrosGenerales;
    }

    public void obtieneLazyListParametrosGenerales() {
        campos = new HashMap();
        lazyListParametrosGenerales
                = this.getGenericLazyListParametrosGenerales().getLazyDataModel(BswParametrosGenerales.class, campos);
    }

    public List<BswParametrosGenerales> obtieneListaBswParametrosGenerales() {
        try {
            campos = new HashMap();
            genericQuery.setEm(em);
            return genericQuery.getSearchResultList(campos, BswParametrosGenerales.class);
        } catch (Exception e) {
        }
        return null;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Tipos Forma">
    private List<BswTipoForma> bswTipoFormaList;
    private List<BswFormas> bswFormaList;

    public List<BswTipoForma> getBswTipoFormaList() {
        if (bswTipoFormaList == null) {
            bswTipoFormaList = obtieneTipoFormaList();
        }
        return bswTipoFormaList;
    }

    public void setBswTipoFormaList(List<BswTipoForma> bswTipoFormaList) {
        this.bswTipoFormaList = bswTipoFormaList;
    }

    private List<BswTipoForma> obtieneTipoFormaList() {
        try {
            campos = new HashMap();
            genericQuery.setEm(em);
            return genericQuery.getSearchResultList(campos, BswTipoForma.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Lazy list Accesos">
    private LazyDataModel lazyListAccesos;
    private GenericLazyListNew gLLA;

    public LazyDataModel getLazyListAccesos() {
        if (lazyListAccesos == null) {
            lazyListAccesos = obtieneLazyListAccesoGrupos();
        }
        return lazyListAccesos;
    }

    public void setLazyListAccesos(LazyDataModel lpb) {
        this.lazyListAccesos = lpb;
    }

    public GenericLazyListNew getgLLA() {
        if (gLLA == null) {
            gLLA = new GenericLazyListNew(em, BswAccesosGrupos.class);
        }
        return gLLA;
    }

    public void setgLLA(GenericLazyListNew gLLA) {
        this.gLLA = gLLA;
    }

    public LazyDataModel obtieneLazyListAccesoGrupos() {
        HashMap camposAcc = new HashMap();
        return this.getgLLA().getLazyDataModel(BswAccesosGrupos.class, camposAcc);

    }
//</editor-fold>
    ///<editor-fold defaultstate="collapsed" desc="Lazy List Personas">
    private GenericLazyListNew genericDetallePersonasList;
    private LazyDataModel lazyListDetallePersonas;

    public LazyDataModel getLazyListDetallePersonas() {
        if (lazyListDetallePersonas == null) {
            obtieneLazyListPersonas();
        }
        return lazyListDetallePersonas;
    }

    public GenericLazyListNew getGenericDetallePersonasList() {
        if (genericDetallePersonasList == null) {
            genericDetallePersonasList = new GenericLazyListNew(em, BswPersonas.class);
        }
        return genericDetallePersonasList;
    }

    public void setGenericDetallePersonasList(GenericLazyListNew genericDetallePersonasList) {
        this.genericDetallePersonasList = genericDetallePersonasList;
    }

    public void setLazyListDetallePersonas(LazyDataModel lazyListDetallePersonas) {
        this.lazyListDetallePersonas = lazyListDetallePersonas;
    }

    public void obtieneLazyListPersonas() {
        campos = new HashMap();
        campos.put("bswEmpresas.id", getEmpresaLogueada().getId().toString());
        if (getBswModulosABM() != null && getBswModulosABM().getId() != null) {
            campos.put("bswModulos.id", getBswModulosABM().getId().toString());
            campos.put("tipo", "P");
        }
        campos.put("WHERE", "1=1");
        campos.put("-1.operador:nombre:  BY ", " asc");
        lazyListDetallePersonas = this.getGenericDetallePersonasList().getLazyDataModel(BswPersonas.class, campos);

    }
    //</editor-fold>
    
    private LazyDataModel lazyListNivelEstudio;

    public LazyDataModel getLazyListNivelEstudio() {
        if (lazyListNivelEstudio == null) {
            obtieneLazyListNivelEstudio();
        }

        return lazyListNivelEstudio;
    }

    public void obtieneLazyListNivelEstudio() {
        campos = new HashMap();
        lazyListNivelEstudio = this.getGenericNivelEstudioList().getLazyDataModel(BswNivelEstudios.class, campos);

    }

    private GenericLazyListNew genericNivelEstudioList;

    public GenericLazyListNew getGenericNivelEstudioList() {
        if (genericNivelEstudioList == null) {
            genericNivelEstudioList = new GenericLazyListNew(em, BswNivelEstudios.class);
        }
        return genericNivelEstudioList;
    }

    private BswNivelEstudios bswNivelEstudioABM;

    public BswNivelEstudios getBswNivelEstudioABM() {
        if (bswNivelEstudioABM == null) {
            bswNivelEstudioABM = new BswNivelEstudios();
        }
        return bswNivelEstudioABM;
    }

    public void setBswNivelEstudioABM(BswNivelEstudios bswNivelEstudioABM) {
        this.bswNivelEstudioABM = bswNivelEstudioABM;
    }

    private BswPersonas represantLegalABM;

    public BswPersonas getRepresantLegalABM() {
        if (represantLegalABM == null) {
            represantLegalABM = new BswPersonas();
        }
        return represantLegalABM;
    }

    public void setRepresantLegalABM(BswPersonas represantLegalABM) {
        this.represantLegalABM = represantLegalABM;
    }

    //
    private LazyDataModel lazyListSectorEcon;

    public LazyDataModel getLazyListSectorEcon() {
        if (lazyListSectorEcon == null) {
            obtieneLazyListSectorEcon();
        }

        return lazyListSectorEcon;
    }

    public void obtieneLazyListSectorEcon() {
        campos = new HashMap();
        lazyListSectorEcon = this.getGenericSectorEconList().getLazyDataModel(BswSectoresEcon.class, campos);
    }

    private GenericLazyListNew genericSectorEconList;

    public GenericLazyListNew getGenericSectorEconList() {
        if (genericSectorEconList == null) {
            genericSectorEconList = new GenericLazyListNew(em, BswSectoresEcon.class);
        }
        return genericSectorEconList;
    }
    
    
    private BswPersonas responsableABM;

    public BswPersonas getResponsableABM() {
        if (responsableABM == null) {
            responsableABM = new BswPersonas();
        }
        return responsableABM;
    }

    public void setResponsableABM(BswPersonas responsableABM) {
        this.responsableABM = responsableABM;
    }
    
    //
    private BswUsuarios usuarioABM;

    public BswUsuarios getUsuarioABM() {
        if (usuarioABM == null) {
            usuarioABM = new BswUsuarios();
        }
        return usuarioABM;
    }

    public void setUsuarioABM(BswUsuarios usuarioABM) {
        this.usuarioABM = usuarioABM;
    }
    
   //
    private BswPersonas personaParticipanteABM;

    public BswPersonas getPersonaParticipanteABM() {
        if (personaParticipanteABM == null) {
            personaParticipanteABM = new BswPersonas();
        }
        return personaParticipanteABM;
    }

    public void setPersonaParticipanteABM(BswPersonas personaParticipanteABM) {
        this.personaParticipanteABM = personaParticipanteABM;
    }
    
}
