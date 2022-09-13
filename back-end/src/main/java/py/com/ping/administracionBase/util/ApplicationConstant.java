/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ApplicationConstant {

    public static final String DATASOURCENAME = "java:jboss/datasources/felsinaDS";

    public static final String SESSIONTIMEOUT = "SESSIONTIMEOUT";
    public static final String NOMBRELOGO = "NOMBRELOGO";
    public static final String USERNAME = "UserName";
    public static final String PAGINVNAME = "cevima";
    public static final String VENTAS_CAB = "ventas_doc.xls";
    public static final String VENTAS_CAB_TEMPLATE = "ventas_cab.xlsx";
    public static final String PAGLOGIN = "/faces/administracionBase/login.xhtml";
    public static final String PAGINIT = "/faces/administracionBase/inicio.xhtml";
    public static final String CONTEXTMAPSESSION = "_OPENSESSION";
    public static final String REPORTXLS = "XLS";
    public static final String REPORTPDF = "PDF";

    //MODULOS
    public static final String CODIGO_MODULO_STOCK = "ST";
    public static final String CODIGO_MODULO_VENTAS = "VT";
    public static final String CODIGO_MODULO_CUENTAS_COBRAR = "CC";
    public static final String CODIGO_MODULO_FUERZA_VENTAS = "FV";
    public static final String CODIGO_MODULO_BASE = "BS";
    public static final String CODIGO_MODULO_CONTABILIDAD = "CO";
    public static final String CODIGO_MODULO_TESORERIA = "TS";
    public static final String CODIGO_MODULO_PRESUPUESTO = "PR";
    public static final String CODIGO_MODULO_USAID = "US";
    public static final String CODIGO_MODULO_PROCESOS = "PO";
    public static final String CODIGO_MODULO_CONTRATO = "CT";
    public static final String CODIGO_MODULO_PROCESO_CONTRATO = "PC";
    public static final String CODIGO_MODULO_MONITOREO = "MO";
    public static final String CODIGO_RECURSOS_HUMANOS = "RH";
    public static final String CODIGO_MODULO_RESERVAS = "RE";
    public static final String CODIGO_MESA_ENTRADA = "ME";
    public static final String CODIGO_MODULO_BASCULA = "BA";
    public static final String CODIGO_MODULO_ADMINISTRACION = "AD";

    //Reservas
    public static final String HORA_INICIO_RESERVA = "HORA_INICIO_RESERVA";
    public static final String HORA_FIN_RESERVA = "HORA_FIN_RESERVA";
    // Permisos Opciones
    public static final String PERM_BOTON_EMPRESA = "BOTON_EMPRESA";
    public static final String PERM_BOTON_SUCURSAL = "BOTON_SUCURSAL";
    public static final String PERM_BOTON_EJERCICIO = "BOTON_EJERCICIO";
    public static final String PERM_CHECKBOX_SUCURSAL = "CHECKBOX_SUCURSAL";
    public static final String MODIFICAR_SUCURSAL = "MODIFICAR_SUCURSAL";
    public static final String ANULA_FACTURA = "ANULA FACTURA";

    public static final String DESANULA_FACTURA = "DESANULA FACTURA";
    public static final String ANULA_NOTA_CREDITO = "ANULA NOTA CREDITO";
    public static final String DESANULA_NOTA_CREDITO = "DESANULA NOTA CREDITO";
    public static final String RESERVA_ARTICULOS = "RESERVA ARTICULOS";
    public static final String AUTORIZA_CAMBIO_PRECIO = "AUTORIZA CAMBIO PRECIO";
    public static final String AUTORIZA_LIMITE_CREDITO = "AUTORIZA LIMITE CREDITO";
    public static final String IMPRIMIR_NOTA_CREDITO = "IMPRIMIR NOTA CREDITO";

    //Formatos de Reportes
    public static final String FORMATO_REPORTE_XLS = "XLS";
    public static final String FORMATO_REPORTE_PDF = "PDF";
    // VTFACTUR
    public static final String ELIMINA_FACTURA = "ELIMINA FACTURA";
    public static final String CAMBIA_NRO_FACTURA = "CAMBIA_NUMERO_FAC";
    public static final String CARGA_SUCURSAL = "CARGA SUCURSAL";

    // CCRECIBOS
    public static final String LISTA_NCR = "MOSTRAR NCR EN RECIBOS";

    public static final String RECIBOS_TEMPLATE_NAME = "recibos_realizados_template.xlsx";
    public static final String RECIBOS_OUTPUT_NAME = "recibos_realizados_output.xlsx";

    // Clientes
    public static final String parametroRUC = "RUC";
    public static final String parametroNOM = "NOM";
    // VTNOTCRE (nota de credito)
    public static final String PERM_ANULAR_NOTCRE = "PERMITE ANULAR";
    public static final String PERM_DESANULAR_NOTCRE = "PERMITE DESANULAR";
    public static final String CARGA_SUCURSAL_NOTCRE = "CARGA SUCURSAL";
    public static final String PERM_IMPRIMIR_NCR = "PERMITE_IMPRIMIR_NCR";
    public static final String AUTORIZA_IMPRESION_NCR = "AUTORIZA_IMPRESION_NCR";

    // NOMBRE DE LAS FORMAS
    // BASE
    public static final String nombreFormaModuolosBase = "BSMODULO";
    public static final String nombreFormaDatosEmpresa = "DAEMP";
    public static final String nombreFormaPramaetrosBase = "BSPARAMETRO";
    public static final String nombreFormaPersonasBase = "BSPERFIS";
    public static final String nombreFormaFormasBase = "BSFORMAS";
    public static final String nombreFormaGruposBase = "BSGRUPOS";
    public static final String nombreFormaUsuariosBase = "BSUSUARI";
    public static final String nombreFormaIdentificacionesBase = "BSWIDENT";
    public static final String nombreFormaAccesoBase = "BSACCEGRUP";
    public static final String nombreFormaPermisosBase = "BSPERMIS";
    public static final String nombreFormaOpcionBase = "BSOPCION";
    public static final String nombreFormaOpcionParamBase = "BSPPARAM";
    public static final String nombreFormaMSNBase = "BSMSNUSER";
    public static final String nombreFormaEmpresaBase = "BSEMPRES";
    public static final String nombreFormaSucursalBase = "BSSUCUR";
    public static final String nombreFormaMonedasBase = "BSMONEDAS";
    public static final String nombreFormaPaisBase = "BSPAIS";
    public static final String nombreFormaDptoBase = "BSPROVIN";
    public static final String nombreFormaCiudadBase = "BSCIUDAD";
    public static final String nombreFormaBarriosBase = "BSBARRIO";
    public static final String nombreFormaNivelEstudioBase = "BSNIVEST";
    public static final String nombreFormaSectorEconBase = "BSSECECON";
    public static final String nombreFormaZonasgeoBase = "BSWZONA";
    public static final String nombreFormaRptListadoUsrBase = "BSRUSR";
    public static final String nombreFormaRptUserGrupoBase = "BSRGRUSU";
    public static final String nombreFormaRptFormasUserBase = "BSRACCFOR";
    public static final String nombreFormaRolSucursal = "BSWROLSUC";
    public static final String NOMBRE_FORMA_TIPO_PROGRAMA_BASE = "BSTIPPRO";
    public static final String NOMBRE_FORMA_TIPO_DATO_BASE = "TIPODATO";
    public static final String NOMBRE_FORMA_COMPONENTES_BASE = "BSCOMPON";
    public static final String NOMBRE_FORMA_CATEGORIA_BASE = "BSCATEGO";
    public static final String NOMBRE_FORMA_PERMISOS_NOTAS_CREDTIO = "STWPNC";
    public static final String PUEDE_CONFIRMAR_REMISIONES = "STCONR";
    public static final String NOMBRE_FORMA_INSTITUCION = "BSTINSTIT";
    public static final String NOMBRE_FORMA_COSTOS_ART = "STCOSART";
    //Contratos
    public static final Integer UM_MES = 1;

    // NOMBRE DE LAS FORMAS
    // STOCK
    public static final String nombreFormaArticulosStock = "STARTICU";
    public static final String nombreFormaGrupoArtStock = "STGRPART";
    public static final String nombreFormaMarcaArtStock = "STMARAR";
    public static final String nombreFormaLineasArtStock = "STLINAR";
    public static final String nombreFormaIvaStock = "STIVA";
    public static final String nombreFormaMOTESStock = "STMOTES";
    public static final String nombreFormaMOTGASStock = "STWMOTGAS";
    public static final String nombreFormaCARGASStock = "STWCARGAS";
    public static final String COD_IVA = "COD_IVA";
    public static final String COD_GRUPO = "COD_GRUPO";
    public static final String nombreFormaProveeStock = "CCWPROVE";
    public static final String nombreFormaPagoProveeStock = "CCWPPROV";
    public static final String nombreFormaEntSalStock = "STENTSAL";
    public static final String nombreFormaGastosStock = "STGASTOS";
    public static final String nombreFormaNOTENVIOStock = "STNOTENV";
    public static final String nombreFormaExistenciaArtStock = "STCONST";
    public static final String nombrePreciosFijosStock = "STPRECFI";
    public static final String nombreFormaRptArticuloStock = "STRARTIC";
    public static final String nombreFormaRptexistenciaStock = "STREXIST";
    public static final String nombreFormaRptMTESSMES = "RRMTME";
    public static final String nombreFormaRptIPSTXT = "RRIPTXT";
    public static final String nombreFormaRPTART_MINStock = "STRARTMI";
    public static final String nombreFormaRptInventarioStock = "STRINVEN";
    public static final String nombreFormaRptMovArtPorMotivo = "STRMOVPR";
    public static final String nombreFormaRutasClientes = "STRUCLI";
    public static final String nombreFormaConsultaArticuloCompra = "STCONART";
    public static final String nombreFormaCargaArticulosDetalle = "STCARARTD";
    public static final String nombreFormaActualizaProducto = "STACTPRD";
    public static final String nombreFormaTalles = "STTALLE";
    public static final String nombreFormaModelos = "STMODEL";
    public static final String nombreFormaColores = "STCOLOR";
    public static final String nombreFormaUnidadMedida = "STCUNDME";
    public static final String nombreFormaConsultaArtEnSal = "STCONARES";
    public static final String nombreFormaEnvioEntreSucursal = "STENVSUC";
    public static final String nombreFormaRecepcionarEnvios = "STRECENV";
    public static final String nombreFormaRecepcionDeStockPendientes = "STRECPEN";
    public static final String nombreFormaVehiculos = "STVEHIC";
    public static final String nombreFormaTipoVehiculos = "STTIVE";

    public static final String nombreFormaChofer = "STCHOFS";
    public static final String nombreFormaChoferes = "STCHOFS";
    public static final String nombreFormaRutas = "STWRUTA";
    public static final String nombreFormaMotivoTraslado = "STMOTTRS";
    public static final String nombreFormaPedidoSucursalProduccion = "STPESUPR";
    public static final String nombreFormaPedidoSucursal = "STPEDSUC";
    public static final String nombreFormaIntercambioProductos = "STINTPR";
    public static final String nombreFormaNotasCredito = "STNOTCR";
    public static final String nombreFormaRecepcionStockPendientes = "STRESTPN";
    public static final String nombreFormaMovimientoArticulo = "STMOVAR";
    public static final String nombreFormaComparativoExistencia = "STCOMEX";
    public static final String nombreFormaMovimientoArticulosEnStock = "STMOARST";
    public static final String PROCESO_COMPRAS_TEMPLATE_NAME = "proceso_compras_realizados_template.xlsx";
    public static final String nombreFormaMovimientoMaquinas = "STMOVMA";
    public static final String nombreFormaListaPrecio = "STWLISP";

    public static final String COMPRAS_TEMPLATE_NAME = "compras_realizados_template.xlsx";
    public static final String MOV_CAJA_TEMPLATE_NAME = "movimiento_caja_template.xlsx";
    public static final String MOV_CAJA_ANULADAS_TEMPLATE_NAME = "movimiento_caja_anuladas_template.xlsx";
    public static final String LIBRO_COMPRAS_RES_90_TEMPLATE_NAME = "libro_compras_res_90.xlsx";
    public static final String LIBRO_COMPRAS_RES_90_OUTPUT = "libro_compras_res_90_output.xlsx";
    public static final String LIBRO_VENTAS_RES_90_TEMPLATE_NAME = "libro_ventas_res_90.xlsx";
    public static final String LIBRO_VENTAS_RES_90_OUTPUT = "libro_ventas_res_90_output.xlsx";
    
    public static final String LINEA_BASE_TEMPLATE_NAME = "plantilla_linea_base.xlsx";
    public static final String LINEA_BASE_OUTPUT_NAME = "plantilla_linea_base_output.xlsx";
    // NOMBRE DE LAS FORMAS
    // Ventas
    public static final String nombreFormaPromoArticulos = "FVPROART";
    public static final String nombreFormaPeriodoComisiones = "FVPERCOM";
    public static final String nombreFormaGruposVendedores = "FVWGRVEN";
    public static final String nombreFormaPeriodoMetas = "FVPERMET";
    public static final String nombreFormaComisionesPorMetas = "FVCOMMET";
    public static final String nombreFormaRptComisionesVendedores = "FVDETCOM";
    public static final String nombreFormaRptComisionesResVendedores = "FVDETCOM";
    public static final String nombreFormaRptMetasAlcanzadas = "FVCOMMET";
    public static final String nombreFormaHabilitacionesVentas = "CCWHABCA";
    public static final String nombreFormaCajasVentas = "STCAJAS";
    public static final String nombreFormaTimbradoVentas = "VTTIMB";
    public static final String nombreFormaTIPTALVentas = "BSTIPTAL";
    public static final String nombreFormaTALONVentas = "BSWTALON";
    public static final String nombreFormaCLIENTEVentas = "CCCLIENT";
    public static final String nombreFormaTIPCOBVentas = "VTTIPCOB";
    public static final String nombreFormaVEndVentas = "FVWVEND";
    public static final String nombreFormaPedidosVentas = "VTPEDIDO";
    public static final String nombreFormaFacturaVentas = "VTFACTUR";
    public static final String nombreFormaFacturaVentasPendientesCobro = "CCCOP";
    public static final String nombreFormaCONARTVentas = "VTCONART";
    public static final String nombreFormaRPTVENTASVENDVentas = "VTRVEPVE";
    public static final String nombreFormaRANKARTVentas = "VTRANKVE";
    public static final String nombreFormaART_VEN_SIN_MOVVentas = "VTRVTALI";
    public static final String nombreRankVENTASVENDPreciosVentas = "VTRFAVEN";
    public static final String nombreFormaRPTDETFACVentas = "VTRDETFAC";
    public static final String nombreFormaRptVentasSemanalVentas = "VTRVISCL";
    public static final String nombreFormaRptVentasVendVentas = "VTRRESVE";
    public static final String nombreFormaRptVentaCajeroVentas = "VTRVENCAJ";
    public static final String nombreFormaRptFacturaVentaCajero = "VTRFACAJ";
    public static final String nombreFormaRptVentaCajero = "VTRVENCAJ";
    public static final String nombreFormaRptVentaMENsaulaGrupoVentas = "VTRCOMEV";
    public static final String nombreFormaRptComparativoMensualGrupoVentas = "VTRVISCL";
    public static final String nombreFormaRptResumenVentasSucursalVentas = "VTRVTASU";
    public static final String nombreFormaRptResumenDiarioFacturacionVentas = "VTRFARES";
    public static final String nombreFormaRptDetalleVentasArtVentas = "VTRFAPRO";
    public static final String nombreFormaRptGastosVentas = "VTRGASTO";
    public static final String nombreFormaRptUtilidadVentas = "VTRUTILI";
    public static final String nombreFormaActualizaComprobante = "VTACTNUM";
    public static final String nombreFormaPresupuestoPedido = "VTPREPRD";
    public static final String nombreFormaPrestadoras = "VTPRESTD";
    public static final String nombreFormaNotadeCredito = "VTNOTCRD";
    public static final String nombreFormaItemsFacturados = "VTITFAC";
    public static final String nombreFormaEstadisticasCobros = "VTESTCOB";
    public static final String nombreFormaArqueodeCaja = "VTARQCAJ";
    public static final String nombreFormaRankingArticulos = "VTRANAR";
    public static final String nombreFormaVentasMes = "VTVEMES";
    public static final String nombreFormaVentasMesVendedor = "VTVEMES";
    public static final String nombreFormaDetalleFacturaEmitidaCajero = "FCEMCAJ";
    public static final String nombreFormaDiferenciaCierreCaja = "VTDIFCCA";
    public static final String nombreFormaResumendeventasemanalporGrupoyVendedores = "RSVSGV";
    public static final String nombreFormaComparativodeVentasMensualesporVendedoresyGrupos = "CMVMGV";

    // Cuentas a Cobrar agregar en la tabla de formas  
    public static final String nombreFormaCondicionVenta = "CCWCONVE";
    public static final String nombreFormaCobranzaRecibos = "CCWREC";
    public static final String nombreFormaAnularCobranzaRecibos = "CCWAREC";
    public static final String nombreFormaCobro = "CCFC";
    public static final String nombreFormaCobradores = "CCWCOBRA";
    public static final String nombreFormaConsultaMovCaj = "CCCMOVC";
    public static final String nombreFormaMovCaja = "CCMOVCAJ";
    public static final String nombreFormaREcibos = "CCRECIBO";
    public static final String nombreFormaAnulacionMovCaja = "CCAMOVCA";
    public static final String nombreFormaClientes = "CCRCLIT";
    public static final String nombreFormaCajas = "CCRCAJA";
    public static final String nombreFormaHabilitacionCajas = "CCHABCAJ";

    // Contabilidad agregar en la tabla de formas 
    public static final String NOMBRE_FORMA_RELACION_CUENTA = "COWRCUE";
    public static final String nombreFormaCentroCosto = "COWCENCO";
    public static final String COD_CUENTA_DISPONIBILIDADES = "1.01.01";
    public static final String NOMBRE_FORMA_CONFIG_FLUJO_EFECTIVO = "COWFLUEF";
    public static final String NOMBRE_FORMA_GENERADORA_PLANTILLA = "COWGENPLA";
    public static final String NOMBRE_FORMA_GRUPO_CUENTA = "COWGRUCU";
    public static final String NOMBRE_FORMA_GENERADOR_TABLAS = "COWGENTA";
    public static final String NOMBRE_FORMA_CUENTA_PROVEEDOR = "COWCUPRO";
    public static final String NOMBRE_FORMA_PLANTILLA = "COWPLANT";
    public static final String NOMBRE_FORMA_ASIENTO_CONTABLE = "COWASICO";
    public static final String NOMBRE_FORMA_CAMBIOS_ASIENTO = "COWASICO";
    public static final String NOMBRE_FORMA_PLAN_CUENTA = "COWPLACU";
    public static final String NOMBRE_FORMA_PLAN_CUENTA_SET = "COPLASET";
    public static final String NOMBRE_FORMA_EJERCICIOS_FISCALES = "COWEJERF";
    public static final String NOMBRE_FORMA_BIENES_USO = "COWBIEUS";
    public static final String NOMBRE_FORMA_REVALUO = "COWREVAL";
    public static final String NOMBRE_FORMA_CONCILIACION_BANCARIA = "COWCONBA";
    public static final String NOMBRE_FORMA_ESTRACTO_BANCO = "COWESTBA";
    public static final String NOMBRE_FORMA_TIPO_BIEN = "COWTIPBN";
    public static final String nombreFormaCowBienesUso = "COWBIEUS";
    public static final String NOMBRE_FORMA_DETALLE_ASIENTO = "COWDETA";

    public static final String NOMBRE_FORMA_DUMMY = "DUMMY";
    public static final String DIARIO_DETALLE_TEMPLATE_NAME = "diario_detalle.xlsx";
    public static final String DIARIO_DETALLE_OUTPUT_NAME = "diario_detalle_output.xlsx";
    public static final String CODIGO_CUENTA_IVA_5 = "5";
//Recursos Humanos
    public static final String NOMBRE_FORMA_EMPLEADOS = "RHWEMPLEADO";
    public static final String NOMBRE_FORMA_CARGOS = "RHWCARG";
    public static final String NOMBRE_FORMA_FERIADOS = "RHWFERIADO";
    public static final String NOMBRE_FORMA_TIMESHEET = "RHWTSHEET";
    public static final String NOMBRE_FORMA_PLANILLA_SALARIO = "RHWPLASALAR";
    public static final String NOMBRE_FORMA_PLANILLA_AGUINALDO = "RHWPLAGUI";
    public static final String NOMBRE_FORMA_COMPONENTE_SALARIO = "RHWCOMPSALAR";
    public static final String NOMBRE_FORMA_LIQUIDACION = "RHWLIQUI";
    public static final String NOMBRE_FORMA_PLANILLA_MTESS = "RHWMTESS";
    public static final String NOMBRE_FORMA_RH_CONFIGURACION = "RHWCONFIG";
    public static final String NOMBRE_FORMA_RH_USUARIOS = "RHWUSUAR";

    public static final String nombreFormaRhwCategoriaInterna = "RHCATIN";
    public static final String nombreFormaRhwDepartamentos = "RHDEPTO";
    public static final String nombreFormaRhwCategoriaMjt = "RHCATMIT";
    public static final String nombreFormaRhwFeriados = "RHFERDS";
    public static final String nombreFormaRhwFormaCobro = "RHFORCO";
    public static final String nombreFormaRhwMotivoSalida = "RHMOTSAL";
    public static final String nombreFormaRhwSituacion = "RHSITC";
    public static final String nombreFormaRhwSituacionIps = "RHSITIPS";
    public static final String nombreFormaRhwTipoDocumento = "RHTIPDOC";
    public static final String nombreFormaRhwTipoSalario = "RHTIPSAL";

    public static final String nombreFormaRhwReposteListaEmpleado = "RHRPEMP";
    public static final String nombreFormaRhwRepostePlanillaIPS = "RHRPIPS";
    public static final String nombreFormaRhwReposteListaAnticipo = "RHRPANT";
    public static final String nombreFormaRhwPropuestaVacaciones = "RHVACN";

    public static final String nombreFormaMap = "MAPS";
    //Contabilidad Reportes
    public static final String nombreFormaCowRptLibroMayor = "RPTLIBMA";
    public static final String nombreFormaCowRptBalanceGralAnalitico = "RPTBAGEA";
    public static final String nombreFormaCowRptBalanceImpositivo = "RPTBAIMP";
    public static final String NOMBRE_RPT_BALANCE_GENERAL = "RPTBAGRL";
    public static final String NOMBRE_RPT_BALANCE_CORPORATIVO = "RPTBACOR";
    public static final String nombreFormaCowRptResumenAsiento = "RPTREASI";
    public static final String NOMBRE_RPT_LIBRO_BANCO = "RPTLIBAN";
    //Contabilidad Reportes
    public static final String NOMBRE_RPT_LIBRO_MAYOR = "RPTLIBMA";
    public static final String NOMBRE_RPT_BALANCE_GRAL_ANALITICO = "RPTBAGEA";
    public static final String NOMBRE_RPT_BALANCE_IMPOSITIVO = "RPTBAIMP";

    public static final String NOMBRE_RPT_RESUMEN_ASIENTO = "RPTREASI";
    // USAID
    public static final String NOMBRE_RPT_RESUMEN_CATEGORIA_USAID = "RPTRECAT";
    //EXTRACTO BANCO INTERNO
    public static final String NOMBRE_RPT_EXTRACTO_BANCO_INTERNO = "RPTREEBI";
    //Revaluo
    public static final String NOMBRE_RPT_REVALUO = "RPTREVA";
//Movil
    public static final String RUC_MOVIL = "RUC";
    public static final String TIPO_COMPROBANTE_PED = "PED";
    public static final String TIPO_COMPROBANTE_DEL = "DEL";

    public static final String SERIE_COMPROBANTE_A = "A";
    public static final String ESTADO_FACTURA_P = "P";
    public static final String ESTADO_P = "P";
    public static final String CONSULTA_CABECERA_REMISIONES_TEMPLATE_NAME = "cabecera_remisiones.xlsx";
    public static final String CONSULTA_CABECERA_REMISIONES_OUTPUT_NAME = "cabecera_remisiones_out.xlsx";
    public static final String CONSULTA_DETALLE_REMISIONES_TEMPLATE_NAME = "detalle_remisiones.xlsx";
    public static final String CONSULTA_DETALLE_REMISIONES_OUTPUT_NAME = "detalle_remisiones_out.xlsx";
    public static final String CONSULTA_ART_ES_TEMPLATE_NAME = "entsal.xlsx";
    public static final String CONSULTA_ART_ES_OUTPUT_NAME = "entsal_output.xlsx";

    public static final String CONSULTA_ART_ES_COMPRAS_TEMPLATE_NAME = "entsal_compras.xlsx";
    public static final String CONSULTA_ART_ES_COMPRAS_OUTPUT_NAME = "entsal_compras_output.xlsx";
    public static final String CONSULTA_REMISIONES_TEMPLATE_NAME = "remisiones.xlsx";
    public static final String CONSULTA_REMISIONES_OUTPUT_NAME = "remisiones_output.xlsx";
    public static final String CONSULTA_ORDEN_COMPRA_TEMPLATE_NAME = "orden_compra.xlsx";
    public static final String CONSULTA_ORDEN_COMPRA_OUTPUT_NAME = "orden_compra_output.xlsx";
    public static final String CONSULTA_PRODUCTORES_ORGANICOS_TEMPLATE_NAME = "productores_organicos.xlsx";
    public static final String CONSULTA_PRODUCTORES_ORGANICOS_OUTPUT_NAME = "productores_organicos_output.xlsx";

    public static final String MOVIMIENTO_ART = "movimiento_art_doc.xlsx";
    public static final String MOVIMIENTO_ART_TEMPLATE = "movimiento_art.xlsx";

    public static final String ESTADO_NUEVO = "N";
    public static final String ESTADO_MODIFICADO = "M";
    public static final String ESTADO_ELIMINADO = "E";

    public static final String ESTADO_PEDIR_CUENTA = "C";
    public static final String ESTADO_PEDIDO_FACTURADO = "F";
    public static final String ESTADO_PEDIDO_PENDIENTE = "P";
    public static final String ESTADO_PEDIDO_ANULADO = "A";
    public static final String ESTADO_MESA_OCUPADA = "O";

    //Tesoreria
    public static final String COD_BANCO_DEFAULT = "COD_BANCO_DEFAULT";
    public static final String nombreFormaCompras = "TSCOMP";
    public static final String nombreFormaConsultaCuotas = "TSCONCU";
    public static final String nombreFormaPagosRealizados = "TSPAGR";
    public static final String nombreFormaMovimientoRubro = "TSMOVR";
    public static final String nombreFormaComprasAnuladas = "TSCOMAN";
    public static final String nombreFormaTswFlujoEfectivoMensual = "TSFLUEM";
    public static final String NOMBRE_FORMA_BANCOS = "TSBAN";
    public static final String NOMBRE_FORMA_RPTLIBRO_COMPRAS = "TSRLC";
    public static final String NOMBRE_FORMA_RPTLIBRO_VENTAS = "TSRLV";
    public static final String NOMBRE_FORMA_INVENTARIO_CONTRATO = "TSINCO";
    public static final String NOMBRE_FORMA_FLUJO_CAJA = "TSFLCA";
    public static final String NOMBRE_FORMA_ACEPTACIONES_PENDIENTES = "TSACPN";
    public static final String NOMBRE_FORMA_FUNCIONARIOS = "TSFUN";
    public static final String NOMBRE_FORMA_ALERTAS = "TSALE";
    public static final String NOMBRE_FORMA_BANCOS_CUENTAS = "TSBANC";
    public static final String NOMBRE_FORMA_ORDEN_PAGO = "TSORPA";
    public static final String NOMBRE_FORMA_AUTORIZACION = "TSAUTO";
    public static final String NOMBRE_FORMA_VALORES_CAJA = "TSVACAJ";
    public static final String NOMBRE_FORMA_RENDICION_FONDOS = "TSRENFO";
    public static final String NOMBRE_FORMA_DETALLE_RENDICION = "TSDETRE";
    public static final String NOMBRE_FORMA_CRONOGRAMA_PAGOS = "TSCROPA";
    public static final String NOMBRE_FORMA_REPOSICION_FONDOS = "TSREPFO";
    public static final String NOMBRE_FORMA_TIPOS_CAMBIO = "TSTCAM";
    public static final String NOMBRE_FORMA_VALORES_CAJA_TIPO_CAMBIO = "TSVCTC";
    public static final String NOMBRE_FORMA_PAGOS_ACTIVIDADES_TIPO_CAMBIO = "TSPATC";
    public static final String NOMBRE_FORMA_PAGOS_AJUSTES = "TSPAAJ";
    public static final String NOMBRE_FORMA_CATEGORIAS_CONTRAPARTIDA = "TSCACO";
    public static final String NOMBRE_FORMA_CONTRAPARTIDAS = "TSCONTR";
    public static final String NOMBRE_FORMA_AUTORIZACION_USUARIOS_TESORERIA = "TSAUUS";
    public static final String nombreFormaProveedores = "TSPROVD";
    public static final String nombreFormaAutorizarPagos = "TSAUTPG";
    public static final String nombreFormaPagoProveedores = "TSPAGPRO";
    public static final String nombreFormaFEfectMensual = "TSFEFMS";
    public static final String PROVEEDOR_DEFAULT = "PROVEEDOR_DEFAULT";
    public static final String PLAN_CUENTAS_DEFAULT = "PLAN_CUENTAS_DEFAULT";
    public static final String nombreFormaTipoAnalisis = "STWTANA";
    public static final String NOMBRE_FORMA_TRANSFERENCIA_BANCOS = "TSTBAN";
    public static final String nombreFormaSolicitudCompra = "TSSOLICO";
    public static final String NOMBRE_FORMA_CONTRATOS_FACTURAS = "TSCONFAC";
    public static final String TEMPLATE_NAME_SOLICITUD_COMPRA = "solicitud-compra.docx";
    public static final String TEMPLATE_NAME_RENDICION_SOLICITUD_COMPRA = "solicitud-rendicion.docx";
    public static final String nombreFormaActividadContrato = "TSACTV";
    public static final String TEMPLATE_NAME_SOLICITUD_PRODUCTOR = "solicitud_productor.docx";
    public static final String nombreFormaActualizarCompras = "TSWACCOM";
    public static final String NOMBRE_FORMA_TIPO_DIFERENCIA = "TSTIPDIF";

    //PROCESOS
    public static final String NOMBRE_FORMA_AUTORIZA_CONTA = "PROCOMPRA";
    public static final String NOMBRE_FORMA_AUTORIZA_ADMIN = "PROAUTAD";
    public static final String NOMBRE_FORMA_AUTORIZA_DG = "PROAUTDG";
    public static final String NOMBRE_FORMA_AUTORIZA_DP = "PROAUTDP";
    public static final String NOMBRE_FORMA_GENERA_ASIENTO = "PROGENASI";
    public static final String NOMBRE_FORMA_IMPRIME_PAGO = "PROIMPPA";
    public static final String NOMBRE_FORMA_FACTURA_CREDITO = "PROFCRE";
    public static final String NOMBRE_FORMA_PENDIENTE_IMPRESION = "PROPENIMP";

    public static final String nombreFormaTswConsultaCuotaProveedor = "TSCNCP";
    //localizacion
    public static final String NOMBRE_FORMA_LOCALIZACION = "LOCAL";
    //Presupuesto 
    public static final String NOMBRE_FORMA_PRESUPUESTO = "PRWPRE";
    public static final String NOMBRE_FORMA_RES_TODOS = "PRWRETO";
    public static final String NOMBRE_FORMA_RES_COM = "PRWRECO";
    public static final Integer PLAZO_DIAS_ENTREGA = 7;
    public static final String MENSAJE_EXENTA = "LOS PRECIOS NO INCLUYEN IVA";
    public static final String MENSAJE_IVA10 = "LOS PRECIOS INCLUYEN IVA";

    public static final String NOMBRE_FORMA_PRESUPUESTO_PERIODO = "PRPREPE";
    public static final String NOMBRE_FORMA_OBJETIVO = "PRWOBJ";
    public static final String NOMBRE_FORMA_SUB_OBJETIVO = "PRWSOB";
    public static final String NOMBRE_FORMA_OUTPUT = "PRWOUT";
    public static final String NOMBRE_FORMA_RESULTADO = "PRWRES";
    public static final String NOMBRE_FORMA_SUB_RESULTADO = "PRWSRE";
    public static final String NOMBRE_FORMA_ACTIVIDAD = "PRWACT";
    public static final String NOMBRE_FORMA_PERIODO_INFORMANTES = "PRWPI";

    //Contratos
    public static final String FACTURAS_COBRADAS_TEMPLATE_NAME = "facturas_cobradas.xlsx";
    public static final String FACTURAS_COBRADAS_OUTPUT_NAME = "facturas_cobradas_output.xlsx";

    public static final String NOMBRE_FORMA_REQUERIMIENTO_ADQUISICION = "CONRDA";
    public static final String NOMBRE_FORMA_TERMINO_REFERENCIA = "CONTDR";
    public static final String NOMBRE_FORMA_REQUERIMIENTO_COTIZACION = "CONRDC";
    public static final String NOMBRE_FORMA_ACEPTACION_PRODUCTOS = "CONAPR";
    public static final String NOMBRE_FORMA_ACEPTACION_PRODUCTOS_COORDINACION = "CONAPC";
    public static final String NOMBRE_FORMA_PREGUNTAS_RESPUESTAS = "CONWPRRE";
    public static final String NOMBRE_FORMA_REQUERIMIENTO_VIAJE = "CONRDV";
    public static final String NOMBRE_FORMA_GENERA_ORDEN_COMPRA = "CONGOC";
    public static final String NOMBRE_FORMA_MODIFICAR_ORDEN_COMPRA = "CONMOC";
    public static final String NOMBRE_FORMA_MODIFICAR_CONTRATO = "MODCON";
    public static final String NOMBRE_FORMA_AUTORIZA_MODIF_CONTRATO = "AMOCONT";
    public static final String NOMBRE_FORMA_AUTORIZACION_USUARIOS_CONTRATO = "CONAUU";
    public static final String NOMBRE_FORMA_EVALUACION_CONSULTORES = "CONECO";
    //Contratos Permisos
    public static final String MODIFICA_NUMERO_ORDEN_COMPRA = "MODIFICA NRO ORDEN COMPRA";
    public static final String MODIFICA_CRONOGRAMAS_PAGA = "MODIFICA_CRONOGRAMAS_PAGA";
    public static final String MODIFICA_RDA = "MODIFICA_RDA";
    public static final String MODIFICA_RUBRO_PRESUPUESTARIO = "MODIF_RUBRO_PRESUPUES";

    public static final String NOMBRE_RPT_ESTADO_ACTIVIDAD = "RPTESACT";

    //Monitoreo
    public static final String NOMBRE_FORMA_CALENDARIO = "RECALEN";
    public static final String NOMBRE_FORMA_CHART = "MONCHAR";
    public static final String NOMBRE_FORMA_LISTAEVALUACION = "MONLEVAL";
    public static final String NOMBRE_FORMA_INDICADORES = "MONIND";
    public static final String NOMBRE_FORMA_CATEGORIAS = "MONCAT";
    //public static final String NOMBRE_FORMA_PERIODO_META = "MONPME";
    public static final String NOMBRE_FORMA_META = "MONMET";
    public static final String NOMBRE_FORMA_META_PERIODO = "MONMPE";
    public static final String NOMBRE_FORMA_META_PERIODO_INICIAL = "MONMPI";
    public static final String NOMBRE_PASOS_PROCESO = "BSPASO";

    //Recursos Humanos
    /*Reportes*/
    public static final String nombreRptRecibosCobrados = "CCRRECCO";
    public static final String nombreRptRecibosNoCobrados = "CCRRECNC";
    public static final String nombreRptResumenCajas = "CCRRESCA";
    public static final String nombreRptDetCajas = "CCDETCA";
    public static final String nombreRptDetCajasFec = "CCRMOCOF";
    public static final String nombreRptDetCajasxComp = "CCRMOCOM";
    public static final String nombreRptChequesRecibidos = "CCRCHECA";
    public static final String nombreRptSaldosClientes = "CCRSALDO";
    public static final String nombreRptSaldosClientesAfecha = "CCRSALCF";
    public static final String nombreRptSaldosClientesAfechaS = "CCRSAFES";
    public static final String nombreRptEficienciaCobradores = "CCREFICO";
    public static final String nombreRptRecEmitVsCob = "CCRREMCO";
    public static final String nombreRptCompPendCobros = "VTRFAPEV";
    public static final String nombreRptFactCreditoEmitidas = "CCRESTFA";
    public static final String nombreRptContadoPendientes = "CCRCONPE";
    public static final String nombreRptREcPendCobros = "CCRRECPE";

    //CONSTANTES DE VENTAS
    public static final String SERIE_CONTADO = "SERIE_CONTADO";
    public static final String CONDICION_VENTA = "CONDICION_VENTA";
    public static final String LISTA_PRECIOS = "LISTA_PRECIOS";
    public static final String TIP_CONTADO = "TIP_CONTADO";
    public static final String COD_MONEDA_PRECIO = "COD_MONEDA_PRECIO";
    public static final String COD_MONEDA_BASE = "COD_MONEDA_BASE";
    public static final String DIAS_VENCIM_PEDIDO = "DIAS_VENCIM_PEDIDO";
    public static final String CODIGO_RUC = "CODIGO_RUC";
    public static final String CODIGO_SECTOR_VENTA = "COD_SEC_VENTA";
    public static final String CODIGO_CLIENTE_DEFECTO = "COD_CLIENTE_DEFECTO";
    public static final String COD_VENDEDOR_DEFECTO = "COD_VENDEDOR_DEFECTO";
    public static final String COD_MONEDA_DOLAR = "COD_MONEDA_DOLAR";
    public static final String COD_MONEDA_GS = "COD_MONEDA_GS";
    public static final String CODIGO_MONEDA_GS = "1";
    public static final String CODIGO_MONEDA_USS = "2";
    public static final int IVA_10 = 10;
    public static final int IVA_5 = 5;
    public static final String EXENTA = "";
    public static final Integer CANTIDAD_MAXIMA_CARACTERES_IMPRESION_FACTURA = 1800;
    public static final Integer CANTIDAD_MAXIMA_ITEMS_REMISION = 11;
    //CONSTANTES DE STOCK
    public static final String MONEDA_BASE_STOCK = "MONEDA_BASE_STOCK";
    public static final String UNIDAD_MEDIDA = "UNIDAD_MEDIDA_STOCK";
    public static final String COD_MOTIVO_DEFAULT = "MOT_DEFAULT_STOCK_COMPRAS";
    public static final String COD_MOTIVO_ENVIO = "MOT_ENVIO_STOCK_COMPRAS";
    public static final String COD_MOTIVO_RECEPCION = "MOT_RECEPCION_STOCK_COMPRAS";
    public static final String COD_MOTIVO_INVENTARIO = "MOTIVO_INVENTARIO";
    public static final String COD_MOD_STOCK = "ST";
    public static final String APLICA_FORMULA_STOCK = "APLICA_FORMULA_STOCK";
    public static final String APLICA_FORMULA_PV_STOCK = "APLICA_FORMULA_PV_STOCK";
    public static final String INCREMENTO_COSTO_STOCK = "INCREMENTO_COSTO_STOCK";
    public static final String INCREMENTO_PRECIO_VENTA = "INCREMENTO_PRECIO_VENTA";

    //Constantes Contabilidad
    public static final String CODIGO_DEF_MONEDA_CONTABILIDAD = "COD_MONEDA_BASE_CO";
    public static final String CODIGO_CUENTA_CAJA = "CODIGO_CUENTA_CAJA";
    public static final String CODIGO_CUENTA_PAGAR = "CODIGO_CUENTA_PAGAR";
    public static final String CODIGO_CUENTA_COBRAR = "CODIGO_CUENTA_COBRAR";
    public static final String CODIGO_CUENTA_RETENCION = "CODIGO_CUENTA_RETENCION";
    public static final String CODIGO_CUENTA_PREVISION_CONTADO = "COD_CUENTA_PROV_CONTADO";
    public static final String CODIGO_FONDO_FIJO = "CODIGO_FONDO_FIJO";
    public static final String CODIGO_BANCO = "CODIGO_BANCO";
    public static final String CODIGO_GRUPO_CONTRATO = "CODIGO_GRUPO_CONTRATO";
    public static final String CONTRATO_COMPLETA_NOMBRE_CLIENTE = "CONTRATO_COMPLETA_NOMBRE_CLIENTE";

    public static final String CODIGO_TIPO_JOIN = "J";
    public static final String CUENTA_RRHH = "CUENTA_RRHH";
    public static final String ARTICULO_DEFAULT = "ARTICULO_DEFAULT";
    public static final String CATEGORIA_DEFAULT = "CATEGORIA_DEFAULT";

    //Relaciones Cuentas
    public static final String CODIGO_CUENTA_IVA = "10";
    public static final String CODIGO_IVA_CREDITO_10 = "CODIGO_IVA_CREDITO_10";
    public static final String CODIGO_IVA_CREDITO_5 = "CODIGO_IVA_CREDITO_5";
    public static final String CODIGO_CUENTA_IVA_INGRESO = "10_I";
    public static final String CODIGO_CUENTA_IVA_5_INGRESO = "5_I";

    //Permisos Especiales
    public static final String CONSULTA_TODAS_HABILITACIONES = "CONSULTA_TODAS_HABIL";
    public static final String APLICA_DESCUENTO = "APLICA_DESCUENTO";
    public static final String MODIFICA_USUARIO_RECEPTOR = "MODIFICA_USUARIO_RECEPTOR";
    public static final String MODIFICA_PRECIO = "MODIFICA_PRECIO";
    public static final String MODIFICA_TIPO_PRECIO = "MODIFICA_TIPO_PRECIO";
    public static final String PRECIO_POR_SUCURSAL = "PRECIO_POR_SUCURSAL";
    public static final String CARGA_INVENTARIO = "CARGA_INVENTARIO";
    public static final String COBRA_FACTURA = "COBRA_FACTURA";
    public static final String GENERA_CONTADO_PENDIENTE = "GENERA_CONTADO_PENDIENTE";
    public static final String GENERA_TICKET_FACTURA = "GENERA_TICKET_FACTURA";

    public static final float LOGO_ANCHO = 680f;
    public static final float LOGO_ALTO = 80f;
    public static final float LOGO_ANCHO_HORIZONTAL = 200f;
    public static final float LOGO_ALTO_HORIZONTAL = 80f;
    public static final String LOGO_PATH = "logo";
    public static final String LOGO_IZQ = "logo_sup_izq.png";
    public static final String LOGO_DER = "CAPI.png";

    //Presupuesto
    public static final String PRESUPUESTO_ESTADO_ABIERTO = "A";
    public static final String PRESUPUESTO_ESTADO_HISTORICO = "H";

//Utiles
    public static final String TODAS = "Consolidado";
    public static final String CODIGO_CATEGORIA_SALARIOS = "1";
    public static final String CODIGO_CATEGORIA_BENEFICIOS_COMPLEMENTARIOS = "2";
    public static final String CODIGO_CATEGORIA_VIAJES_Y_TRANSPORTE = "4.A";
    public static final String CODIGO_CATEGORIA_EQUIPOS = "4.B";

    public static final String CODIGO_CATEGORIA_SUMINISTRO_MATERIALES = "4.C";
    public static final String CODIGO_CATEGORIA_COMUNICACIONES = "4.D";
    public static final String CODIGO_CATEGORIA_CONTRACTUAL = "4.E";
    public static final String CODIGO_CATEGORIA_MSI = "4.E.1";

    public static final String CODIGO_CATEGORIA_OTROS = "4.F";

    public static final Integer FONT_SIZE_FOR_XLS_REPORT = 8;

    public static final String PRESUPUESTO_TEMPLATE_NAME = "presupuesto_template.xlsx";
    public static final String PRESUPUESTO_OUTPUT_NAME = "presupuesto_output.xlsx";
    public static final String PRESUPUESTO_CATEGORIA_TEMPLATE_NAME = "presupuesto_categoria_template.xlsx";
    public static final String PRESUPUESTO_CATEGORIA_OUTPUT_NAME = "presupuesto_categoria_output.xlsx";
    public static final String EMAIL_GANADOR_TEMPLATE_NAME = "Emailganador.docx";
    public static final String EMAIL_PERDEDOR_TEMPLATE_NAME = "EmailPerdedor.docx";
    public static final String PAGOS_TEMPLATE_NAME = "pagos_realizados_template.xlsx";
    public static final String DETALLE_RENDICION_TEMPLATE_NAME = "detalle_rendicion_template.xlsx";
    public static final String DETALLE_RENDICION_OUTPUT = "detalle_rendicion.xlsx";
    public static final String PAGOS_INGRESO_EGRESO_TEMPLATE_NAME = "pagos_ingreso_egreso_template.xlsx";

    public static final String PAGOS_TC_TEMPLATE_NAME = "pagos_realizados_tipo_cambio_template.xlsx";

    public static final String FACT_ANULADAS = "fanuladas_doc.xlsx";
    public static final String FACT_ANULADAS_TEMPLATE = "fanuladas.xlsx";
    public static final String FACT_RECIBIDAS = "frecibidas_doc.xlsx";
    public static final String FACT_RECIBIDAS_TEMPLATE = "frecibidas.xlsx";
    public static final String FACT_RECIBIDAS_2 = "frecibidas2_doc.xlsx";
    public static final String FACT_RECIBIDAS_TEMPLATE_2 = "frecibidas2.xlsx";

    public static final String TIMBRADO_EMPRESA = "12072593";
    public static final String RUC_CLIENTE_CONSOLIDADO = "44444401";

    public static final String VEN_TIP_IMP = "I";
    public static final String DV_CLIENTE_CONSOLIDADO = "7";
    public static final String NOMBRE_CLIENTE_CONSOLIDADO = "IMPORTES CONSOLIDADOS";
    public static final String VENTA_CODIGO_OBLIGACION = "921";
    public static final String VENTA_CODIGO_FORMULARIO = "221";
    public static final String NOMBRE_EMPRESA = "GF MULTITIENDAS";
    public static final String SET_TIPO_OPERACION = "0";
    public static final String NOMBRE_CONCILIACION = "RPTCONCI";
    public static final String HECHAUKA_TEMPLATE = "hechauka.xlsx";
    public static final String HECHAUKA = "hechauka_doc.xlsx";
    public static final String HECHAUKA_COMPRA_TEMPLATE = "hechauka_compra.xlsx";
    public static final String HECHAUKA_COMPRA = "hechauka_compra_doc.xlsx";
    public static final String nombreFormaHechaukaV2 = "HECKV2";
    public static final String RUC_REPRESENTANTE_LEGAL = "1234567-9";
    public static final String DV_REPRESENTANTE_LEGAL = "9";
    public static final String NOMBRE_REPRESENTANTE_LEGAL = "CONFIGURAR REPRESENTANTE";
    public static final String HECHAUKA_TITULAR = "HECHAUKA_TITULAR";
    public static final String PAGOS_OUTPUT_NAME = "pagos_realizados_output.xlsx";
    public static final String PAGOS1_INGRESO_EGRESO_OUTPUT_NAME = "pagos_ingreso_egreso_output.xlsx";
    public static final String PAGOS_ANULADOS_OUTPUT_NAME = "pagos_anulados_output.xlsx";
    public static final String CONTRAPARTIDA_TEMPLATE_NAME = "contrapartida_template.xlsx";
    public static final String CONTRAPARTIDA_OUTPUT_NAME = "contrapartida_output.xlsx";
    public static final String FLUJO_CAJA_TEMPLATE_NAME = "flujo_caja_template.xlsx";
    public static final String FLUJO_CAJA_OUTPUT_NAME = "flujo_caja_output.xlsx";
    public static final String PRESUPUESTO_CAT_TEMPLATE_NAME = "presupuesto.xlsx";
    public static final String PRESUPUESTO_CAT_OUTPUT_NAME = "presupuesto_output.xlsx";
    public static final String CONCILIACION_BANCARIA_TEMPLATE_NAME = "conciliacion_bancaria_template_v2.xlsx";
    public static final String CONCILIACION_BANCARIA_OUTPUT_NAME = "conciliacion_bancaria_output.xlsx";
    public static final String CONCILIACION_BANCARIA_V3_TEMPLATE_NAME = "conciliacion_bancaria_template_v3.xlsx";
    public static final String CONCILIACION_BANCARIA_V3_OUTPUT_NAME = "conciliacion_bancaria_output_v3.xlsx";
    public static final String CRONOGRAMA_PAGOS_TEMPLATE_NAME = "cronograma_pagos_template.xlsx";
    public static final String CRONOGRAMA_PAGOS_OUTPUT_NAME = "cronograma_pagos_output.xlsx";
    public static final String CRONOGRAMA_PAGOS_CAB_TEMPLATE_NAME = "cronograma_pagos_cab_template.xlsx";
    public static final String OBLIGACIONES_DETALES_TEMPLATE_NAME = "obligaciones_detalle_actividad_template.xlsx";
    public static final String CRONOGRAMA_PAGOS_DET_TEMPLATE_NAME = "cronograma_pagos_det_actividad_template.xlsx";
    public static final String CRONOGRAMA_PAGOS_CAB_OUTPUT_NAME = "cronograma_pagos_cab_output.xlsx";
    public static final String INVENTARIO_CONTRATOS_TEMPLATE_NAME = "inventario_contratos_template.xlsx";
    public static final String INVENTARIO_CONTRATOS_OUTPUT_NAME = "inventario_contratos_output.xlsx";
    public static final String PRESUPUESTO_DETALLE_TEMPLATE_NAME = "presupuesto_detalle_template.xlsx";
    public static final String PRESUPUESTO_DETALLE_OUTPUT_NAME = "presupuesto_detalle_output.xlsx";
    public static final String OBJETIVO_TEMPLATE_NAME = "objetivo_template.xlsx";
    public static final String OBJETIVO_OUTPUT_NAME = "objetivo_output.xlsx";
    public static final String RDA_TEMPLATE_NAME = "rda.docx";
    public static final String TEMPLATE_INSTITUCIONAL = "rda-02.docx";
    public static final String TEMPLATE_CONACYT = "rda-03.docx";
    public static final String TEMPLATE_DELOITE_FONACIDE = "rda-04.docx";
    public static final String TEMPLATE_DELOITE_PRESUPUESTO_CIUDADANO = "rda-05.docx";
    public static final String RDA_TEMPLATE_NAME_GENERICO = "rda-generico.docx";
    public static final String GRILLA_EVALUACION_PUNTAJE_TEMPLATE_NAME = "Grilla de Evaluación Puntaje.docx";
    public static final String GRILLA_EVALUACION_PUNTAJE_PJ = "GrillaEvaluacionPuntaje_PJ.docx";
    public static final String GRILLA_EVALUACION_PUNTAJE_PF = "GrillaEvaluacionPuntaje_PF.docx";
    public static final String TDR_TEMPLATE_NAME = "tdr.docx";
    public static final String TDR_MACRO_TEMPLATE_NAME = "tdr.docm";
    public static final String TDR_MACRO_TEMPLATE_GRAL_NAME = "tdrGral.docm";
    public static final String TDR_CONTRATACION_DIRECTA_TEMPLATE_NAME = "tdr_cd.docx";
    public static final String TDR_CONTRATACION_DIRECTA_MACRO_TEMPLATE_NAME = "tdr_cd.docm";
    public static final String TDR_CONTRATACION_DIRECTA_GRAL_MACRO_TEMPLATE_NAME = "tdr_cd_gral.docm";
    public static final String OMC_TEMPLATE_NAME = "omc.xlsx";
    public static final String OMC_TEMPLATE_OUTPUT_NAME = "omc_output.xlsx";
    public static final String RDV_TEMPLATE_NAME = "rdv.xlsx";
    public static final String RDV_PDG_TEMPLATE_NAME = "rdv_pdg.xlsx";
    public static final String RDV_TEMPLATE_OUTPUT_NAME = "rdv_output.xlsx";
    public static final String OC_MODIF_TEMPLATE_NAME = "modificacion_oc.docx";
    public static final String CONTRATO_MODIF_TEMPLATE_NAME = "modificacion_contrato.docx";
    public static final String PLANILLA_EVALUACION_CONSULTORES_TEMPLATE_NAME = "planilla_evaluacion_consultores.docx";
    public static final String ODC_TEMPLATE_NAME = "odc.docx";
    public static final String CONTRATO_TEMPLATE_NAME = "contrato.docm";
    public static final String CONTRATO_GRAL_TEMPLATE_NAME = "contratoGral.docm";
    public static final String MEMO_TEMPLATE_NAME = "tmemo.docx";
    public static final String ATV_TEMPLATE_NAME = "ATV.docx";
    public static final String PLANILLA_IPS_TEMPLATE = "planilla_ips_template.xlsx";
    public static final String PLANILLA_IPS_OUTPUT = "planilla_ips_output.xlsx";
    public static final String PLANILLA_IPS_MTEES_TEMPLATE = "planilla_ips_mtees_template.xls";
    public static final String PLANILLA_IPS_MTEES_OUTPUT = "planilla_ips_mtees_output.xls";
    public static final String LISTADO_ANTICIPOS_TEMPLATE = "listado_anticipos_template.xlsx";
    public static final String LISTADO_ANTICIPOS_OUTPUT = "listado_anticipos_output.xlsx";
    public static final String RECIBO_ANTICIPO_TEMPLATE_NAME_DOCX = "recibo_anticipo.docx";
    public static final String PROPUESTA_VACACIONES_TEMPLATE = "propuesta_vacaciones_template.xlsx";
    public static final String PROPUESTA_VACACIONES_OUTPUT = "propuesta_vacaciones_output.xlsx";
    public static final String PLANILLA_BANCO_TEMPLATE = "planilla_banco_template.xlsx";
    public static final String PLANILLA_DAS_BANCO_TEMPLATE = "planilla_das_banco_template.xlsx";
    public static final String PLANILLA_BANCO_OUTPUT = "planilla_banco_output.xlsx";
    public static final String RECIBO_VACACIONES_TEMPLATE = "recibo_vacaciones_template.xlsx";
    public static final String RECIBO_VACACIONES_OUTPUT = "recibo_vacaciones_output.xlsx";
    public static final String LISTA_EMPLEADO_POR_CATEGORIA_TEMPLATE = "lista_empleado_por_categoria_template.xlsx";
    public static final String LISTA_EMPLEADO_POR_CATEGORIA_OUTPUT = "lista_empleado_por_categoria_output.xlsx";
    public static final String LISTA_ALTA_MASIVA_TEMPLATE = "lista_alta_masiva_template.xls";
    public static final String LISTA_ALTA_MASIVA_OUTPUT = "lista_alta_masiva_output.xls";
    //public static final String MEMOTWO_TEMPLATE_NAME = "tmemo.docx";
    //public static final String MEMO_MACRO_TEMPLATE_NAME = "tmemo.docm";
    //public static final String MEMOTWO_MACRO_TEMPLATE_NAME = "tmemo.docm";
    public static final String LISTA_CONTRATO_TEMPLATE = "lista_contrato_template.xlsx";
    public static final String LISTA_CONTRATO_OUTPUT = "lista_contrato_output.xlsx";
    public static final String LISTA_CONTRATO_CAB_TEMPLATE = "lista_contrato_cab_template.xlsx";
    public static final String LISTA_CONTRATO_CAB_OUTPUT = "lista_contrato_cab_output.xlsx";
    public static final String LISTA_CONTRATO_DET_TEMPLATE = "lista_contrato_det_template.xlsx";
    public static final String LISTA_CONTRATO_DET_OUTPUT = "lista_contrato_det_output.xlsx";
    public static final String LISTA_TODOS_CONTRATO_DET_TEMPLATE = "lista_todos_contrato_det_template.xlsx";
    public static final String LISTA_TODOS_CONTRATO_DET_OUTPUT = "lista_todos_contrato_det_output.xlsx";
    public static final String LISTA_CONTRATO_SENABICO_TEMPLATE = "lista_contrato_senabico_template.xlsx";
    public static final String LISTA_CONTRATO_SENABICO_OUTPUT = "lista_contrato_senabico_output.xlsx";
    public static final String PLANTILLA_ASISTENCIA_NAME = "Planilla de Asistencia CTE -083.docx";
    public static final String GRILLA_EVALUACION_RDC_NAME = "Grilla de Evaluación RDC 083-C2-2016.docx";
    public static final String GRILLA_EVALUACION_RDC_PJ = "GrillaEvaluacion_PJ.docx";
    public static final String GRILLA_EVALUACION_RDC_PF = "GrillaEvaluacion_PF.docx";
    public static final String CONFLICTO_INTERESES_NAME = "Conflicto de Intereses -.docx";
    public static final String CONTROL_DOCUMENTACIONES_NAME = "5.Control documentaciones legales administrativas- RDC 083.docx";
    public static final String INSTRUCCIONES_CTE_RDC_NAME = "2.Instrucciones para  CTE - RDC 083.docx";
    public static final String CERTIFICADO_CONFIDENCIALIDAD_NAME = "Certificado Confidencialidad.docx";
    public static final String CODIGO_COMPONENTE_UNO = "1";
    public static final String CODIGO_COMPONENTE_DOS = "2";
    public static final String CODIGO_COMPONENTE_TRES = "3";

    public static final String TIPO_REPOSICION = "REP";
    public static final String TIPO_RENDICION = "REN";
    public static final String NOMBRE_FORMA_HECHAUKA = "HECHK";
    public static final String NOMBRE_FORMA_HECHAUKA_COMPRA = "HECHKC";

    public static final String EVALUACION_TEMPLATE_NAME = "evaluacion.docx";

    public static final String CONTRATACION_DIRECTA_PJ = "contratacion_directa_pj.docx";
    public static final String CONTRATACION_DIRECTA_PF = "contratacion_directa_pf.docx";

    public static final String NOTIFICACION_CONTRATACION_DIRECTA_ADJ = "NotiAdjAnexo.docx";
    public static final String NOTIFICACION_CONTRATACION_DIRECTA_NO_ADJ = "NotiNoAdjAnexo.docx";

    public static final String PLANILLA_SALARIO_TEMPLATE_NAME = "planilla_salario.xlsx";
    public static final String PLANILLA_SALARIO_TEMPLATE_OUTPUT_NAME = "planilla_salario_output.xlsx";

    public static final String PLANILLA_AGUINALDO_TEMPLATE_NAME = "planilla_aguinaldo.xlsx";
    public static final String PLANILLA_AGUINALDO_TEMPLATE_OUTPUT_NAME = "planilla_aguinaldo_output.xlsx";

    public static final String PLANILLA_MTESS_TEMPLATE_NAME = "planilla_mtess.xlsx";
    public static final String PLANILLA_MTESS_TEMPLATE_OUTPUT_NAME = "planilla_mtess_output.xlsx";

    public static final String PLANILLA_MTESS_MENSUAL_TEMPLATE_NAME = "planilla_mtess_mensual.xlsx";
    public static final String PLANILLA_MTESS_MENSUAL_TEMPLATE_OUTPUT_NAME = "planilla_mtess_mensual_output.xlsx";

    public static final String PLANILLA_RECIBO_SALARIO_TEMPLATE_NAME = "recibo_salario.xlsx";
    public static final String PLANILLA_RECIBO_SALARIO_TEMPLATE_OUTPUT_NAME = "recibo_salario_output.xlsx";

    public static final String PLANILLA_RECIBO_AGUINALDO_TEMPLATE_NAME = "recibo_aguinaldo.xlsx";
    public static final String PLANILLA_RECIBO_AGUINALDO_TEMPLATE_OUTPUT_NAME = "recibo_aguinaldo_output.xlsx";

    public static final String PLANILLA_EMPLEADOS_TEMPLATE_NAME = "planilla_empleados.xlsx";
    public static final String PLANILLA_EMPLEADOS_TEMPLATE_OUTPUT_NAME = "planilla_empleados_output.xlsx";

    public static final String PLANILLA_EMPLEADO_TEMPLATE_NAME = "planilla_empleado.xlsx";
    public static final String PLANILLA_EMPLEADO_TEMPLATE_OUTPUT_NAME = "planilla_empleado_output.xlsx";

    public static final String PLANILLA_TIMESHEET_TEMPLATE_NAME = "TimeSheet.xlsx";
    public static final String PLANILLA_TIMESHEET_TEMPLATE_OUTPUT_NAME = "TimeSheet_output.xlsx";

    public static final String LIQUIDACION_TEMPLATE_NAME = "liquidacion_salario.xlsx";
    public static final String LIQUIDACION_TEMPLATE_OUTPUT_NAME = "liquidacion_salario_output.xlsx";

    public static final String PLANILLA_MOVIMIENTO_MAQUINAS_TEMPLATE = "movimiento_maquinas.xlsx";
    public static final String PLANILLA_MOVIMIENTO_MAQUINAS_TEMPLATE_OUTPUT = "movimiento_maquinas_output.xlsx";
    public static final String PLANILLA_ACTIVIDAD_CONTRATO_TEMPLATE = "actividad_contrato.xlsx";
    public static final String PLANILLA_ACTIVIDAD_CONTRATO_TEMPLATE_OUTPUT = "actividad_contrato_output.xlsx";
    /**
     * ***************************
     * TIPO DE PASOS
     *
     */
    public static final String PASO_GUARDAR = "G";
    public static final String PASO_RECHAZO_CONTABILIDAD = "RC";
    public static final String PASO_MODIFICADO_TESORERIA = "MT";
    public static final String PASO_PENDIENTE_EVALUACION = "PEV";
    public static final String PASO_PENDIENTE_AUTORIZACION_ADMINISTRACION = "AC";
    public static final String PASO_PENDIENTE_AUTORIZACION_DP = "ADP";
    public static final String PASO_PENDIENTE_AUTORIZACION_DG = "ADG";
    public static final String PASO_PENDIENTE_GENERACION_ASIENTOS = "GAC";
    public static final String PASO_PENDIENTE_IMPRESION = "TPI";
    public static final String PASO_PENDIENTE_PUBLICACION = "PP";
    public static final String PASO_PENDIENTE_FIRMA = "PPF";
    public static final String PASO_CONTRATO_FINALIZADO = "PCF";
    public static final String PASO_FINALIZAR = "FIN";
    public static final String PASO_RECHAZADO_GENERACION_ASIENTOS = "RAC";
    public static final String PASO_RECHAZADO_ADMINISTRACION = "RA";
    public static final String PASO_RECHAZADO_DP = "RDP";
    public static final String PASO_RECHAZADO_DG = "RDG";
    public static final String PASO_RECHAZADO_PENDIENTE_IMPRESION = "RTPI";
    public static final String PASO_NOTIFICACION_EVALUACIONES = "NE";
    public static final String PASO_PENDIENTE_GENERACION_CONTRATO = "PGC";

    public static final String TAB = "\t";

//    Libro de Compras
    //Cabecera
    public static final String COMPRA_TIPO_REGISTRO = "1";
    public static final String COMPRA_TIPO_REPORTE_ORIGINAL = "1";
    public static final String COMPRA_TIPO_REPORTE_RECTIFICATIVA = "2";
    public static final String COMPRA_CODIGO_OBLIGACION = "911";
    public static final String COMPRA_CODIGO_FORMULARIO = "211";
    public static final String COMPRA_VERSION = "2";
    //Detalle
    public static final String COMPRA_TIPO_REGISTRO_DETALLE = "2";
    public static final String COMPRA_TIPO_DOCUMENTO_FACTURA = "1";

    public static final String SET_TIPO_DOC_FACTURA = "1";
    public static final String SET_TIPO_DOC_NOTA_DEBITO = "2";
    public static final String SET_TIPO_DOC_NOTA_CREDITO = "3";
    public static final String SET_TIPO_DOC_DESPACHO = "4";
    public static final String SET_TIPO_DOC_AUTO_FACTURA = "5";
    public static final String SET_TIPO_DOC_PASAJE_AEREO = "7";
    public static final String SET_TIPO_DOC_FACTURA_EXTERIOR = "8";
    public static final String SET_TIPO_DOC_PLANILLA_SUELDOS = "9";
    public static final String SET_TIPO_DOC_COMPROBANTE_INGRESO = "10";
    public static final String SET_TIPO_DOC_RETENCION_ABSORBIDA = "11";
    public static final String SET_TIPO_DOC_PASAJE_AEREO_ELECTRONICO = "13";

    public static final String SET_TIPO_OP_GRAVADA = "8";
    public static final String SET_TIPO_OP_EXCENTA = "11";

    public static final String SET_CONDICION_COMP_CREDITO = "1";
    public static final String SET_CONDICION_COMP_CONTADO = "2";

    public static final String CUENTA_CORRIENTE_DESCRIPCION = "CUENTA CORRIENTE";

    public static final String TIPO_OPERACION_GIRO = "G";
    public static final String TIPO_OPERACION_DEPOSITO = "D";

    public static final String CODIGO_CAUSA_CHEQUE = "CH";
    public static final String CODIGO_CAUSA_DEPOSITO = "DE";

    public static final String CONCEPTO_FONDO_FIJO = "FONDO FIJO";

    public static final String VENTAS = "ventas_doc.xlsx";
    public static final String VENTAS_TEMPLATE = "ventas.xlsx";

    public static final String CONSULTA_SALDO_PROVEEDORES = "consulta_saldos_proveedores_output.xlsx";
    public static final String CONSULTA_SALDO_PROVEEDORES_TEMPLATE = "consultas_saldos_proveedores_template.xlsx";

    //public static final String MOVIMIENTO_ART = "movimiento_art_doc.xlsx";
    //public static final String MOVIMIENTO_ART_TEMPLATE = "movimiento_art.xlsx";
    public static final String CODIGO_MOTIVO_ES_ENTRADA = "E";
    public static final String CODIGO_MOTIVO_ES_SALIDA = "S";
    public static final String TIPO_MOV_TRANSFERENCIA = "T";
    public static final String ESTADO_MOV_APROBADO = "A";

    //<editor-fold defaultstate="collapsed" desc="WORK PLAN">
    public static final Integer SUB_OBJETIVO_LEVEL = 3;
    public static final Integer OUTPUT_LEVEL = 5;
    public static final Integer RESULTADOS_LEVEL = 7;
    public static final Integer SUB_RESULTADO_LEVEL = 9;
    public static final Integer ACTIVIDAD_LEVEL = 11;

//</editor-fold>
    public static final List<String> MESES = new ArrayList<String>(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
    public static final List<String> DIAS = new ArrayList<String>(Arrays.asList("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"));

    public static final String CODIGO_COMPONENTE_ADMINISTRATIVO = "5";

    public static final String COD_PROVEEDOR_BENEFICIARIO = "COD_PROVEEDOR_BENEFICIARIO";

//<editor-fold defaultstate="collapsed" desc="Proceso Contrato Paso">
    public static final String PASO_CONTRATO_1 = "1";
    public static final String PASO_PENDIENTE_AUTORIZACION_AT = "AAT";
    public static final String PASO_PENDIENTE_VERIFICACION_AT = "PVAT";
    public static final String PASO_RECEPCIONAR_PROPUESTAS = "PRP";
    public static final String PASO_CONFORMAR_COMITE_EVAL = "PCCE";
    public static final String PASO_EVALUAR_PROPUESTAS = "PEP";
    public static final String PASO_GENERACION_CONTRATO = "PGC";
    public static final String PASO_NOTIFICACION_EVALUACION = "NE";

    public static final String PASO_RECHAZADO_AT = "RAT";

    public static final String PASO_PENDIENTE_AUTORIZACION_GERENCIA = "PAG";
    public static final String PASO_RECHAZADO_GERENCIA = "RG";

    public static final String PASO_PENDIENTE_AUTORIZACION_COP = "PACOP";
    public static final String PASO_RECHAZADO_COP = "RCOP";
    public static final String PASO_RECHAZADO_CTE = "RCTE";

    public static final String PASO_PENDIENTE_AUTORIZACION_CONTRATOS = "PACO";
    public static final String PASO_RECHAZADO_CONTRATOS = "RCO";

    public static final String PASO_PENDIENTE_VERIFICACION_RDC = "PVRDC";
    public static final String ESTADO_RECEPCIONAR_PROPUESTA_CD = "PRPCD";
    public static final Integer NRO_PASO_RECEPCIONAR_PROPUESTA_CD = 14;

    public static final String NOMBRE_FORMA_AUTORIZA_SOLICITUD_AT = "PROASAT";

    public static final String NOMBRE_FORMA_AUTORIZA_SCAV_GERENCIA = "PROAGE";
    public static final String NOMBRE_FORMA_AUTORIZA_SCAV_COP = "PROACOP";
    public static final String NOMBRE_FORMA_AUTORIZA_SCAV_CONTRATOS = "PROACON";
    public static final String NOMBRE_FORMA_RECEPCIONAR_PROPUESTAS_CONTRATACION_DIRECTA = "PRORPCD";
    public static final String NOMBRE_FORMA_SOLICITUD_ADENDA = "PROSOAD";
    public static final String NOMBRE_FORMA_GENERA_CONTRATOS = "PROGECON";
    public static final String NOMBRE_FORMA_SOLICITAR_MODIF_CONTRATO = "SOLMCON";
    public static final String NOMBRE_FORMA_SOLICITUD_PUBLICACION = "PRSP";

    public static final String NOMBRE_FORMA_PROCESAR_EVALUACION = "PROEVAL";

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Contratos">
    public static final String PROPUESTA_TECNICA_CODIGO = "PT";
    public static final String FORMACION_ACADEMICA_CODIGO = "FA";
    public static final String PROPUESTA_ECONOMICA = "PE";
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Estados registro">
    public static final String ESTADO_PENDIENTE = "PEN";
    public static final String ESTADO_COMMIT = "COMMIT";

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Constantes TDR">
    public static final String TDR_ANTECEDENTES = "El Centro de Estudios Ambientales y Sociales (CEAMSO) es una Organización No Gubernamental, de utilidad pública, reconocida por Personería Jurídica N° 22.367 en Agosto de 1998.  Con la constitución de CEAMSO, se busca fortalecer institucionalmente a las entidades y organismos del estado así como generar mecanismos que mejoren su transparencia e integridad, con acciones innovadoras que mejoren la calidad de vida de los habitantes del Paraguay.\n"
            + "En la actualidad,  CEAMSO gerencia varios programas incluyendo el Programa de Democracia y Gobernabilidad (PDG) de la Agencia de los Estados Unidos para el Desarrollo Internacional (USAID).  El objetivo de este Programa es contribuir a mejorar la efectividad del gobierno paraguayo en desarrollar su capacidad institucional y de recursos humanos así como consolidar los mecanismos de transparencia y rendición de cuentas, estableciendo un camino claro hacia la mayor capacidad de respuesta a las necesidades y demandas ciudadanas y una base más sólida para la democracia y el estado de derecho.\n"
            + "Para lograr este propósito se trabaja en fortalecer el control interno y los sistemas de administración en instituciones públicas claves. El Programa se enfoca en funciones prioritarias como el servicio civil, la gerencia de las finanzas públicas, las instancias de contraloría y auditoría, la participación ciudadana y el fortalecimiento de la capacidad institucional de CEAMSO, como organismo implementador.\n"
            + "De allí, que el PDG focaliza su apoyo en cuatro (4) objetivos estratégicos o Componentes (C), a saber:\n"
            + "1. Mejorar la capacidad de gestión de las instituciones claves seleccionadas (C1).\n"
            + "2. Fortalecer el control interno, la rendición de cuentas, transparencia y las acciones\n"
            + "   anticorrupción que desarrollan estas instituciones (C2).\n"
            + "3. Mejorar el marco legal y las políticas  públicas tendientes a mejorar la efectividad del\n"
            + "   Estado (C3).\n"
            + "4. Fortalecer el desarrollo institucional de CEAMSO para sostener los proyectos y sus logros,\n"
            + "   que permita avanzar hacia la democracia y el buen gobierno en el Paraguay en el futuro (C4).\n"
            + "\n"
            + "Además de cuatro (4) áreas transversales que orientan el desarrollo de los ejes estratégicos y son: Tecnologías de Información y Comunicación (TICs), Comunicación, Género y Monitoreo.";
    public static final String TDR_ACTIVIDADES = "Para el cumplimiento de los objetivos, la consultora realizará las siguientes actividades enunciativas pero no limitadas de otras actividades conducentes a los objetivos esperados:";
    public static final String TDR_PRODUCTO_ENT_FORMA_ENTREGA = "Todos los productos deberán ser entregados en formato digital editable (Word) y en PDF, en formato impreso (2 copias) y digital, debidamente archivados.";
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Plantilla disposiciones Generales">
    public static final String DISPOSICIONES_GENERALES = "La presente Orden está financiado mediante un contrato entre "
            + "el cliente de CEAMSO y CEAMSO. El Proveedor está de acuerdo que no tomará parte en ninguna actividad que "
            + "entren en conflicto con la entrega de los artículos y servicios requeridos, que ejercerá la responsabilidad "
            + "fiscal para el mejor interés de CEAMSO y el cliente de CEAMSO, que pasará cualquier descuento comercial "
            + "obtenido en la entrega de los artículos y servicios requeridos, que ha proporcionado todos los datos de "
            + "costos/precios que son actuales, completos y precisos (a partir de la fecha de este premio) para que CEAMSO "
            + "pueda determinar la razonabilidad de los precios, que cumplirá con todas las leyes y reglamentos nacionales, "
            + "estatales, regionales y locales aplicables, que no está afiliado a ninguna oficina o agencia del gobierno "
            + "(excepto como se indica en la Portada del Premio) y no ha ofrecido, proporcionado o prometido un regalo, "
            + "pago, o cualquier cosa de valor a ningún funcionario, empleado o representante de CEAMSO o cualquier entidad "
            + "gubernamental (tanto a nivel local y estatal, EE.UU. y fuera de los Estados Unidos), en relación con esta "
            + "orden de compra.  ";
    public static final String LA_CONDUCTA_PROFESIONAL = "El Proveedor se compromete a siempre comportarse de una manera profesional en la ejecución de este acuerdo y se abstendrán de participar en cualquier actividad que pueda razonablemente interpretarse como ilegal, inmoral o que constituyan un reflejo pobre de CEAMSO o de su cliente. El  proveedor deberá defender y librar de responsabilidad CEAMSO contra cualquier reclamación, pérdida, daño o lesión que surja directa o indirectamente de los errores, actos, omisiones o mala conducta del  Proveedor.";
    public static final String LA_RELACION_INDEPENDENTE = "Nada en esta orden se interpretará como la creación de nada además de una relación independiente de contratista/ Proveedor, o comprador/vendedor entre CEAMSO y el Proveedor. El Proveedor es responsable del pago de sus ingresos y los impuestos de ventajas sociales, renta, tarifas de aduanas y licencia, IVA, transporte, seguros, y todos otros costos de hacer negocios y todos los costos de sus proveedores.";
    public static final String INSPECCION_ACEPTACION_RECIBOS = "Todos los equipos, materiales, suministros, software, y servicios prestados a continuación están sujetos a la aceptación por el Representante del Proyecto CEAMSO. Equipos, materiales, servicios y suministros que se encuentran razonablemente satisfactorios para CEAMSO o que no cumplen con los estándares profesionales normales serán rechazados por CEAMSO o corregidas inmediatamente por el Proveedor (a la discreción de CEAMSO), y un ajuste equitativo hecho por el Representante de Contratos de CEAMSO. Todos los artículos enviados y en garantía deben ir acompañados por documentación válida de envío y de garantía.";
    public static final String LA_GARANTIA_PROFESIONAL = "El Proveedor garantiza que la calidad de los suministros y servicios requeridos cumplen las normas profesionales generalmente aceptadas de comercialización en los Estados Unidos. El Proveedor garantiza la seguridad de todos los equipos y se compromete a asumir toda la responsabilidad por su fracaso y/o causa de lesión. Menos de pleno rendimiento resultará en una disminución equitativa en el precio fijo. A menos que se acuerde expresamente por ambos partidos, todas las prestaciones deben ser empacadas apropiadamente para la entrega segura y mínimo de 12 meses de almacenamiento en seco.";
    public static final String ASIGNACION_NOVACION = "El Proveedor no transferirá ni asignará este acuerdo, su derecho a los pagos debidos o a ser debidos, ni a ningún derecho u obligaciones bajo los términos aquí establecidos, a cualquier entidad sin el consentimiento previo por escrito del Director de Programa de CEAMSO, el cual no se debe irrazonablemente denegar. Ninguna asignación, novación, u otra delegación de responsabilidad, con o sin el consentimiento de CEAMSO, debe aliviar el  Proveedor de cualquiera de sus obligaciones según este acuerdo, o perjudicar cualquiera de los derechos de CEAMSO contra el  Proveedor, ya sea que surjan antes o después la fecha de cualquier asignación.";
    public static final String SUBCONTRATACION_CONSULTORES = "A. Se requiere la autorización previa por escrito del Director de Programa de CEAMSO para la obtención de servicios de consultores y Proveedores de menor nivel. Los costos de los consultores y Proveedores de menor nivel que no han recibido la aprobación previa por escrito de acuerdo con esta cláusula no serán permitidos. Inclusión en el presupuesto o la propuesta del Proveedor no constituye una solicitud o aprobación de Proveedores de menor nivel.\n"
            + "B. Al solicitar el uso de consultores, el Proveedor deberá proporcionar la siguiente información: Curriculum Vitae, el Formulario SF1420-17 Bio-Data debidamente firmado, y el nivel de esfuerzo. \n"
            + "C. Consentimiento para un subcontrato no constituye una determinación de la aceptabilidad de los términos o precio del subcontrato o de la admisibilidad de los gastos. Si se solicita CEAMSO, se proporcionará una copia del subcontrato propuesto para tomar una decisión de aceptabilidad.";
    public static final String ENTREGA_VARIACION_EN_CANTIDAD = "A. El Proveedor será responsable de la ejecución de los servicios y la entrega de los suministros en el (los) sitio(s) especificado(s) en la Portada del Premio. Todos los riesgos y costos incurridos antes de la entrega y la aceptación final de CEAMSO serán por cuenta exclusiva del Proveedor. Si bajo este acuerdo se requieren servicios de traducción, impresión, u otros servicios o suministros de cantidad de gran volumen, una variación de +/- 10% de las cantidades citadas podrá ser autorizada por CEAMSO a la misma tasa por unidad mediante acuerdo escrito. \n"
            + "B. Si los servicios no se llevan a cabo y/o no se entregan suministros de manera oportuna, y en todos aspectos, de conformidad con el acuerdo, se puede requerir que el Proveedor reembolse CEAMSO por cualquier pérdida o gasto incurrido por CEAMSO que pueda resultar. El Proveedor se considerará concluyentemente haber autorizado CEAMSO a deducir cualquier importe(s) de pago(s) por otra parte debido(s).";
    public static final String PRECIO_MEJOR_PRECIO_NO_COLUSION_CERTIFICACION = "Los precios unitarios y precios extendidos según acuerdos de precio fijo son precios totales firmes y fijos, con todo incluido, que cubren la ejecución de todas las obligaciones del Proveedor en virtud de este acuerdo. El Proveedor certifica que ha proporcionado su Mejor Precio para CEAMSO y que ningún otro contratista/comprador ha recibido un precio inferior para servicios comparables o volumen de suministros similares. El Proveedor también certifica que no ha discutido o acordado con ningún partido para cobrar precios más altos a CEAMSO, y que toda la información de costos y precios proporcionada por esta orden es actual, precisa y completa. ";
    public static final String LIMITACION_DE_LA_RESPONSABILIDAD = "Ninguna de las partes será responsable ante la otra por cualquier tipo de daños consecuenciales, aún si les han sido aconsejados de la probabilidad de su ocurrencia. El Proveedor será responsable por cualquiera y todas las reclamaciones de terceros por lesiones, pérdidas o daños que surjan contra el Proveedor y/o CEAMSO como resultado del trabajo, errores, actos, omisiones, o mala conducta del Proveedor aquí establecidos.  ";
    public static final String RETRASOS_EXCUSABLES = "El Contratado será responsable por defecto a menos que el incumplimiento sea causado por una ocurrencia más allá del control razonable del Contratado y sin su falta o negligencia tal como fuerzas mayores o el enemigo público, los actos del Gobierno en su capacidad soberana o contractual, fuego, inundaciones, epidemias, restricciones de la cuarentena, huelgas, clima inusualmente severo, y retardos comunes de transportes. El Contratado  notificará a CEAMSO  por escrito tan pronto como sea razonablemente posible cuando comience un retardo perdonable, disponiendo los detalles completos en conexión con eso, remediando tal ocurrencia con todo el envío razonable y dando puntualmente el aviso escrito a CEAMSO .";
    public static final String LIMITACION_DE_DANHOS = "Si una demanda por danos o un derecho a cualquier otra forma de relevación, basada en el contrato, indemnización, negligencia u otro surge en conexión con esta Contrato, la Parte reclamante tomará todas las medidas necesarias para atenuar los daños o la pérdida, hasta el punto de ésta pueda ser superada sin costo irrazonable o inconveniencia. Nunca cualquier demanda o relevación incluirá o permitirá la recuperación de daños ejemplares o consecuentes, al menos en lo descrito.";
    public static final String LIQUIDACION_DE_DANHOS = "Si el contratado   incumple con la entrega de entregables/producto o la implementación de servicios dentro del periodo especificado en el Anexo I de esta contrato, el Contratado  deberá, en lugar de responder por daños reales, pagar a CEAMSO los daños liquidado por un monto total de $200 dólares americanos por cada día de retraso. \n"
            + "\n"
            + "NOTA:  Cualquier demora en el cumplimiento de cualquiera de los entregables indicados en el Anexo I del presente contrato que no haya sido comunicado por escrito a CEAMSO y que exceda de los 10 días, resultará en cancelación automática del presente contrato quedando CEAMSO  liberado de cualquier compromiso de pago.";
    public static final String LA_PROHIBICION_CONTRA_LA_FINANCIACION_DEL_TERRORISMO = "La Orden Ejecutiva #13224 y la ley de los EE.UU. prohíben al Proveedor de participar en transacciones con, y la provisión de recursos y apoyo, a personas y organizaciones asociadas con el terrorismo. Es la responsabilidad del Proveedor de asegurar el cumplimiento de estas Órdenes y Leyes Ejecutivas así como informar a CEAMSO cualquier asociación terrorista sospechada. Esta disposición se requiere de todos proveedores de nivel inferior.";
    public static final String DERECHOS_PROPIEDAD_DATOS = "CEAMSO y USAID tendrán uso ilimitado, irrevocable, y no exclusivo a todos los datos, publicaciones, guiones, gráficos, videos y software producidos y/o entregados con la financiación de esta orden.";
    public static final String DISPUTAS = "Los conflictos y las disputas derivadas resultantes de la ejecución de esta orden serán decididos por el Director del Programa de CEAMSO, según los principios de la imparcialidad y ajuste equitativo. Los conflictos no resueltos que involucran más de 3.000 dólares EE.UU. estarán sujetos a las Reglas de Arbitración UNCITRAL para la resolución en una ubicación a la discreción de MSI. Esta orden no está sujeta a los Tribunales Laborales o Fiscales.";
    public static final String CAMBIOS = "CEAMSO puede cambiar la cantidad y contenido de los suministros y servicios cuando dirigido por el Cliente de CEAMSO, mediante notificación por escrito, sujeto al ajuste equitativo de acuerdo con las condiciones actuales del mercado, la razonabilidad de precios, imparcialidad, y si el Gobierno de los EE.UU. es el Cliente, el Reglamento Federal de Adquisición (FAR).";
    public static final String TERMINACION_CANCELACION = "Esta orden puede ser terminado inmediatamente por CEAMSO por defecto o por cualquier razón por el Director del Programa CEAMSO o su designado, con cinco (5) días de anticipación, mediante un aviso por escrito. En tales casos, se hará un ajuste equitativo al precio del subcontrato para todo el trabajo completado hasta la fecha. CEAMSO también puede, mediante la provisión de notificación por escrito al Proveedor, cancelar sin cargo (a) al Acuerdo en su totalidad o (b) un artículo individual la cantidad de suministros o servicios incidentales, antes de la entrega.";
    public static final String AVISOS = "A. Los avisos se harán por escrito, firmado por el Director del Programa de CEAMSO o su designado, y enviado por correo electrónico con acuse de recibo o por servicio expreso de mensajería reconocido (con recibo de confirmación). En todos los casos, los avisos se dirigirán a las personas de CEAMSO mencionados en la Portada del Premio, y deben indicar claramente el Número del Acuerdo y el Número de Orden de Compra, tal como se especifica en la Portada del Premio.\n"
            + "B. Avisos transmitidos oralmente se pueden proporcionar con antelación, siempre que un aviso por escrito está transmitido puntualmente de acuerdo con el párrafo A encima. Los avisos serán efectivos cuando se reciben, o en la fecha de vigencia de un aviso recibido, el que sea más tarde. \n"
            + "C. A los efectos del Artículo 1, Avisos también pueden ser enviados a CEAMSO a la siguiente dirección: \n"
            + "\n"
            + "Ofelia Yegros, Directora Ejecutiva\n"
            + "	Centro de Estudios Ambientales Y Sociales\n"
            + "	Cecilio Avila No. 3838, \n"
            + "Asunción, Paraguay \n"
            + "	Tel: 604 332 / 662585\n"
            + "	E-mail:  oyegros@ceamso.org.py";
    public static final String MARCACION = "El Proveedor deberá cumplir con los requisitos de cualquier política encomendada por el Cliente de Marcación, como el “Manual de Normas Gráficas” de USAID (disponible en www.usaid.gov/branding) o cualquier plan de implementación marcación para Contratos o Premios de Asistencia fundados por USAID. El Proveedor confirmará cualquier requisito de marcación con CEAMSO. El Proveedor debe incluir este requisito en todos los subcontratos y sub-premios de nivel inferior. ";
    public static final String LENGUAJE_GOBERNANTE = "Este Acuerdo fue escrito y destinado a entenderse en el Idioma Español. En el caso de que se proporcione toda o parte de la presente Orden al Proveedor en un idioma distinto del español y hay una ambigüedad, malentendido o conflicto entre las versiones, la versión en español tendrá prioridad. ";
    public static final String ACTIVIDADES_VOLUNTARIAS = "A. Requisitos para el Programa de Esterilización Voluntaria. Ninguno de los fondos disponibles en virtud de este contrato serán utilizados para pagar por el rendimiento de la esterilización involuntaria como un método de planificación familiar o para coaccionar o proporcionar cualquier incentivo financiero a cualquier persona para practicar la esterilización. \n"
            + "B. La Prohibición de Actividades Relacionadas al Aborto\n"
            + "I. Ningunos fondos disponibles según este contrato serán utilizados para financiar, apoyar, o ser atribuidos a las siguientes actividades: (i) la adquisición o distribución de equipos destinados a ser utilizados con el propósito de inducir abortos como un método de planificación familiar; (ii) honorarios especiales o incentivos a cualquier persona para coaccionar o motivarlos a tener abortos; (iii) pagos a personas para realizar abortos o para solicitar personas a tener abortos; (iv) programas de información, educación, formación, o comunicación que tratan de promover el aborto como un método de la planificación familiar; y (v) el cabildeo a favor o en contra del aborto. El término “motivar”, en lo que se refiere a la asistencia de la planificación familiar, no se debe interpretar para prohibir la provisión, de conformidad con la ley local, de información o asesoramiento sobre todas las opciones del embarazo. \n"
            + "2. Ningunos fondos disponibles según este contrato serán utilizados para financiar cualquier investigación biomédica que se relacione, en su totalidad o en parte, a métodos de, o la realización de, abortos o esterilizaciones involuntarias como medio de planificación familiar. No se excluye la investigación epidemiológica o descriptiva para evaluar la incidencia, magnitud, o consecuencias de los abortos.\n"
            + "A. El contratista deberá insertar esta disposición en todos los subcontratos. ";
    public static final String LA_INICIATIVA_DISCAPACIDAD_USAID = "De acuerdo con la política de USAID, CEAMSO requiere que el Proveedor no discrimine contra personas con discapacidad en la implementación de programas de USAID y que haga todo lo posible para cumplir con los objetivos de la Iniciativa de la Discapacidad de USAID en la ejecución de este contrato. Se puede encontrar el documento de política de USAID aquí: www.usaid.gov/about_usaid/disability/. ";
    public static final String LA_LUCHA_CONTRA_TRAFICO_PERSONAS = "El Proveedor deberá cumplir con la cláusula del Reglamento Federal de Adquisición (FAR) 52.222-50, titulado, “Combating Trafficking in Persons” está incorporado aquí por referencia y está disponible aquí: https://www.acquisition.gov/far/current/html/52_222.html#wp1151848.";
    public static final String CERTIFICACIONES_TERMINOS_ADICIONALE_CONDICIONES = "El Proveedor se compromete las certificaciones adicionales y las Disposiciones Especiales (si existen) establecidas en el Anexo C, Disposiciones Especiales. ";
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Plantilla disposiciones Generales">
    public static final String DISPOSICIONES_GENERALES_CONT = "Este Contrato está financiado con fondos adjudicados al Centro de Estudios Ambientales y Sociales (CEAMSO) por la Agencia de Desarrollo Internacional de los Estados Unidos (USAID), y se origina en el marco del Acuerdo de Cooperación Nº AID-526-A-13-00003 firmado en fecha 30 de septiembre de 2013, cuyas cláusulas y condiciones son también aplicables al presente contrato en las partes que así se encuentran establecidas en el precitado acuerdo de cooperación.\n"
            + "Asimismo, El Proveedor está de acuerdo y acepta cumplir las siguientes condiciones:\n"
            + "a.- que no tomará parte en ninguna actividad que entre en conflicto con la entrega de los productos y servicios requeridos; \n"
            + "b.- que asumirá toda y cualquier responsabilidad para el mejor interés de CEAMSO y de USAID;\n"
            + "c.- que pasará a CEAMSO cualquier descuento comercial obtenido en la entrega de los artículos y servicios requeridos;\n"
            + "d.- que ha proporcionado todos los datos de costos/precios que son actuales, completos y precisos (a partir de la fecha de la presentación de su oferta) para que CEAMSO pueda determinar la razonabilidad de su propuesta económica; \n"
            + "e.- que cumplirá con todas las leyes y reglamentos nacionales y locales aplicables; \n"
            + "f.- que no está asociado a ninguna oficina o agencia del gobierno (excepto como se indica en la Portada del Contrato); y, \n"
            + "g.- que no ha ofrecido, proporcionado o prometido un regalo, pago, o cualquier cosa de valor a ningún funcionario, empleado o representante de CEAMSO o de cualquier entidad gubernamental (tanto a nivel local o departamental, como nacional en la República del Paraguay), en relación con este Contrato.";

    public static final String LA_CONDUCTA_PROFESIONAL_CONT = "manera ética y lícita en la ejecución de este Contrato, y acepta que se abstendrá de participar en cualquier actividad que pueda razonablemente interpretarse como ilegal, inmoral o que pudiere perjudicar el buen nombre y la reputación de CEAMSO o de USAID. El Proveedor deberá defender y liberar de responsabilidad a  CEAMSO contra cualquier reclamación, pérdida, daño o lesión que surja directa o indirectamente de los errores, actos, omisiones o mala conducta de El Proveedor.\n"
            + "El Proveedor acepta conocer y cumplir el Código de Ética de CEAMSO, cuyo contenido se encuentra publicado en el sitio: http://www.ceamso.org.py/upload/publicaciones/1484048057.pdf, y cuya copia el Proveedor ha recibido de CEAMSO antes de la firma de este contrato.";
    public static final String LA_RELACION_INDEPENDENTE_CONT = "Este Contrato únicamente establece  una relación contractual independiente entre CEAMSO y El Proveedor, que sólo son contratista/contratante, proveedor/. Por lo tanto, El Proveedor es el único responsable del pago de todos los tributos nacionales o municipales, internos o de aduanas, y demás contribuciones sociales,  transportes, seguros, y, en fin, de todo y cualquier otro costo en que incurriere para cumplir sus obligaciones derivadas del presente contrato.";
    public static final String INSPECCION_ACEPTACION_RECIBOS_CONT = "Todos los bienes suministrados o los servicios prestados por El Proveedor en virtud de este contrato están sujetos a la aceptación por parte de CEAMSO. Los bienes suministrados o los servicios prestados que no fueren razonablemente satisfactorios para CEAMSO, o que no cumplieren con las especificaciones técnicas o los términos de referencia establecidos en este contrato, serán rechazados por CEAMSO, o deberán ser corregidos inmediatamente por El Proveedor si CEAMSO, a su exclusiva discreción, así lo indicare a El Proveedor, en cuyo caso deberá dársele a éste las instrucciones y señalarse las condiciones que deberán ser cumplidas para la aceptación satisfactoria del servicio o suministro rechazado.";
    public static final String LA_GARANTIA_PROFESIONAL_CONT = "El Proveedor garantiza que la calidad de los productos o entregables requeridos cumplen las especificaciones técnicas y/o los términos de referencia establecidos en este Contrato.";
    public static final String ASIGNACION_NOVACION_CONT = "El Proveedor no transferirá ni cederá sus acciones, derechos u obligaciones derivados de este contrato a persona alguna sin el consentimiento previo y por escrito  de CEAMSO. Ninguna cesión, novación, o cualquier otra delegación de responsabilidad, con o sin el consentimiento de CEAMSO, debe exonerar a El  Proveedor de cualquiera de sus obligaciones asumidas según este Contrato, o perjudicar cualquiera de los derechos de CEAMSO contra el  Proveedor, ya sea que surjan antes o después la fecha de cualquier asignación.";
    public static final String SUBCONTRATACION_CONSULTORES_CONT = "A. Se requiere la autorización previa por escrito de CEAMSO para que El Proveedor sub-contrate servicios de terceros consultores y proveedores. Los pagos a terceros consultores y proveedores cuya sub-contratación no ha recibido la aprobación previa y por escrito de CEAMSO conforme a lo dispuesto en esta cláusula, no serán permitidos. La inclusión de terceros consultores o proveedores en el presupuesto o en la propuesta de El Proveedor no implica la aprobación expresa de una sub-contratación, la cual en todos los casos deberá ser previamente autorizada por escrito por parte de CEAMSO.\n"
            + "B. Al solicitar la sub-contratación de terceros consultores, El Proveedor deberá proporcionar a CEAMSO la siguiente documentación: Currículum Vitae y, a menos que se le indique lo contrario, también deberá presentar el Formulario SF1420-17 de Bio-Data debidamente firmados, y suministrará también información clara acerca de las actividades y nivel de esfuerzo del consultor tercero que se pretende sub-contratar. \n"
            + "C. El consentimiento del tercero consultor para firmar un sub-contrato no constituye una determinación de la aceptabilidad de los términos o del precio del sub-contrato, o de la admisibilidad de los gastos para dicha pretendida sub-contratación. Si fuere solicitado por CEAMSO, El Proveedor proporcionará una copia del sub-contrato propuesto para que CEAMSO pueda tomar una decisión de aceptabilidad o no de la sub-contratación del consultor tercero.";
    public static final String ENTREGA_VARIACION_EN_CANTIDAD_CONT = "A. El Proveedor será responsable de la ejecución de los servicios y la entrega de los suministros en el (los) sitio(s) especificado(s) en la Portada del Contrato y en detalle, en los TdRs, de este Contrato. Todos los riesgos y costos incurridos antes de la entrega y la aceptación final de CEAMSO serán por cuenta exclusiva del Proveedor. Si bajo este Contrato se requieren servicios de traducción, impresión, u otros servicios o suministros de cantidad de gran volumen, CEAMSO podrá autorizar, por escrito, una variación de +/- 10% del precio de este contrato, si está justificado.\n"
            + "B. Si los servicios no se llevan a cabo y/o no se entregan los suministros de manera oportuna, y en todos los aspectos, de conformidad con lo previsto en este Contrato, CEAMSO podrá requerir que El Proveedor le reembolse por cualquier pérdida o gasto incurrido por CEAMSO como resultado de la situación prevista en este párrafo. Asimismo y como consecuencia de la estipulación que inmediatamente antecede, El Proveedor autoriza a CEAMSO a deducir de los pagos que aún le fueren adeudados cualquier pérdida o gasto incurrido por CEAMSO por la razón expuesta más arriba en este mismo párrafo.";
    public static final String PRECIO_MEJOR_PRECIO_NO_COLUSION_CERTIFICACION_CONT = "Los precios unitarios y precios extendidos según acuerdos de precio fijo son precios totales firmes y fijos, con todo incluido, que cubren la ejecución de todas las obligaciones del Proveedor en virtud de este acuerdo. El Proveedor certifica que ha proporcionado su Mejor Precio para CEAMSO. El Proveedor también certifica que no ha discutido o acordado con nadie para cobrar precios más altos a CEAMSO, y que toda la información de costos y precios proporcionada por esta orden es actual, precisa y completa.";
    public static final String LIMITACION_DE_LA_RESPONSABILIDAD_CONT = "El Proveedor será el único responsable por cualquiera y todas las reclamaciones de terceros por lesiones, pérdidas o daños que surjan como resultado del trabajo, errores, actos, omisiones, o negligencia de su parte. En consecuencia, El Proveedor garantiza a CEAMSO que ninguna reclamación de terceras personas ajenas a este Contrato deberá ser atendida ni satisfecha por ella.";
    public static final String RETRASOS_EXCUSABLES_CONT = "El incumplimiento por parte de El Proveedor de cualquiera de sus obligaciones en virtud del Contrato no se considerará como violación del mismo ni como negligencia, cuando este incumplimiento se deba a un evento de fuerza mayor, y El Proveedor (a) haya adoptado todas las precauciones posibles, puesto debido cuidado y tomado medidas alternativas razonables a fin de cumplir con los términos y condiciones de este Contrato, y (b) haya informado a CEAMSO prontamente del  acontecimiento del dicho evento. Para los efectos de este Contrato, “fuerza mayor” significa un acontecimiento que está fuera del control de una de las Partes, y que hace que el cumplimiento de las obligaciones contractuales de esa Parte resulte imposible o tan poco viable que puede considerarse  imposible bajo tales circunstancias. ";
    public static final String LIMITACION_DE_DANHOS_CONT = "Si una demanda por danos o un derecho a cualquier otra forma de relevación, basada en el contrato, indemnización, negligencia u otro surge en conexión con esta Contrato, la Parte reclamante tomará todas las medidas necesarias para atenuar los daños o la pérdida, hasta el punto de ésta pueda ser superada sin costo irrazonable o inconveniencia. Nunca cualquier demanda o relevación incluirá o permitirá la recuperación de daños ejemplares o consecuentes, al menos en lo descrito.";
    public static final String LIQUIDACION_DE_DANHOS_CONT = "Si el contratado   incumple con la entrega de entregables/producto o la implementación de servicios dentro del periodo especificado en el Anexo I de esta contrato, el Contratado  deberá, en lugar de responder por daños reales, pagar a CEAMSO los daños liquidado por un monto total de $200 dólares americanos por cada día de retraso. \n"
            + "\n"
            + "NOTA:  Cualquier demora en el cumplimiento de cualquiera de los entregables indicados en el Anexo I del presente contrato que no haya sido comunicado por escrito a CEAMSO y que exceda de los 10 días, resultará en cancelación automática del presente contrato quedando CEAMSO  liberado de cualquier compromiso de pago.";
    public static final String LA_PROHIBICION_CONTRA_LA_FINANCIACION_DEL_TERRORISMO_CONT = "La Orden Ejecutiva Nº 13224 y la legislación de los EE.UU. aplicable a USAID y a las ayudas financieras otorgadas por la misma, prohíben a El Proveedor participar en transacciones así como la provisión de recursos y apoyo, a personas y organizaciones asociadas con el terrorismo. Es la responsabilidad de El Proveedor asegurar el cumplimiento irrestricto de estas prohibiciones legales y contractuales así como informar a CEAMSO sobre cualquier asociación terrorista sospechada. Esta misma disposición contractual deberá ser inserta en todo y cualquier otro sub-contrato que El Proveedor pudiere celebrar en el marco de la ejecución de este mismo contrato.";
    public static final String DERECHOS_PROPIEDAD_DATOS_CONT = "CEAMSO y USAID tendrán uso ilimitado, irrevocable, y no exclusivo a todos los datos, publicaciones, guiones, gráficos, videos y software producidos y/o entregados por El Proveedor en el marco del presente contrato.  ";
    public static final String DISPUTAS_CONT = "Las partes acuerdan someter cualquier controversia que surja de la ejecución de este contrato o tenga relación con el mismo, con su interpretación, validez o invalidez, a un proceso de Mediación ante el Centro de Arbitraje y Mediación Paraguay de la Cámara Nacional de Comercio y Servicios de Paraguay, de acuerdo con las normas de procedimiento para mediación que posee dicha institución. Para el caso que las partes no resuelvan la controversia en el procedimiento de mediación, se obligan a someter su diferencia a arbitraje, ante un tribunal arbitral conformado por tres árbitros designados de la lista del Cuerpo Arbitral del Centro de Arbitraje y Mediación Paraguay, que decidirá conforme a derecho, siendo el laudo definitivo vinculante para las partes. En ambos casos se aplicarán los reglamentos respectivos y demás disposiciones que regulen dichos procedimientos al momento de recurrir a los mismos, declarando las partes conocer y aceptar los vigentes, incluso en orden a su régimen de gastos y costas, considerándolos parte integrante del presente contrato.";
    public static final String CAMBIOS_CONT = "CEAMSO puede modificar la cantidad y contenido de los suministros y servicios mediante notificación por escrito a EL Proveedor, debiendo en tal circunstancia negociar y acordar con El Proveedor los ajustes equitativos que fueren pertinentes de acuerdo con las condiciones actuales del mercado, la razonabilidad de precios, y el nuevo alcance de los servicios requeridos. En ningún caso, la modificación del contrato puede implicar un aumento superior al 10% del monto originalmente contratado.";
    public static final String TERMINACION_CANCELACION_CONT = "Este contrato puede ser terminado antes del cumplimiento del plazo originariamente pactado por CEAMSO por defecto o por cualquier razón, mediante aviso por escrito al Proveedor con cuanto menos quince días de anticipación. En tales casos se hará un ajuste equitativo al precio del contrato para todo el servicio completado a satisfacción de CEAMSO hasta la fecha. En caso que la duración original del contrato fuere mayor a seis meses, el aviso de terminación o cancelación deberá ser realizado con una antelación no menor a treinta días.\n"
            + "El Proveedor también podrá terminar el contrato, debiendo dar a CEAMSO el mismo preaviso y dentro de los mismos plazos indicados en el párrafo que antecede.";
    public static final String AVISOS_CONT = "A. Los avisos y comunicaciones entre las Partes se harán por escrito y serán enviados por correo electrónico con acuse de recibo o por servicio expreso de mensajería reconocido (con recibo de confirmación). En todos los casos, los avisos se dirigirán a las personas mencionados en la Portada del Contrato, y deben indicar claramente el Número del Acuerdo y el Número del Contrato indicado en dicha portada.\n"
            + "B. Los avisos o comunicaciones también pueden ser realizados anticipadamente en forma oral, pero en todos los casos deberán efectuarse los avisos por escrito conforme a lo establecido en el párrafo A que antecede. Los avisos serán efectivos desde que fueren recibidos, o desde la fecha indicada en la comunicación.";
    public static final String MARCACION_CONT = "El Proveedor deberá cumplir con los requisitos de cualquier política encomendada por CEAMSO sobre el Plan de Marcas contenido en el “Manual de Normas Gráficas” de USAID (disponible en www.usaid.gov/branding), o con cualquier otro plan de implementación de las marcas de USAID establecidos para Contratos o Asistencias financiados por USAID. En todos los casos el Proveedor confirmará con CEAMSO cualquier requisito sobre el uso de las marcas de USAID. El Proveedor debe incluir este requisito en todos los subcontratos que subscriba en el marco de la ejecución del presente contrato. ";
    public static final String LENGUAJE_GOBERNANTE_CONT = "Este Contrato fue escrito y destinado para entenderse en el idioma español. En el caso que se proporcione toda o parte del presente Contrato a El Proveedor en un idioma distinto del español y que hubiere alguna ambigüedad, malentendido o conflicto entre las versiones, será aplicable la versión en español. \n"
            + "No obstante lo establecido precedentemente, CEAMSO y El Proveedor aceptan la posibilidad de que puedan ser incorporados al contrato algunos prospectos, folletos o determinadas frases o términos estrictamente técnicos de aplicación usual redactados en idiomas diferentes al español, debiendo en tal caso acordarse entre las partes la interpretación o traducción que sea más adecuada para el propósito de esta contratación";
    public static final String ACTIVIDADES_VOLUNTARIAS_CONT = "A. Ninguno de los fondos disponibles en virtud de este contrato serán utilizados para pagar por la esterilización como un método de planificación familiar o para coaccionar o proporcionar cualquier incentivo financiero a cualquier persona para practicar la esterilización. \n"
            + "B. La Prohibición de Actividades Relacionadas al Aborto\n"
            + "I. Ningunos fondos disponibles según este contrato serán utilizados para financiar, apoyar, o ser atribuidos a las siguientes actividades: (i) la adquisición o distribución de equipos destinados a ser utilizados con el propósito de inducir abortos como un método de planificación familiar; (ii) honorarios especiales o incentivos a cualquier persona para coaccionar o motivarlos a tener abortos; (iii) pagos a personas para realizar abortos o para solicitar personas a tener abortos; (iv) programas de información, educación, formación, o comunicación que tratan de promover el aborto como un método de la planificación familiar; y (v) el cabildeo a favor o en contra del aborto. El término “motivar”, en lo que se refiere a la asistencia de la planificación familiar, no se debe interpretar para prohibir la provisión, de conformidad con la ley local, de información o asesoramiento sobre todas las opciones del embarazo. \n"
            + "2. Ningunos fondos disponibles según este contrato serán utilizados para financiar cualquier investigación biomédica que se relacione, en su totalidad o en parte, a métodos de, o la realización de, abortos o esterilizaciones involuntarias como medio de planificación familiar. No se excluye la investigación epidemiológica o descriptiva para evaluar la incidencia, magnitud, o consecuencias de los abortos.\n"
            + "C. El Proveedor deberá insertar esta disposición en todos los subcontratos que pudiere otorgar a otros terceros sub-contratistas en el marco de la ejecución del presente contrato. ";
    public static final String LA_INICIATIVA_DISCAPACIDAD_USAID_CONT = "De acuerdo con la política de USAID, CEAMSO requiere que El Proveedor no discrimine a personas con discapacidad en la implementación de programas de USAID y que haga todo lo posible para cumplir con los objetivos de la Iniciativa de la Discapacidad de USAID en la ejecución de este contrato. Se puede encontrar el documento de política de USAID aquí: www.usaid.gov/about_usaid/disability/. ";
    public static final String LA_LUCHA_CONTRA_TRAFICO_PERSONAS_CONT = "El Proveedor deberá cumplir con la cláusula del Reglamento Federal de Adquisición (FAR) 52.222-50, titulado, “Combating Trafficking in Persons” está incorporado aquí por referencia y está disponible aquí: https://www.acquisition.gov/far/current/html/52_222.html#wp1151848.";
    public static final String CERTIFICACIONES_TERMINOS_ADICIONALE_CONDICIONES_CONT = "El Proveedor se compromete a cumplir las certificaciones adicionales y las Disposiciones Especiales (si existen) establecidas en los Anexos I y II que forman parte de este Contrato.     ";

    public static final String MULTA_APLICABLE_CONT = "Si El Proveedor incumpliere su obligación de entregar los productos o de ejecutar los servicios dentro del periodo especificado en el Anexo I de este contrato, el mismo  deberá, en substitución de una indemnización de daños y perjuicios, pagar a CEAMSO una multa contractual equivalente al 1% del valor total del contrato por cada día de retraso. Dicha multa no podrá sobrepasar el 10% del valor total del contrato y será efectivizada mediante el descuento de su importe de las sumas que CEAMSO debiere abonar a El Proveedor.  \n"
            + "\n"
            + "Toda demora en el cumplimiento de cualquiera de los productos indicados en el Anexo I del presente contrato que no haya sido comunicado por escrito a CEAMSO y que exceda de los 10 días, resultará en cancelación automática del presente contrato. En este caso, CEAMSO realizará una evaluación de las tareas realizadas y de los productos entregados por El Proveedor hasta la cancelación del contrato a los efectos de determinar la eficacia o provecho o utilidad que dichas tareas parcialmente realizadas o productos entregados podrían tener para contribuir al éxito esperado en la ejecución del Programa de Democracia y Gobernabilidad a cargo de CEAMSO. Si las tareas parcialmente realizadas o productos entregados por El Proveedor fueren de utilidad, CEAMSO pagará a aquél una suma de dinero equitativamente determinada por ella en función a dicha utilidad de los productos entregados. ";
    public static final String NORMAS_LEGALES_SUPLETORIAS_CONT = "CEAMSO y EL Proveedor acuerdan que, en toda cuestión contractual que no se encuentre expresamente prevista en este Contrato, podrán ser supletoriamente aplicables las normas legales del Código Civil paraguayo, siempre que con dicha aplicación supletoria no fueren modificadas las cláusulas expresamente pactadas en este mismo acuerdo, cuyas estipulaciones prevalecerán en todos los casos frente a las normas de la Ley Nº 1.183/1985 del Código Civil paraguayo.";
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Plantillas RDC">
    public static final String COD_PLANTILLA_PERSONA_JURIDICA_CONSORCIO = "PJOC";
    public static final String COD_PLANTILLA_PERSONA_JURIDICA = "PJ";
    public static final String COD_PLANTILLA_PERSONA_FISICA = "PF";

    //<editor-fold defaultstate="collapsed" desc="Templates doc de RDC">
    public static final String DOC_TEMPLATE_PERSONA_JURIDICA_CONSORCIO = "rdc_pjoc.docx";
    public static final String DOC_TEMPLATE_PERSONA_JURIDICA = "rdc_pj.docx";
    public static final String DOC_TEMPLATE_PERSONA_FISICA = "rdc_pf.docx";

    public static final String DOC_TEMPLATE_PERSONA_JURIDICA_CONSORCIODOCM = "rdc_pjoc.docm";
    public static final String DOC_TEMPLATE_PERSONA_JURIDICADOCM = "rdc_pj.docm";
    public static final String DOC_TEMPLATE_PERSONA_FISICADOCM = "rdc_pf.docm";

    public static final String DOC_ADENDA = "docAdenda.docx";
    public static final String MODIF_CONTRATO_MEMO = "memo_modif_contrato.docx";
    public static final String MODIF_CONTRATO = "modificacion_contrato.docx";
//</editor-fold>

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Contratos">
    public static final String TERMINOS_PAGO = "Remitirse a las estipulaciones pactadas en los términos de pago.\n"
            + "este contrato se encuentra exento de IVA s/ lo dispuesto en la ley Nº 110/92";

    public static final String CODIGO_ROL_DIRECTORA_EJECUTIVA = "DIRG";
    public static final String CODIGO_ROL_DIRECTORA_PROGRAMA = "DIRP";
    public static final String CODIGO_ROL_DIRECTOR_ADMINISTRATIVO = "ADM";

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="comment">
    public static final String COD_SUCURSAL_PDG = "01";
    public static final String COD_SUCURSAL_INSTITUCIONAL = "02";
    public static final String COD_SUCURSAL_CONACYT = "03";
    public static final String COD_SUCURSAL_DELOITE_PRESUPUESTO_CIUDADANO = "04";
    public static final String COD_SUCURSAL_DELOITE_FONACIDE = "05";
    public static final String COD_SUCURSAL_ITAIPU = "06";

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="CONSTANTES">
    public static final String CONSTANTE_ANULADO = "A";
//</editor-fold>
    public static final String CODIGO_ROL_INVITADO = "invitado";

    public static final String NOMBRRE_CARPETA_ATV = "atv";
//<editor-fold defaultstate="collapsed" desc="Email">
    public static final String PLANTILLA_EMAIL_CONTRATACION_DIRECTA = "<p>&nbsp;</p>\n"
            + "\n"
            + "<p style=\"text-align:right\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">$FECHA</span></span></p>\n"
            + "\n"
            + "<p>&nbsp;</p>\n"
            + "\n"
            + "<table cellspacing=\"0\" class=\"Table\" style=\"border-collapse:collapse; height:202px; width:635px\">\n"
            + "	<tbody>\n"
            + "		<tr>\n"
            + "			<td style=\"vertical-align:top; width:241.5pt\">\n"
            + "			<p><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">$NOMBRE.</span></span></p>\n"
            + "\n"
            + "			<p><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Consultor/a Oferente</span></span></p>\n"
            + "\n"
            + "			<p><u><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Ciudad.</span></span></u></p>\n"
            + "			</td>\n"
            + "			<td style=\"vertical-align:top; width:239.75pt\">\n"
            + "			<p style=\"text-align:right\">&nbsp;</p>\n"
            + "\n"
            + "			<p style=\"text-align:right\">&nbsp;</p>\n"
            + "\n"
            + "			<p style=\"text-align:right\">&nbsp;</p>\n"
            + "\n"
            + "			<p style=\"text-align:right\"><u><span style=\"font-size:10.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Referencia: </span></span></u></p>\n"
            + "\n"
            + "			<p style=\"text-align:right\"><span style=\"font-size:10.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Invitaci&oacute;n a presentar Propuesta Econ&oacute;mica</span></span></p>\n"
            + "\n"
            + "			<p>&nbsp;</p>\n"
            + "			</td>\n"
            + "		</tr>\n"
            + "	</tbody>\n"
            + "</table>\n"
            + "\n"
            + "<p style=\"text-align:justify\">&nbsp;</p>\n"
            + "\n"
            + "<p style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">El Centro de Estudios Ambientales y Sociales &ndash;CEAMSO- le invita </span></span><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">a <u>presentar su propuesta econ&oacute;mica</u> para la </span></span><strong><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">&ldquo;$TITULO&rdquo;</span></span></strong><strong><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">,</span></span></strong><strong> </strong><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">bajo el $PROGRAMA, </span></span><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">en base a los T&eacute;rminos de Referencia &ndash; TdRs.</span></span></p>\n"
            + "\n"
            + "<p style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Para su mejor cotizaci&oacute;n, es importante tener en cuenta que e</span></span><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">n el marco del PDG, </span></span><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">CEAMSO, como implementador del programa, </span></span><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">se encuentra exento de IVA, seg&uacute;n Ley N&deg; 110/92.</span></span></p>\n"
            + "\n"
            + "<p style=\"text-align:justify\">&nbsp;</p>\n"
            + "\n"
            + "<div style=\"border:solid windowtext 1.0pt; padding:1.0pt 4.0pt 1.0pt 4.0pt\">\n"
            + "<p style=\"text-align:center\"><span style=\"background-color:white\"><span style=\"background-color:white\"><u><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Plazo m&aacute;ximo de entrega</span></span></u><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">: <span style=\"color:red\">hasta las $PLAZO</span></span></span></span></span></p>\n"
            + "\n"
            + "<p style=\"text-align:center\"><span style=\"background-color:white\"><span style=\"background-color:white\"><strong><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">En formato digital, a los correos electr&oacute;nicos: </span></span></strong></span></span></p>\n"
            + "\n"
            + "<p style=\"text-align:center\"><span style=\"background-color:white\"><span style=\"background-color:white\"><a href=\"mailto:llamadoaconcurso@ceamso.org.py\"><strong><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">llamadoaconcurso@ceamso.org.py</span></span></strong></a><strong><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\"> / llamadosceamso@gmail.com</span></span></strong></span></span></p>\n"
            + "</div>\n"
            + "\n"
            + "<p style=\"text-align:justify\">&nbsp;</p>\n"
            + "\n"
            + "<p style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Adjuntar a la Propuesta Econ&oacute;mica los siguientes documentos legales administrativos:</span></span></p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Copia simple de CI, escaneado.</span></span></li>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Certificado de cumplimiento tributario actualizado. </span></span></li>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Certificado de No Ser Funcionario P&uacute;blico. (Se gestiona y obtiene en forma inmediata en el siguiente link: </span></span><a href=\"https://www.documentos.gov.py/simple/etapas/ejecutar/151625\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">https://www.documentos.gov.py</span></span></a> <span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\"> .</span></span></li>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">DECLARACI&Oacute;N JURADA de comportamiento &eacute;tico.</span></span></li>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">DECLARACI&Oacute;N JURADA por la cual el Oferente garantiza: el Cumplimiento de derechos de menores, si los tuviera, <strong> </strong>la libre disposici&oacute;n de sus bienes y su administraci&oacute;n.</span></span></li>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">CV actualizado con documentos respaldatorios.</span></span></li>\n"
            + "</ol>\n"
            + "\n"
            + "<p style=\"text-align:justify\"><u><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Para facilitar su presentaci&oacute;n, en anexo encontrar&aacute;:</span></span></u></p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">L</span></span><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">os T&eacute;rminos de Referencia-TdRs y el perfil t&eacute;cnico requerido.</span></span></li>\n"
            + "	<li style=\"text-align:justify\"><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Formatos de Nota de presentaci&oacute;n de documentos legales administrativos, de la propuesta econ&oacute;mica, de las DDJJ solicitadas y de CV.</span></span></li>\n"
            + "</ol>\n"
            + "\n"
            + "<p><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Ante cualquier consulta, no dude en contactar a las direcciones de correo indicadas m&aacute;s arriba. </span></span></p>\n"
            + "\n"
            + "<p><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">Muchas gracias</span></span><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\">.</span></p>\n"
            + "\n"
            + "<p style=\"text-align:center\"><strong><span style=\"font-size:11.0pt\"><span style=\"font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;\"><span style=\"color:red\">Favor acusar recibo de este mail.</span></span></span></strong></p>\n"
            + "\n"
            + "<p><br />\n"
            + "&nbsp;</p>";
    public static final String PLANTILLA_EMAIL_OFERENTE_GANADOR = "<p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">Estimados Señores,<u></u><u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><b><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">$NOMBRE,</span></b><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\"><u></u><u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\"><u></u>&nbsp;<u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">Adjunto les remitimos Nota de comunicación de adjudicación del llamado RDC N° $NRO para realizar la&nbsp;<b>“$TITULO”<u></u><u></u></b></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><b><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\"><u></u>&nbsp;<u></u></span></b></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">Favor dar acuse de recibo del presente correo.<u></u><u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\"><u></u>&nbsp;<u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">Cualquier consulta a las órdenes.&nbsp;<u></u><u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">&nbsp;<u></u><u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">Cordiales saludos.<u></u><u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\"><u></u>&nbsp;<u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">CEAMSO<u></u><u></u></span></p><p class=\"MsoNormal\" style=\"margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><font face=\"Calibri, sans-serif\"><span style=\"font-size: 14.6667px;\">$PROGRAMA</span></font></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">Área de Contratos.<u></u><u></u></span></p><p class=\"MsoNormal\" style=\"font-family: arial, sans-serif; font-size: 12.8px; margin: 0px; color: rgb(34, 34, 34); background-color: rgb(255, 255, 255);\"><span style=\"font-size: 11pt; font-family: Calibri, sans-serif;\">Asunción/Paraguay</span></p>";
    public static final String PLANTILLA_EMAIL_OFERENTE_PERDEDOR = "<p class=\"\\&quot;MsoNormal\\&quot;\" style=\"\\&quot;font-weight:\" normal;=\"\" margin:=\"\" 0px;=\"\" color:=\"\" rgb(34,=\"\" 34,=\"\" 34);=\"\" font-family:=\"\" arial,=\"\" sans-serif;=\"\" font-size:=\"\" 12.8px;=\"\" background-color:=\"\" rgb(255,=\"\" 255,=\"\" 255);\\\"=\"\">$TITULO.<u></u><u></u></p><p class=\"\\&quot;MsoNormal\\&quot;\" style=\"\\&quot;font-weight:\" normal;=\"\" margin:=\"\" 0px;=\"\" color:=\"\" rgb(34,=\"\" 34,=\"\" 34);=\"\" font-family:=\"\" arial,=\"\" sans-serif;=\"\" font-size:=\"\" 12.8px;=\"\" background-color:=\"\" rgb(255,=\"\" 255,=\"\" 255);\\\"=\"\"><u></u>&nbsp;<u></u></p><p class=\"\\&quot;MsoNormal\\&quot;\" style=\"\\&quot;font-weight:\" normal;=\"\" margin:=\"\" 0px;=\"\" color:=\"\" rgb(34,=\"\" 34,=\"\" 34);=\"\" font-family:=\"\" arial,=\"\" sans-serif;=\"\" font-size:=\"\" 12.8px;=\"\" background-color:=\"\" rgb(255,=\"\" 255,=\"\" 255);\\\"=\"\"><u></u>&nbsp;<u></u></p><p class=\"\\&quot;MsoNormal\\&quot;\" style=\"\\&quot;font-weight:\" normal;=\"\" margin:=\"\" 0px;=\"\" color:=\"\" rgb(34,=\"\" 34,=\"\" 34);=\"\" font-family:=\"\" arial,=\"\" sans-serif;=\"\" font-size:=\"\" 12.8px;=\"\" background-color:=\"\" rgb(255,=\"\" 255,=\"\" 255);\\\"=\"\"><span lang=\"\\&quot;ES-MX\\&quot;\">Adjunto enviamos la comunicación de resultado del RDC $NRO.&nbsp; Saludos.</span></p>";

    public static final String TIPO_EMAIL_NOTIFICACION_ADJUDICACION = "ADJ";
    public static final String TIPO_EMAIL_NOTIFICACION_NO_ADJUDICADO = "NOADJ";
    public static final String TIPO_EMAIL_NOTIFICACION_INVITACION = "INV";
    public static final String PLANTILLA_EMAIL_CD_ADJUDICADO = "<p>$NOMBRE.</p>"
            + "<p>Buenas tardes estimado se&ntilde;or.</p>"
            + "<p>Adjunto le env&iacute;o la comunicaci&oacute;n de adjudicaci&oacute;n de contrato para la <strong>CONSULTOR&Iacute;A DE &ldquo;$TITULO&ldquo;. </strong></p>"
            + "<p>En breve le enviar&eacute; el contrato para su consideraci&oacute;n y aprobaci&oacute;n.</p>"
            + "<p>Le esperamos para su firma en nuestras oficinas&nbsp; La consultor&iacute;a tendr&aacute; vigencia a partir de la fecha de hoy.&nbsp; La coordinaci&oacute;n &nbsp;de TICs , del PDG, instancia de apoyo al &aacute;rea de Administraci&oacute;n y Finanzas para la ejecuci&oacute;n de este contrato, contactar&aacute; con usted para afinar detalles de los inicios de la consultor&iacute;a.</p>"
            + "<p>&nbsp;</p>"
            + "<p>Cordiales saludos.</p>";
    public static final String PLANTILLA_ANEXO_MAIL_ADJUDICADO = "<p style=\"text-align:right\">$FECHA</p>"
            + "<p><strong>$EMPRESA</strong> $TIPO_EMP</p>"
            + "<p><strong>$OFERENTE</strong></p>"
            + "<p>Oferente</p>"
            + "<p><strong>Asunción-Paraguay</strong></p>"
            + "<p style=\"text-align:right\">Referencia:</p>"
            + "<p style=\"text-align:right\">Comunicación de Adjudicación de Contrato para la </p>"
            + "<p style=\"text-align:right\"><strong>$TITULO</strong></p>"
            + "<p>Estimado/a Sr/a:<p>"
            + "<p style=\"text-align: justify;\">Cumplimos en informarle que, luego de analizar todos sus documentos legales administrativos y su propuesta econ&oacute;mica, "
            + "recibidos en tiempo y forma, conforme a lo solicitado, fue adjudicado/a para la ejecuci&oacute;n de la <strong>CONSULTOR&Iacute;A</strong> de referencia, por un monto total de "
            + "$SIGLAMONEDA. <strong>$MONTO</strong>.- ($MONEDA: $MONTLETRAS .-), excenta del iva</p>"
            + "<p>En la brevedad le enviaremos el borrador del contrato, que será $TIPOPRECIO, para su consideración y   firma, para el inicio de los servicios contratados.</p>"
            + "<p> Cualquier consulta, quedamos a disposición.</p>"
            + "<p>Cordiales Saludos.</p>";
    public static final String PLANTILLA_ANEXO_MAIL_NO_ADJUDICADO = "<p style=\"text-align:right\">$FECHA</p>"
            + "<p><strong>$EMPRESA</strong> $TIPO_EMP</p>"
            + "<p><strong>$OFERENTE</strong></p>"
            + "<p>Oferente</p>"
            + "<p><strong>Asunción-Paraguay</strong></p>"
            + "<p style=\"text-align:right\">Referencia:</p>"
            + "<p style=\"text-align:right\">Comunicación de Resultado</p>"
            + "<p style=\"text-align:right\"><strong>CONSULTORIA DE $TITULO</strong></p>"
            + "<p>Estimado/a Sr/a:<p>"
            + "<p style=\"text-align: justify;\">Cumplimos en informarles que el proceso de evaluación de las propuestas recibidas para prestar servicios de consultoría de la referencia, bajo el Programa de "
            + "$NOMBRE_EMPRESA, en el que fuera Oferente, ha finalizado y en esta ocasión, no ha salido adjudicado. </p>"
            + "<p>Agradecemos su participación. Le invitamos a estar atentos a nuestros llamados en la web de CEAMSO, que puedan estar relacionados a sus áreas de acción y ser de su interés y, en las cuales será muy importante poder contar con sus propuestas.</p>"
            + "<p>Cordiales Saludos.</p>";
    public static final String PLANTILLA_EMAIL_CD_NO_ADJUDICADO = "<p>Estimado/a Se&ntilde;or/a:$NOMBRE</p>"
            + "<p>Adjunto le enviamos la comunicaci&oacute;n de resultados de las evaluaciones de propuestas recibidas para prestar servicios de consultor&iacute;a de $TITULO&quot;.</p>";
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="TAG's RDC">
    public static final String TAG_DIAS = "dias";
    public static final String TAG_AID = "aid";
    public static final String TAG_TARJETA_DIPLOMATICA = "trj";
    public static final String TAG_RDC = "rdc";
    public static final String TAG_TIPO_PERSONA = "tipoPersona";
    public static final String TAG_TIPO_CONTRATO = "tipoContrato";
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Email">
    public static final String PLANTILLA_TEC_MEMO_INTRODUCCION = "<p style=\"text-align: justify;\">Esta es una consultor&iacute;a con contrato por $TIPO_CONTRATO para el &ldquo;$TITULO_RDC&rdquo;, que tendr&aacute; una vigencia de $PLAZO_EJECUCION.</p>";
    public static final String PLANTILLA_TEC_MEMO_ANTECEDENTES = "<p style=\"text-align: justify;\">El Centro de Estudios Ambientales y Sociales (CEAMSO) es el implementador primario para el Programa de Democracia y Gobernabilidad (PDG) de la Agencia para el Desarrollo Internacional (USAID), Acuerdo Cooperativo No. AID-526-A-00003.</p>";
    public static final String PLANTILLA_TEC_MEMO_COMPETICION_PROCEDIMIENTO = "<p style=\"text-align:justify\">Se emiti&oacute; el RDC Nro. $RDC_NRO el d&iacute;a $FECHA_CREACION :</p>"
            + "<p style=\"text-align:justify\">Se realizaron invitaciones al concurso a trav&eacute;s de la p&aacute;gina web de Ceamso, publicaciones en los peri&oacute;dicos y en las redes sociales Facebook y Twitter de Ceamso y grupos de inform&aacute;ticos en Facebook.</p>"
            + "<p style=\"text-align:justify\">Link de la publicaci&oacute;n en la p&aacute;gina web de Ceamso: $LINK</p>"
            + "<p style=\"text-align:justify\"><u>Resumen del llamado:</u></p>"
            + "<ul>"
            + "	<li style=\"text-align: justify;\">Fecha l&iacute;mite de preguntas: Hasta el $CONSULTAS_HASTA</li>"
            + "	<li style=\"text-align: justify;\">Fecha de respuestas a las preguntas: Hasta el $RESPUESTAS_HASTA</li>"
            + "	<li style=\"text-align: justify;\">Fecha de presentaci&oacute;n de propuestas: $FECHA_LIMITE</li>"
            + "	<li style=\"text-align: justify;\">Monto m&aacute;ximo asignado: $PRES_MAXIMO</li>"
            + "</ul>"
            + "<p style=\"text-align: justify;\">&nbsp;</p>"
            + "<p style=\"text-align:justify\"><u>Se recibieron $CANT_PROPUESTAS propuestas en fecha:</u></p>"
            + "$PROPUESTAS"
            + "<p style=\"text-align: justify;\">&nbsp;</p>";
    public static final String PLANTILLA_TEC_MEMO_EVALUACION = "<p style=\"text-align:justify\">El comit&eacute; t&eacute;cnico de evaluaci&oacute;n (CTE) estuvo integrado por:</p>"
            + "<p style=\"text-align: justify;\">&nbsp;</p>"
            + "$LISTA_EVALUADORES"
            + "<p style=\"text-align: justify;\">&nbsp;</p>"
            + "<p style=\"text-align:justify\">El comit&eacute; se reuni&oacute; el d&iacute;a $FECHA_AUTORIZADO &nbsp;y antes de recibir las propuestas a revisar se procedi&oacute; a la lectura y firma de los siguientes documentos: memor&aacute;ndum correspondiente a la Solicitud de Cotizaci&oacute;n RDC N&deg; $RDC_NRO , Conflicto de inter&eacute;s y Declaraci&oacute;n de Confidencialidad, y la Certificaci&oacute;n y Acuerdo para el Uso y Divulgaci&oacute;n de Propuestas en Respuesta a esta Requisici&oacute;n de Cotizaci&oacute;n (RDC). Posteriormente los miembros del comit&eacute; notificaron a la presidenta no tener conflicto de intereses real o aparente, motivo por el cual se dio inicio a la evaluaci&oacute;n de las propuestas recibidas.</p>"
            + "<p style=\"text-align: justify;\">&nbsp;</p>"
            + "$ANTECEDENTES_REQ";
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Nro de Pasos CONTRATO">
    public static final String NUMERO_PASO_NOTIFICAR_EVALUACIONES = "15";
    public static final String NUMERO_PASO_CONSULTA_EVALUACION = "9";
    public static final String NUMERO_PASO_NOTIFICAR_CONTRACION_DIRECTA = "14";
    public static final String NUMERO_PASO_RECEPCIONAR_PROPUESTAS = "7";
    public static final String NUMERO_PASO_PENDIENTE_PUBLICACION = "11";

//</editor-fold>
    public static final String TIPO_ORDEN_COMPRA = "OC";
    public static final String TIPO_CONTRATO_DIRECTO = "CD";
    public static final String TIPO_CONTRATO = "CON";
    public static final String TIPO_RDV = "RDV";

    //<editor-fold defaultstate="collapsed" desc="Estados Adenda - Aplica a TDR y RDC">
    public static final String ESTADO_ACTUAL = "A";
    public static final String ESTADO_HISTORICO = "H";

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Tipos Archivos">
    public static final String TIPO_ARCHIVO_ADENDA = "A";
    public static final String TIPO_ARCHIVO_OTROS = "O";
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Estados Modificacion">
    public static final String ESTADO_MODIFICACION_CONTRATO_SOLICITADO = "S";
    public static final String ESTADO_MODIFICACION_CONTRATO_SOLICITADO_APROBADO = "A";
    public static final String ESTADO_MODIFICACION_CONTRATO_SOLICITADO_RECHAZADO = "R";

    public static final String PATH_SOLICITUD_MODIFICACION_CONTRATO = "solicitudModifContrato";

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Modificacion de Contrato">
    public static final String TEXTO_MEMO = "<p><span style=\"color:#FF0000\"><strong>El prop&oacute;sito de esta modificaci&oacute;n es:</strong></span></p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li><span style=\"color:#FF0000\"><strong>Ampliaci&oacute;n del plazo de ejecuci&oacute;n. Hasta el 20 de enero de 2016.</strong></span></li>\n"
            + "</ol>\n"
            + "\n"
            + "<p><span style=\"color:#FF0000\"><u>Justificaci&oacute;n:</u> necesidad de continuar&nbsp; con el acompa&ntilde;amiento&nbsp; a la implementaci&oacute;n del Portal de Datos Abiertos para el MSPyBS. A ser utilizados durante el evento del Hackaton 2015 y al desarrollo de la propuesta adjudicada.</span></p>\n"
            + "\n"
            + "<p style=\"margin-left:.25in\"><span style=\"color:#FF0000\">&nbsp;En consecuencia, se modifican los siguientes &iacute;temes y quedan redactados como sigue:</span></p>\n"
            + "\n"
            + "<p style=\"margin-left:.25in\"><span style=\"color:#FF0000\">2.1. En los <strong>T&eacute;rminos de Referencia</strong>- Secci&oacute;n A. &nbsp;XII. <u>DURACI&Oacute;N DE LA CONSULTOR&Iacute;A. </u>&nbsp;La duraci&oacute;n de la consultor&iacute;a es desde&nbsp; <strong>Noviembre 17/2014&nbsp; al 20/01/2016.</strong></span></p>\n"
            + "\n"
            + "<p style=\"margin-left:.25in\">&nbsp;</p>\n"
            + "\n"
            + "<p style=\"margin-left:.25in\"><span style=\"color:#FF0000\">2.2. En la planilla <strong>T&eacute;rminos de Pago- </strong>Secci&oacute;n B. &nbsp;</span></p>\n"
            + "\n"
            + "<p style=\"margin-left:.25in\"><span style=\"color:#FF0000\">La fecha m&aacute;xima de entrega del producto 8:&rdquo;Informe final de mantenimiento y pruebas de las herramientas desarrolladas en MEC, DNCP, SENATICs y MSPyBS ser&aacute; el 15/01/2016.</span></p>\n"
            + "\n"
            + "<p><span style=\"color:#FF0000\"><strong><em>Todos los dem&aacute;s t&eacute;rminos y condiciones se mantendr&aacute;n sin cambios.</em></strong></span></p>";
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="RRHH">
    public static final String CODIGO_ROL_RRHH = "RRHH";
    public static final BigDecimal montoPorcIPSMensual = new BigDecimal(6868);
    public static final BigDecimal montoPorcIPSJornal = new BigDecimal(7925);
//</editor-fold>
    public static final String COD_SUCURSAL_REMOTA = "COD_SUCURSAL_REMOTA";
    public static final String COD_SUCURSAL_MOSTRADOR_ = "COD_SUCURSAL_MOSTRADOR";
    public static final String COD_SUCURSAL_INSUMOS = "COD_SUCURSAL_INSUMOS";
    public static final String COD_SUCURSAL_PEDIDO_EXPRESS = "COD_SUCURSAL_COCINA_EX";
    public static final String COD_SUCURSAL_PRODUCCION = "COD_SUCURSAL_PRODUCCION";
    public static final String COD_SUCURSAL_CAMARA = "COD_SUCURSAL_CAMARA";
    public static final String NUM_MAX_AMARILLO = "NUM_MAX_AMARILLO";
    public static final String NUM_MAX_ROJO = "NUM_MAX_ROJO";
    public static final String NUM_MAX_AMARILLO_FACTURA = "NUM_MAX_AMARILLO_FACTURA";
    public static final String NUM_MAX_ROJO_FACTURA = "NUM_MAX_ROJO_FACTURA";
    public static final String MODIFICA_PEDIDO = "MODIFICA_PEDIDO";
    public static final String SALARIO_MINIMO = "SALARIO_MINIMO";

    public static final String BOOLEAN_TRUE = "true";
    public static final String BOOLEAN_FALSE = "false";
    public static final String CONSULTA_CUENTA_PROVEEDORES = "consulta_cuenta_proveedores_output.xlsx";
    public static final String CONSULTA_CUENTA_PROVEEDORES_TEMPLATE = "consultas_cuenta_proveedores_template.xlsx";
    public static final String FORMA_COBRO_TEMPLATE_NAME = "formas_cobros_template.xlsx";
    public static final String FORMA_COBRO_NAME = "formas_cobros_output.xlsx";
//<editor-fold defaultstate="collapsed" desc="Reportes word ">
    public static final String TEMPLATE_FACTURA = "factura.docx";
    public static final String TEMPLATE_COMPRA = "compra.docx";

    public static final String TEMPLATE_NOTA_REMISION = "NotaRemision.docx";
//</editor-fold>

    public static final String ESTADO_ACTIVO = "A";
    public static final String RUC = "RUC";
    public static final String CI = "CI";
    public static final String ACTIVO = "ACTIVO";
    public static final String INACTIVO = "INACTIVO";
    public static final String BLOQUEADO = "BLOQUEADO";
    public static final String CREDITO_BLOQUEADO = "CREDITO BLOQUEADO";

    public static final String ESTADO_CLIENTE_ACTIVO = "A";
    public static final String ESTADO_CLIENTE_INACTIVO = "I";
    public static final String ESTADO_CLIENTE_BLOQUEADO = "B";
    public static final String ESTADO_CLIENTE_CREDITO_BLOQUEADO = "C";
    public static final String MSG_RECIBO_RECHAZADO = "Recibo Rechazado, Saldo Negativo. Intente Recibir Datos";

    //<editor-fold defaultstate="collapsed" desc="Hechauka">
    public static final String VENTIPIMP = "I";
    public static final Integer VENRUB05 = 0;
    public static final Integer VENIMPUTA = 0;
    public static final Integer GENERAR = 0;
    public static final String ORIGEN = "LI";
    public static final Integer VENCUENTA = 40104;
    public static final String VENPRVNOM = "IMPORTES CONSOLIDADOS";
    public static final String VENDISGRA = "A";
    public static final Integer CTAIVA = 101030503;
    public static final Integer CTACAJA = 0;
    public static final Integer VENRETENC = 0;
    public static final Integer VENAUX = 0;
    public static final Integer VENCTRL = 0;
    public static final Integer CAMBIO = 0;
    public static final Integer VALOR = 0;
    public static final Integer TKDESDE = 0;
    public static final Integer TKHASTA = 0;
    public static final Integer CAJA = 0;
    public static final Integer FORMADEVO = 1;
    public static final Integer USEIDE = 0;
    public static final String HECHAUKA_TEMPLATE_NAME = "hechauka_2.xlsx";
    public static final String HECHAUKA_OUTPUT_NAME = "hechauka_2_output.xlsx";
    public static String nombreFormaActualizarFacturaVentas = "VTAFACTU";
    public static String nombreFormaRemisionesVentas = "VTAENV";
    public static String NOMBRE_FORMA_AUDITORIA = "MONAUDIT";
    public static final String nombreFormaSession = "BSSES";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Email Config">
    public static final String from = "posada@pedazodecielo.com.py";
    public static final String bcc = "pedazodecielo@fast.com.py";
    public static final String host = "mail.pedazodecielo.com.py";
    public static final String port = "26";
    public static final String tls = "true";
    public static final String auth = "true";
    public static final String user = "posada@pedazodecielo.com.py";
    public static final String pwd = "pdc2018";
    public static final String active = "true";
    public static final String isGmail = "false";

//</editor-fold>
    public static final String codigoGrupoTecnicos = "TC";

    //<editor-fold defaultstate="collapsed" desc="PORTERIA">
    public static final String CODIGO_MODULO_PORTERIA = "PO";
    public static final String nombreFormaTipoMovimiento = "PRWTMOV";
    public static final String nombreFormaIngreso = "PRWINGRE";
    public static final String nombreFormaDestino = "PRWDEST";
    public static final String nombreFormaMovimientoIngresos = "PRWMOVIN";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Modulo Expedientes">
    public static final String nombreFormaMewEstado = "MEESTADO";
    public static final String nombreFormaMEFOR = "MEFOR";
    public static final String nombreFormaTipoExpediente = "METIP";
    public static final String nombreFormaConsultarExpedientes = "MECONEX";
    public static final String puedeCrearExpedientes = "MECREEXP";
    public static final String puedeConsultarHisExpedientes = "MECOHEX";
    public static final String nombreFormaProcesoExpediente = "MEPROEX";

    public static final String CONSULTA_MESA_ENTRADA_TEMPLATE_NAME = "mesa_entrada.xlsx";
    public static final String CONSULTA_MESA_ENTRADA_OUTPUT_NAME = "mesa_entrada_out.xlsx";
    public static final String TEMPLATE_NAME_MESA_ENTRADA = "mesa_entrada.docx";
    public static final String NOMBRE_FORMA_CAUAS = "TSCAU";
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="BASCULA">
    public static final String nombreFormaFletes = "BAWFLETE";
    public static final String nombreFormaIngresosConfirmados = "BAWINCON";
    public static final String tipoVehiculoSimple = "SIMPLE";
    public static final String tipoVehiculoMultiple = "MULTIPLE";
    public static final String nombreFormaTurnos = "BAWTURNOS";
    public static String nombreFormaBasculas = "BAWBASCULA";
    //</editor-fold>

        public static final String nombreFormaMotInut = "MOT_INUT";
    
    public static final String nombreFormaBswSectorEcon = "SBW_SECECON";
}
