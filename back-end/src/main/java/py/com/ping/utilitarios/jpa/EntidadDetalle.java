package py.com.ping.utilitarios.jpa;


/**
 *
 * @author gzacur
 *
 * Interfaz que tienen que implementar todas las clases JPA que quieran ser manejadas
 * por clases que extiendan el Controller generico y que representen el detalle en una pantalla
 * maestro/detalle
 *
 */
public interface EntidadDetalle extends EntidadCodigo {
    public void setMaestro(EntidadMaestro maestro);
}
