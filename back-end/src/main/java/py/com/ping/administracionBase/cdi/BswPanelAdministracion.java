package py.com.ping.administracionBase.cdi;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.ping.administracionBase.ejb.AccesosGruposEJB;
import py.com.ping.administracionBase.ejb.BswPanelAdministracionEJB;
import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswGruposUsuarios;
import py.com.ping.administracionBase.jpa.BswModulos;

/**
 *
 * @author josema
 */
@Named
@ViewScoped
public class BswPanelAdministracion implements Serializable {
    private BswEmpresas bswEmpresasOrigenABM;
    private BswEmpresas bswEmpresasDestinoABM;
    private BswEmpresas bswEmpresasOrigenPlanABM;
    private BswEmpresas bswEmpresasDestinoPlanABM;
    private BswEmpresas bswEmpresasOrigenAccesoABM;
    private BswEmpresas bswEmpresasDestinoAccesoABM;
    private BswEmpresas bswEmpresasOrigenEjerciciosABM;
    private BswEmpresas bswEmpresasDestinoEjerciciosABM;
    private BswEmpresas bswEmpresasOrigenCategoriaABM;
    private BswEmpresas bswEmpresasDestinoCategoriaABM;
    private BswEmpresas bswEmpresasOrigenPrwPeriodoCabABM;
    private BswEmpresas bswEmpresasDestinoPrwPeriodoCabABM;
    private BswEmpresas bswEmpresasOrigenPresupuestoABM;
    private BswEmpresas bswEmpresasDestinoPresupuestoABM;
    private BswEmpresas bswEmpresasOrigenRelacionesCuentasABM;
    private BswEmpresas bswEmpresasDestinoRelacionesCuentasABM;
    private BswEmpresas bswEmpresasOrigenModuloABM;
    private BswEmpresas bswEmpresasDestinoModuloABM;
    protected FacesMessage facesMsg;
    @EJB
    private BswPanelAdministracionEJB bswPanelAdministracionEJB;
    @EJB
    private AccesosGruposEJB accesosGruposEJB;
    @Inject
    private LoginControlador loginControlador;

    @PostConstruct
    public void InicializaValores() {

    }

