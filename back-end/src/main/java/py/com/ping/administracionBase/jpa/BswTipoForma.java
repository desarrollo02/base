/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;




/**
 *
 * @author Rudy
 */
@Entity
@Table(name = "bsw_tipo_forma")

@NamedQueries({
    @NamedQuery(name = "BswTipoForma.findAll", query = "SELECT b FROM BswTipoForma b")
    , @NamedQuery(name = "BswTipoForma.findById", query = "SELECT b FROM BswTipoForma b WHERE b.id = :id")
    , @NamedQuery(name = "BswTipoForma.findByCodigo", query = "SELECT b FROM BswTipoForma b WHERE b.codigo = :codigo")
    , @NamedQuery(name = "BswTipoForma.findByDescripcion", query = "SELECT b FROM BswTipoForma b WHERE b.descripcion = :descripcion")})
public class BswTipoForma implements Serializable {

  

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "bswTipoForma")
    private List<BswFormas> bswFormasList;
    @Transient
    private List<BswFormas> formasPortipoListTransient;
    public BswTipoForma() {
    }

    public BswTipoForma(Integer id) {
        this.id = id;
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

    
    public List<BswFormas> getBswFormasList() {
        return bswFormasList;
    }

    public void setBswFormasList(List<BswFormas> bswFormasList) {
        this.bswFormasList = bswFormasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BswTipoForma)) {
            return false;
        }
        BswTipoForma other = (BswTipoForma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.BswTipoForma[ id=" + id + " ]";
    }
    
      /**
     * @return the formasPortipoListTransient
     */
    public List<BswFormas> getFormasPortipoListTransient() {
        return formasPortipoListTransient;
    }

    /**
     * @param formasPortipoListTransient the formasPortipoListTransient to set
     */
    public void setFormasPortipoListTransient(List<BswFormas> formasPortipoListTransient) {
        this.formasPortipoListTransient = formasPortipoListTransient;
    }
    
}
