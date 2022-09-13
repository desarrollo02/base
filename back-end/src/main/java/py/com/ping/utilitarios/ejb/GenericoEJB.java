 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

 /**
  *
  * @author inv
  *
  *
  *
  * EJB generico para operaciones ABM (Altas, Bajas y Modificaciones) de clases
  * JPA
  *
  * Se utilizan Generics para hacer que la clase sea generica y no tener que
  * realizar casting en los clientes de la clase (Controller JSF/CDI). Esto es
  * para los metodos ABM. Ej:
  *
  * private GenericoEJB<Persona> genericoEJB;
  *
  * Las funciones que realizan consultas, reciben como parametro el tipo de clase
  * JPA y concatenan el nombre de la clase con el nombre de la consulta
  */
 @Stateless
 public class GenericoEJB<E> {
 

     @PersistenceContext
     private EntityManager em;
 
     public void antesActualizar(E entidadNueva, E entidadVieja) {
     }
 
     public void insertar(E entidad) throws Exception {
         getEm().persist(entidad);
         
     }
 
     public E guardar(E entidad) throws Exception {
         getEm().persist(entidad);
         getEm().flush();
         return entidad;
 
     }
 
     public void actualizar(E entidadNueva) throws Exception {
         getEm().merge(entidadNueva);
         getEm().flush();
     }
 
     public void eliminar(E entidad) throws Exception {
         getEm().remove(getEm().merge(entidad));
         getEm().flush();
     }
 
     public E getPorPK(Class<?> entidad, Object pk) {
         return (E) getEm().find(entidad, Long.parseLong(pk.toString()));
     }
 
     //agregado para poder generar el codigo automaticamente
     public Object getCodigoMax(Class<?> clase) {
         Object numero = getEm().createQuery("SELECT max(c.codigo) FROM "
                 + clase.getSimpleName() + " c").getSingleResult();
         return numero;
     }
 
     public E getPorCodigo(Class<?> clase, Long codigo) {
         //return (E) em.createQuery("SELECT c FROM " + clase.getSimpleName() + " c WHERE c.codigo=:codigo").setParameter("codigo",codigo).getSingleResult();
         try {
             return (E) getEm().createNamedQuery(clase.getSimpleName() + ".getPorCodigo").
                     setParameter("codigo", codigo).getSingleResult();
         } catch (Exception er) {
             return null;
         }
     }
 
     public E getPorCodigo(Class<?> clase, String codigo) {
         //return (E) em.createQuery("SELECT c FROM " + clase.getSimpleName() + " c WHERE c.codigo=:codigo").setParameter("codigo",codigo).getSingleResult();
         return (E) getEm().createNamedQuery(clase.getSimpleName() + ".getPorCodigo").
                 setParameter("codigo", codigo).getSingleResult();
     }
 
     public List<E> getCodigosPatron(Class<?> clase, String patron) {
         return (List<E>) getEm().createNamedQuery(clase.getSimpleName()
                 + ".getCodigosPatron").setParameter("patron", "%" + patron + "%").
                 getResultList();
     }
 
     public List<E> getDescripcionPatron(Class<?> clase, String patron) {
         return (List<E>) getEm().createNamedQuery(clase.getSimpleName() + ".getDescPatron").
                 setParameter("patron", "%" + patron + "%").getResultList();
     }
 
     public List<E> getTodos(Class<?> clase) {
         //return (List<E>) em.createQuery("SELECT")
         return (List<E>) getEm().createNamedQuery(clase.getSimpleName() + ".findAll").getResultList();
     }
 
     public List<E> ejecutarQueryLista(String query) {
         return getEm().createQuery(query).getResultList();
     }
 
     public E ejecutarQueryObjeto(String query) {
         return (E) getEm().createQuery(query).getSingleResult();
     }
 
     /**
      * @return the em
      */
     public EntityManager getEm() {
         return em;
     }
 
     /**
      * @param em the em to set
      */
     public void setEm(EntityManager em) {
         this.em = em;
     }
 }
