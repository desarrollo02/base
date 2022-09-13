 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import py.com.ping.administracionBase.jpa.BswAccesosGrupos;
 import py.com.ping.administracionBase.jpa.BswModulos;
 import py.com.ping.administracionBase.util.ApplicationConstant;
 import py.com.ping.utilitarios.cdi.AbstractControllerGenerico;

 import javax.faces.view.ViewScoped;
 import javax.inject.Named;

 /**
  *
  * @author Administrador
  */
 @Named
 @ViewScoped
 public class BswAccesosGruposControlador extends AbstractControllerGenerico<BswAccesosGrupos> {

     private static final Logger log =
             LoggerFactory.getLogger(BswUsuariosControlador.class);

     @Override
     public void setParametros() {
         this.formName = ApplicationConstant.nombreFormaAccesoBase;
         this.paginaActual = "BswAccesosGrupos.xhtml";
         this.clase = BswAccesosGrupos.class;
         if (acercaDe.isEmpty()) {
             getAcercaDe().add("Datos de la pantalla:");
             getAcercaDe().add("- Pantalla:BswAccesosGrupos.xhtml");
             getAcercaDe().add("- Controlador: BswAccesosGruposControlador.java");
             getAcercaDe().add("- EJB: BswAccesosGruposEJB.java");
             getAcercaDe().add("- JPA: BswAccesosGrupos.java");
         }
 
         if (ayuda.isEmpty()) {
             getAyuda().add("Esta pantalla puede ser utilizada para agregar, modificar o elminar Accesos de Grupos a las Formas.");
             getAyuda().add("");
             getAyuda().add(" Para agregar debe hacar un click en el boton nuevo.");
             getAyuda().add(" Para modificar, hacer un click en el boton editar de la grilla, realizar los cambios y presionar guardar.");
             getAyuda().add(" Para eliminar, hacer un click en el boton eliminar de la grilla, luego presionar eliminar.");
         }
     }
 
    
     @Override
     protected void antesABM() {
         getActual().setCodUsuarioAud(getUserNombre());
         if (getComponentes().getBswModulosABM().getId() != null) {
             actual.setBswModulos(getComponentes().getBswModulosABM());
         }
         else{
             actual.setBswModulos(null);
         }
         if (getComponentes().getBswFormasABM().getId() != null) {
             actual.setBswFormas(getComponentes().getBswFormasABM());
         }
         else{
             actual.setBswFormas(null);
         }
         if (getComponentes().getBswGruposUsuariosABM().getId() != null) {
             actual.setBswGruposUsuarios(getComponentes().getBswGruposUsuariosABM());
         }
         else{
             actual.setBswGruposUsuarios(null);
         }
         actual.setBswEmpresas(empresaLogueada);
     }
 
     @Override
     public void cargarCamposRelacionados() {
         getComponentes().setBswModulosABM(getActual().getBswModulos());
         getComponentes().setBswFormasABM(getActual().getBswFormas());
         getComponentes().setBswGruposUsuariosABM(getActual().getBswGruposUsuarios());
     }
 
     @Override
     public void limpiarDatosLocales() {
         getComponentes().setBswModulosABM(new BswModulos());
         
     }
 }
