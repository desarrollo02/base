/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.cdi;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author inv
 */
@Named
@ViewScoped
public class ReporteAccesoGruposControlador  implements Serializable{

 /* List<BswGruposUsuarios> gruposUsuariosLista;

  private String codGrupoDesde;

  private String descripcionDesde;

  private String codGrupoHasta;

  private String descripcionHasta;

  private BswGruposUsuarios grupoUsuarioSeleccionado;

  private ImprimirReporte imprimirreporte;

  private String inputPresionado;

  private String paginaActual;

  private @Inject LoginControlador loginControlador;

  @PersistenceContext
  protected EntityManager em;


  public ReporteAccesoGruposControlador()
  {
  super();
  gruposUsuariosLista = new ArrayList<BswGruposUsuarios>();
  imprimirreporte = new ImprimirReporte(getEmpresaLogueada());
  }

  public List<BswGruposUsuarios> getGrupoUsuarioLista() {
    if(gruposUsuariosLista == null || gruposUsuariosLista.isEmpty())
    {
    Query query =  em.createNamedQuery("BswGruposUsuarios.findAll");//BswAccesosGrupos.findAll
    gruposUsuariosLista =  (List<BswGruposUsuarios>) query.getResultList();
    }
    return gruposUsuariosLista;
  }

  public void setGrupoUsuarioLista(List<BswGruposUsuarios> accesosGruposLista) {
    this.gruposUsuariosLista = accesosGruposLista;
  }

  public String getCodGrupoDesde() {
    return codGrupoDesde;
  }

  public void setCodGrupoDesde(String codGrupo) {
    this.codGrupoDesde = codGrupo;
  }

  public String getDescripcionDesde() {
    return descripcionDesde;
  }

  public void setDescripcionDesde(String descripcion) {
    this.descripcionDesde = descripcion;
  }

  public void imprimirReporte()
  {
  imprimirreporte.setNombreReporte("accesoGrupos");
  Map<String, Object> parametros = new HashMap<String, Object>();
  parametros.put("codGrupoDesde", getCodGrupoDesde());
  parametros.put("codGrupoHasta", getCodGrupoHasta());
  parametros.put("impresoPor",loginControlador.getUsuario().getCodUsuario());
  imprimirreporte.setParametros(parametros);
  imprimirreporte.imprimir();
  limpiarCampos();
  }

  @Override
  public void setParametros() {
    this.formName = "BSRACCGR";
    this.clase = BswGruposUsuarios.class;//BswAccesosGrupos.class;
    this.paginaActual = "reporteAccesoGrupos.xhtml";
    

        if(getAcercaDe().isEmpty()){
            getAcercaDe().add("Datos de la pantalla:");
            getAcercaDe().add("- Pantalla:reporteAccesoGrupos.xhtml");
            getAcercaDe().add("- Forma: BSRACCGR");
            getAcercaDe().add("- Controlador: ReporteAccesoGrupoControlador.java");
            getAcercaDe().add("- EJB: GenericoEJB.java");
            getAcercaDe().add("- JPA: BswAccesosGrupos.java");
            getAcercaDe().add("- REPORTE: accesoGrupos.jrxml");
        }

        if(getAyuda().isEmpty()){
            getAyuda().add("Esta pantalla puede ser utilizada para imprimir el reporte de acceso grupo.");
        }
  }

    public BswGruposUsuarios getGrupoUsuarioSeleccionado() throws InstantiationException, IllegalAccessException {
      if(grupoUsuarioSeleccionado == null)
      {
      grupoUsuarioSeleccionado = (BswGruposUsuarios) this.clase.newInstance();
      }
    return grupoUsuarioSeleccionado;

  }

  public void setGrupoUsuarioSeleccionado(BswGruposUsuarios grupoUsuarioSeleccionado) {
    this.grupoUsuarioSeleccionado = grupoUsuarioSeleccionado;
    if(inputPresionado != null)
    {
     if(inputPresionado.equals("CodGrupoDesde"))
      {
       setCodGrupoDesde(grupoUsuarioSeleccionado.getCodGrupo());
       setDescripcionDesde(grupoUsuarioSeleccionado.getDescripcion());
      }

      if(inputPresionado.equals("CodGrupoHasta"))
      {
       setCodGrupoHasta(grupoUsuarioSeleccionado.getCodGrupo());
       setDescripcionHasta(grupoUsuarioSeleccionado.getDescripcion());
      }
    }

  }

  public void asignarValor(ValueChangeEvent event) {
   this.inputPresionado = (String) event.getComponent().getAttributes().get("codigoGrupo");
 }

  public String getInputPresionado() {
    return inputPresionado;
  }

  public void setInputPresionado(String inputPresionado) {
    this.inputPresionado = inputPresionado;
  }

    public String getCodGrupoHasta() {
    return codGrupoHasta;
  }

  public void setCodGrupoHasta(String codGrupoHasta) {
    this.codGrupoHasta = codGrupoHasta;
  }

  public String getDescripcionHasta() {
    return descripcionHasta;
  }

  public void setDescripcionHasta(String descripcionHasta) {
    this.descripcionHasta = descripcionHasta;
  }

  public void limpiarCampos() {
   this.codGrupoDesde=null;
   this.codGrupoHasta=null;
   this.descripcionDesde=null;
   this.descripcionHasta=null;
  }
*/
   
}