    public BswEmpresas getBswEmpresasOrigenEjerciciosABM() {
        if (bswEmpresasOrigenEjerciciosABM == null) {
            bswEmpresasOrigenEjerciciosABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenEjerciciosABM;
    }

    public void setBswEmpresasOrigenEjerciciosABM(BswEmpresas bswEmpresasOrigenEjerciciosABM) {
        this.bswEmpresasOrigenEjerciciosABM = bswEmpresasOrigenEjerciciosABM;
    }

    public BswEmpresas getBswEmpresasDestinoEjerciciosABM() {
        if (bswEmpresasDestinoEjerciciosABM == null) {
            bswEmpresasDestinoEjerciciosABM = new BswEmpresas();
        }
        return bswEmpresasDestinoEjerciciosABM;
    }

    public void setBswEmpresasDestinoEjerciciosABM(BswEmpresas bswEmpresasDestinoEjerciciosABM) {
        this.bswEmpresasDestinoEjerciciosABM = bswEmpresasDestinoEjerciciosABM;
    }

    public BswEmpresas getBswEmpresasOrigenPlanABM() {
        if (bswEmpresasOrigenPlanABM == null) {
            bswEmpresasOrigenPlanABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenPlanABM;
    }

    public void setBswEmpresasOrigenPlanABM(BswEmpresas bswEmpresasOrigenPlanABM) {
        this.bswEmpresasOrigenPlanABM = bswEmpresasOrigenPlanABM;
    }

    public BswEmpresas getBswEmpresasDestinoPlanABM() {
        if (bswEmpresasDestinoPlanABM == null) {
            bswEmpresasDestinoPlanABM = new BswEmpresas();
        }
        return bswEmpresasDestinoPlanABM;
    }

    public void setBswEmpresasDestinoPlanABM(BswEmpresas bswEmpresasDestinoPlanABM) {
        this.bswEmpresasDestinoPlanABM = bswEmpresasDestinoPlanABM;
    }

    public BswEmpresas getBswEmpresasOrigenAccesoABM() {
        if (bswEmpresasOrigenAccesoABM == null) {
            bswEmpresasOrigenAccesoABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenAccesoABM;
    }

    public void setBswEmpresasOrigenAccesoABM(BswEmpresas bswEmpresasOrigenAccesoABM) {
        this.bswEmpresasOrigenAccesoABM = bswEmpresasOrigenAccesoABM;
    }

    public BswEmpresas getBswEmpresasDestinoAccesoABM() {
        if (bswEmpresasDestinoAccesoABM == null) {
            bswEmpresasDestinoAccesoABM = new BswEmpresas();
        }
        return bswEmpresasDestinoAccesoABM;
    }

    public void setBswEmpresasDestinoAccesoABM(BswEmpresas bswEmpresasDestinoAccesoABM) {
        this.bswEmpresasDestinoAccesoABM = bswEmpresasDestinoAccesoABM;
    }

    public BswEmpresas getBswEmpresasOrigenCategoriaABM() {
        if (bswEmpresasOrigenCategoriaABM == null) {
            bswEmpresasOrigenCategoriaABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenCategoriaABM;
    }

    public void setBswEmpresasOrigenCategoriaABM(BswEmpresas bswEmpresasOrigenCategoriaABM) {
        this.bswEmpresasOrigenCategoriaABM = bswEmpresasOrigenCategoriaABM;
    }

    public BswEmpresas getBswEmpresasDestinoCategoriaABM() {
        if (bswEmpresasDestinoCategoriaABM == null) {
            bswEmpresasDestinoCategoriaABM = new BswEmpresas();
        }
        return bswEmpresasDestinoCategoriaABM;
    }

    public void setBswEmpresasDestinoCategoriaABM(BswEmpresas bswEmpresasDestinoCategoriaABM) {
        this.bswEmpresasDestinoCategoriaABM = bswEmpresasDestinoCategoriaABM;
    }

    public BswEmpresas getBswEmpresasOrigenPresupuestoABM() {
        if (bswEmpresasOrigenPresupuestoABM == null) {
            bswEmpresasOrigenPresupuestoABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenPresupuestoABM;
    }

    public void setBswEmpresasOrigenPresupuestoABM(BswEmpresas bswEmpresasOrigenPresupuestoABM) {
        this.bswEmpresasOrigenPresupuestoABM = bswEmpresasOrigenPresupuestoABM;
    }

     /**
     * @return the bswEmpresasDestinoRelacionesCuentasABM
     */
    public BswEmpresas getBswEmpresasDestinoRelacionesCuentasABM() {
        return bswEmpresasDestinoRelacionesCuentasABM;
    }

    /**
     * @param bswEmpresasDestinoRelacionesCuentasABM the bswEmpresasDestinoRelacionesCuentasABM to set
     */
    public void setBswEmpresasDestinoRelacionesCuentasABM(BswEmpresas bswEmpresasDestinoRelacionesCuentasABM) {
        this.bswEmpresasDestinoRelacionesCuentasABM = bswEmpresasDestinoRelacionesCuentasABM;
    }
    /**
     * @return the bswEmpresasOrigenRelacionesCuentasABM
     */
    public BswEmpresas getBswEmpresasOrigenRelacionesCuentasABM() {
        if (bswEmpresasOrigenRelacionesCuentasABM == null) {
            bswEmpresasOrigenRelacionesCuentasABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenRelacionesCuentasABM;
    }

    /**
     * @param bswEmpresasOrigenRelacionesCuentasABM the bswEmpresasOrigenRelacionesCuentasABM to set
     */
    public void setBswEmpresasOrigenRelacionesCuentasABM(BswEmpresas bswEmpresasOrigenRelacionesCuentasABM) {
        this.bswEmpresasOrigenRelacionesCuentasABM = bswEmpresasOrigenRelacionesCuentasABM;
    }

    public BswEmpresas getBswEmpresasDestinoPresupuestoABM() {
        if (bswEmpresasDestinoPresupuestoABM == null) {
            bswEmpresasDestinoPresupuestoABM = new BswEmpresas();
        }
        return bswEmpresasDestinoPresupuestoABM;
    }

    public void setBswEmpresasDestinoPresupuestoABM(BswEmpresas bswEmpresasDestinoPresupuestoABM) {
        this.bswEmpresasDestinoPresupuestoABM = bswEmpresasDestinoPresupuestoABM;
    }

    public BswEmpresas getBswEmpresasOrigenABM() {
        if (bswEmpresasOrigenABM == null) {
            bswEmpresasOrigenABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenABM;
    }

    public void setBswEmpresasOrigenABM(BswEmpresas bswEmpresasOrigenABM) {
        this.bswEmpresasOrigenABM = bswEmpresasOrigenABM;
    }

    public BswEmpresas getBswEmpresasDestinoABM() {
        if (bswEmpresasDestinoABM == null) {
            bswEmpresasDestinoABM = new BswEmpresas();
        }
        return bswEmpresasDestinoABM;
    }

    public void setBswEmpresasDestinoABM(BswEmpresas bswEmpresasDestinoABM) {
        this.bswEmpresasDestinoABM = bswEmpresasDestinoABM;
    }

    public BswEmpresas getBswEmpresasOrigenPrwPeriodoCabABM() {
        if (bswEmpresasOrigenPrwPeriodoCabABM == null) {
            bswEmpresasOrigenPrwPeriodoCabABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenPrwPeriodoCabABM;
    }

    public void setBswEmpresasOrigenPrwPeriodoCabABM(BswEmpresas bswEmpresasOrigenPrwPeriodoCabABM) {
        this.bswEmpresasOrigenPrwPeriodoCabABM = bswEmpresasOrigenPrwPeriodoCabABM;
    }

    public BswEmpresas getBswEmpresasDestinoPrwPeriodoCabABM() {
        if (bswEmpresasDestinoPrwPeriodoCabABM == null) {
            bswEmpresasDestinoPrwPeriodoCabABM = new BswEmpresas();
        }
        return bswEmpresasDestinoPrwPeriodoCabABM;
    }

    public void setBswEmpresasDestinoPrwPeriodoCabABM(BswEmpresas bswEmpresasDestinoPrwPeriodoCabABM) {
        this.bswEmpresasDestinoPrwPeriodoCabABM = bswEmpresasDestinoPrwPeriodoCabABM;
    }

    public BswEmpresas getBswEmpresasOrigenModuloABM() {
        if (bswEmpresasOrigenModuloABM == null) {
            bswEmpresasOrigenModuloABM = loginControlador.getEmpresaSeleccionada();
        }
        return bswEmpresasOrigenModuloABM;
    }

    public void setBswEmpresasOrigenModuloABM(BswEmpresas bswEmpresasOrigenModuloABM) {
        this.bswEmpresasOrigenModuloABM = bswEmpresasOrigenModuloABM;
    }

    public BswEmpresas getBswEmpresasDestinoModuloABM() {
        if (bswEmpresasDestinoModuloABM == null) {
            bswEmpresasDestinoModuloABM = new BswEmpresas();
        }
        return bswEmpresasDestinoModuloABM;
    }

    public void setBswEmpresasDestinoModuloABM(BswEmpresas bswEmpresasDestinoModuloABM) {
        this.bswEmpresasDestinoModuloABM = bswEmpresasDestinoModuloABM;
    }

    public void guardarGruposUsuarios() {
        if (getBswEmpresasOrigenABM() != null && getBswEmpresasOrigenABM().getId() != null && getBswEmpresasDestinoABM() != null && getBswEmpresasDestinoABM().getId() != null) {
            for (BswGruposUsuarios bswGruposUsuarios : getBswEmpresasOrigenABM().getBswGruposUsuariosList()) {
                bswPanelAdministracionEJB.insertar(bswGruposUsuarios, getBswEmpresasDestinoABM());
            }
        } else {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar Empresas", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

    public void guardarGruposAcceso() {
        if (getBswEmpresasOrigenAccesoABM() != null && getBswEmpresasOrigenAccesoABM().getId() != null && getBswEmpresasDestinoAccesoABM() != null && getBswEmpresasDestinoAccesoABM().getId() != null) {
            for (BswAccesosGrupos bswAccesosGrupos : getBswEmpresasOrigenAccesoABM().getBswAccesosGruposList()) {
                bswPanelAdministracionEJB.insertarAccesoGrupo(bswAccesosGrupos, getBswEmpresasDestinoAccesoABM(),getBswEmpresasOrigenAccesoABM());
            }
        } else {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar Empresas", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

    public void guardarCategoria() {
        if (getBswEmpresasOrigenCategoriaABM() != null && getBswEmpresasOrigenCategoriaABM().getId() != null && getBswEmpresasDestinoCategoriaABM() != null && getBswEmpresasDestinoCategoriaABM().getId() != null) {
            
        } else {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar Empresas", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

    public void guardarModulo() {
        if (getBswEmpresasOrigenModuloABM() != null && getBswEmpresasOrigenModuloABM().getId() != null && getBswEmpresasDestinoModuloABM() != null && getBswEmpresasDestinoModuloABM().getId() != null) {
            for (BswModulos bswModulos : getBswEmpresasOrigenModuloABM().getBswModulosList()) {
                bswPanelAdministracionEJB.insertarBswModulos(bswModulos, getBswEmpresasDestinoModuloABM());
            }
        } else {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar Empresas", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

}
