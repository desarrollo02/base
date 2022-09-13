package py.com.ping.contabilidad.cdi.dto;

import java.io.Serializable;

/**
 *
 * @author rudy
 */
public class ExtensionCategoriasDto implements Serializable{
    private Integer id;
    private String codigo;
    private String descripcion;

    public ExtensionCategoriasDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
