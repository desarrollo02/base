package py.com.ping.utilitarios.jpa;

import java.util.List;
import py.com.ping.utilitarios.jpa.EntidadCodigo;

/**
 *
 * @author javier martinez canillas
 *
 * Interfaz que tienen que implementar todas las clases JPA que quieran ser manejadas
 * por clases que extiendan el Controller generico y que representen el maestro en una pantalla
 * maestro/detalle
 */
public interface EntidadMaestro extends EntidadCodigo {
    public List getDetalle();
}
