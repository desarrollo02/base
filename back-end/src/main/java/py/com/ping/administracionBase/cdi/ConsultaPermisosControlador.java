/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.ping.administracionBase.ejb.BswAccesosGruposEJB;
import py.com.ping.administracionBase.util.ApplicationConstant;

/**
 *
 * @author gdb <dominbla@gmail.com>
 */
@Named
@ViewScoped
public class ConsultaPermisosControlador implements Serializable {

    @Inject
    BswAccesosGruposEJB accesosGruposEJB;

    public boolean puedeConsultarForma(String nombreForma) {
        return accesosGruposEJB.puedeConsultar(nombreForma);
        //      return true;
    }

    public boolean puedeConsultarModulo(String nombreModulo) {
        return accesosGruposEJB.poseeModulo(nombreModulo);
        //      return true;
    }

    //<editor-fold defaultstate="collapsed" desc="Base">
    public boolean puedeConsultarModuloBase() {
        return puedeConsultarModulo(ApplicationConstant.CODIGO_MODULO_BASE);
    }

    public boolean puedeConsultarModulosBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaModuolosBase);
    }

    public boolean puedeConsultarParamGenBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaPramaetrosBase);
    }

    public boolean puedeConsultarPersonasBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaPersonasBase);
    }

    public boolean puedeConsultarFormasBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaFormasBase);
    }

    public boolean puedeConsultarGrupoUserBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaGruposBase);
    }

    public boolean puedeConsultarUserBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaUsuariosBase);
    }

    public boolean puedeConsultarIdentificacionBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaIdentificacionesBase);
    }

    public boolean puedeConsultarAccesoGrBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaAccesoBase);
    }

    public boolean puedeConsultarOpcionPerBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaOpcionBase);
    }

    public boolean puedeConsultarOpcionParamBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaOpcionParamBase);
    }

    public boolean puedeConsultarMsnUserBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaMSNBase);
    }

    public boolean puedeConsultarEmpresaParamBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaEmpresaBase);
    }

    public boolean puedeConsultarSucursalBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaSucursalBase);
    }

    public boolean puedeConsultarRolSucursalBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRolSucursal);
    }

    public boolean puedeConsultarTipoProgramaBase() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_TIPO_PROGRAMA_BASE);
    }

    public boolean puedeConsultarTipoDato() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_TIPO_PROGRAMA_BASE);
    }

    public boolean puedeConsultarComponentesBase() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_COMPONENTES_BASE);
    }

    public boolean puedeConsultarCategoriasBase() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_CATEGORIA_BASE);
    }

    public boolean puedeConsultarInstituciones() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_INSTITUCION);
    }

    public boolean puedeConsultarMonedasBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaMonedasBase);
    }

    public boolean puedeConsultarPaisBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaPaisBase);
    }

    public boolean puedeConsultarDeptoBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaDptoBase);
    }

    public boolean puedeConsultarCiudadesBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaCiudadBase);
    }

    public boolean puedeConsultarBarrioBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaBarriosBase);
    }

    public boolean puedeConsultarNivelEstudioBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaNivelEstudioBase);
    }

    public boolean puedeConsultarSectorBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaSectorEconBase);
    }

    public boolean puedeConsultarZonasBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaZonasgeoBase);
    }

    public boolean puedeConsultarRptListaUsrBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRptListadoUsrBase);
    }

    public boolean puedeConsultarRptUsrGrupoBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRptUserGrupoBase);
    }

    public boolean puedeConsultarRptFormasUserBase() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaFormasBase);
    }

    public boolean puedeConsultarCalendario() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_CALENDARIO);
    }

    public boolean puedeConsultarModuloMonitoreo() {
        return puedeConsultarModulo(ApplicationConstant.CODIGO_MODULO_MONITOREO);
    }

    public boolean puedeConsultarEmpresasVarias() {
        return accesosGruposEJB.puedeConsultarEmpresasVarias();
    }

    public boolean puedeConsultarDatosEmpresa() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaDatosEmpresa);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="servicios">
    public boolean puedeConsultarCajas() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaCajas);
    }

    public boolean puedeConsultarBswTalonarios() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaTALONVentas);
    }

    public boolean puedeConsultarVtwCoTipoCobro() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaTIPCOBVentas);
    }

    public boolean puedeConsultarVtwMovServicioFactura() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaFacturaVentas);
    }

    public boolean puedeConsultarStwArticulos() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaArticulosStock);
    }

    public boolean puedeConsultarStwGruposArt() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaGrupoArtStock);
    }

    public boolean puedeConsultarCowBienesUso() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaCowBienesUso);
    }

    public boolean puedeConsultarBswCategoria() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_CATEGORIA_BASE);
    }

    public boolean puedeConsultarPrwPresupuestos() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_PRESUPUESTO);
    }

    public boolean puedeConsultarTswBancosCuentas() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_BANCOS_CUENTAS);
    }

    public boolean puedeConsultarProGeneraAsientosConta() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_AUTORIZA_CONTA);
    }

    public boolean puedeConsultarTswRendicionFondo() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_RENDICION_FONDOS);
    }

    public boolean puedeConsultarTswCronogramaPagoV2() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_CRONOGRAMA_PAGOS);
    }

    public boolean puedeConsultarTswPagosRealizados() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaPagosRealizados);
    }

    public boolean puedeConsultarTswOrdenPago() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_ORDEN_PAGO);
    }

    public boolean puedeConsultarTswValoresCajas() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_VALORES_CAJA);
    }

    public boolean puedeConsultarTswComprasAnuladas() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaComprasAnuladas);
    }

    public boolean puedeConsultarTswRptFlujoCaja() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_FLUJO_CAJA);
    }

    public boolean puedeConsultarTswFlujoEfectivoMensual() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaTswFlujoEfectivoMensual);
    }

    public boolean puedeConsultarTswRptLibroCompras() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_RPTLIBRO_COMPRAS);
    }

    public boolean puedeConsultarHechauka() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_HECHAUKA);
    }

    public boolean puedeConsultarHechaukaV2() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaHechaukaV2);
    }

    public boolean puedeConsultarTswRptLibroVentas() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_RPTLIBRO_VENTAS);
    }

    public boolean puedeConsultarRhwEmpleado() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_EMPLEADOS);
    }

    public boolean puedeConsultarRrhhUsuarios() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_RH_USUARIOS);
    }

    public boolean puedeConsultarRhwPlanillaSalario() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_PLANILLA_SALARIO);
    }

    public boolean puedeConsultarRhwPlanillaAguinaldo() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_PLANILLA_AGUINALDO);
    }

    public boolean puedeConsultarRhwLiquidacion() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_LIQUIDACION);
    }

    public boolean puedeConsultarRhwPlanillaMtess() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_PLANILLA_MTESS);
    }

    public boolean puedeConsultarRhwPlanillaMtessMEs() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRptMTESSMES);
    }

    public boolean puedeConsultarRhwPlanillaIPSTXT() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRptIPSTXT);
    }

    public boolean puedeConsultarRhwCargo() {
        return puedeConsultarForma(ApplicationConstant.NOMBRE_FORMA_CARGOS);
    }

    public boolean puedeConsultarRhwCategoriaInterna() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwCategoriaInterna);
    }

    public boolean puedeConsultarRhwCategoriaMjt() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwCategoriaMjt);
    }

    public boolean puedeConsultarRhwDepartamentos() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwDepartamentos);
    }

    public boolean puedeConsultarRhwFeriados() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwFeriados);
    }

    public boolean puedeConsultarRhwFormaCobro() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwFormaCobro);
    }

    public boolean puedeConsultarRhwMotivoSalida() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwMotivoSalida);
    }

    public boolean puedeConsultarRhwSituacion() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwSituacion);
    }

    public boolean puedeConsultarRhwSituacionIps() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwSituacionIps);
    }

    public boolean puedeConsultarRhwTipoDocumento() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwTipoDocumento);
    }

    public boolean puedeConsultarRhwTipoSalario() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwTipoSalario);
    }

    public boolean puedeConsultarRhwReposteListaEmpleado() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwReposteListaEmpleado);
    }

    public boolean puedeConsultarRhwRepostePlanillaIPS() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwRepostePlanillaIPS);
    }

    public boolean puedeConsultarRhwReposteListaAnticipo() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwReposteListaAnticipo);
    }

    public boolean puedeConsultarRhwPropuestaVacaciones() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaRhwPropuestaVacaciones);
    }

    public boolean puedeConsultarMaps2() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaMap);
    }

    public boolean puedeConsultarTswConsultaCuotaProveedor() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaConsultaCuotas);
    }
    //</editor-fold>
    public boolean puedeConsultarSectorEcon() {
        return puedeConsultarForma(ApplicationConstant.nombreFormaBswSectorEcon);
    }
}
