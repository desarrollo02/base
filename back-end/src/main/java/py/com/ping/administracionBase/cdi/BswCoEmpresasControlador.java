/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.ping.administracionBase.jpa.BswEmpresas;
import py.com.ping.administracionBase.jpa.BswSucursales;
import py.com.ping.administracionBase.util.ApplicationConstant;
//import py.com.ping.contabilidad.cdi.CowPlanCuentasControlador;
//import py.com.ping.contabilidad.jpa.CowPlanCuentas;
import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author josema
 */
@Named
@ViewScoped
public class BswCoEmpresasControlador extends AbstractControllerGenerico<BswEmpresas> {

    private static final Logger log =
            LoggerFactory.getLogger(BswCoEmpresasControlador.class);

    private BswEmpresas empresaABM;
    private Boolean planCuenta;
    private Boolean sucursal;

    @Override
    public void setParametros() {
        this.clase = BswEmpresas.class;
        this.formName = ApplicationConstant.nombreFormaEmpresaBase;
        this.paginaActual = "BswEmpresas.xhtml";

        if (acercaDe.isEmpty()) {
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Forma:BSEMPRES");
            getAcercaDe().add("- Controlador: BswCoEmpresasControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswEmpresas.java");
        }

        if (ayuda.isEmpty()) {
            getAyuda().add("Esta es la pantalla puede ser utilizada para agregar, modificar o eliminar Modulos.");
            getAyuda().add("");
            getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
            getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
            getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
        }
    }

    @Override
    protected void antesABM() {

    }

    @Override
    public void cargarCamposRelacionados() {

    }

    @Override
    public void limpiarDatosLocales() {

    }

    public BswEmpresas getEmpresaABM() {
        return empresaABM;
    }

    public void setEmpresaABM(BswEmpresas empresaABM) {
        this.empresaABM = empresaABM;
    }

    public Boolean getPlanCuenta() {
        return planCuenta;
    }

    public void setPlanCuenta(Boolean planCuenta) {
        this.planCuenta = planCuenta;
    }

    public Boolean getSucursal() {
        return sucursal;
    }

    public void setSucursal(Boolean sucursal) {
        this.sucursal = sucursal;
    }

    @Inject
//    CowPlanCuentasControlador cowPlanCuentasControlador;
//    List<CowPlanCuentas> ex = new ArrayList<>();

    public void clonarEmpresa() {
        empresaABM.setId(null);
        empresaABM.setCodEmpresa(getMaxCodEmpresa().toString());
        if (sucursal) {
            empresaABM.setBswSucursalesSet(getSucursal(empresaABM.getBswSucursalesSet()));
        } else {
            empresaABM.setBswSucursalesSet(null);
        }
        setActual(empresaABM);

//        for (CowPlanCuentas plan : getEmpresaABM().getCowPlanCuentasList()) {
//            if (plan.getCowPlanCueTotalizadora() == null) {
//                CowPlanCuentas cowPlanCuentas;
//                plan.setId(null);
//                plan.setCowPlanCuentasList(getlista2(plan.getCowPlanCuentasList()));
//                plan.setBswEmpresas(getActual());
//                cowPlanCuentas = plan;
//                ex.add(cowPlanCuentas);
//            }
//
//        }
        this.grabar();
        empresaABM = null;

    }

    @Override
    protected void despuesInsertar() throws Exception {
        setObjetoAgregado(getActual());
//        if (planCuenta) {
//            for (CowPlanCuentas cowPlanCuentas : ex) {
//                cowPlanCuentas.setBswEmpresas(getObjetoAgregado());
//                cowPlanCuentasControlador.setActual(cowPlanCuentas);
//                cowPlanCuentasControlador.grabar();
//            }
//        }
//        ex.clear();
        cargarDatosNuevoObjeto();
        mensajeExito("Los datos fueron guardados correctamente!");
    }

    public List<BswSucursales> getSucursal(List<BswSucursales> list) {
        List<BswSucursales> lista = new ArrayList<>();
        for (BswSucursales sucu : list) {
            sucu.setId(null);
            sucu.setCodSucursal(getMaxCodSucursal().toString());
            lista.add(sucu);
        }
        return lista;
    }

//    public List<CowPlanCuentas> getlista2(List<CowPlanCuentas> list) {
//        List<CowPlanCuentas> lista = new ArrayList<>();
//        for (CowPlanCuentas d : list) {
//            d.setId(null);
//            d.setBswEmpresas(getActual());
//            d.setCowPlanCuentasList(getlista3(d.getCowPlanCuentasList()));
//            lista.add(d);
//        }
//        return lista;
//    }
//
//    public List<CowPlanCuentas> getlista3(List<CowPlanCuentas> list) {
//        List<CowPlanCuentas> lista = new ArrayList<>();
//        for (CowPlanCuentas d : list) {
//            d.setId(null);
//            d.setBswEmpresas(getActual());
//            d.setCowPlanCuentasList(getlista4(d.getCowPlanCuentasList()));
//            lista.add(d);
//        }
//        return lista;
//    }
//
//    public List<CowPlanCuentas> getlista4(List<CowPlanCuentas> list) {
//        List<CowPlanCuentas> lista = new ArrayList<>();
//        for (CowPlanCuentas d : list) {
//            d.setId(null);
//            d.setBswEmpresas(getActual());
//            d.setCowPlanCuentasList(getlista5(d.getCowPlanCuentasList()));
//            lista.add(d);
//        }
//        return lista;
//    }
//
//    public List<CowPlanCuentas> getlista5(List<CowPlanCuentas> list) {
//        List<CowPlanCuentas> lista = new ArrayList<>();
//        for (CowPlanCuentas d : list) {
//            d.setId(null);
//            d.setBswEmpresas(getActual());
//            lista.add(d);
//        }
//        return lista;
//    }

    public Integer getMaxCodSucursal() {
        try {

            String maxNumero = (String) getEm().createQuery("select MAX(s.codSucursal) from BswSucursales s").getSingleResult();
            return maxNumero != null ? (Integer.parseInt(maxNumero) + 1) : 1;

        } catch (Exception e) {
            log.error("Error al calcular el numero maximo", e);
            return 0;
        }
    }

    public Integer getMaxCodEmpresa() {
        try {

            String maxNumero = (String) getEm().createQuery("select MAX(e.codEmpresa) from BswEmpresas e").getSingleResult();
            return maxNumero != null ? (Integer.parseInt(maxNumero) + 1) : 1;

        } catch (Exception e) {
            log.error("Error al calcular el numero maximo", e);
            return 0;
        }
    }
}
