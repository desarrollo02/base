/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.cdi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author gdb <dominbla@gmail.com>
 */
@Named
@SessionScoped
public class ControladorInicio implements Serializable {
	private static final Logger logger =
			LoggerFactory.getLogger(ControladorInicio.class);

	private String txt1;
	
	private String txt2;
	
	private String txt3;
	
	private String txt4;
	
	private String txt5;
    
    private String txt6;
    
    private String txt7;
    
 
	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();
		
		for(int i = 0; i < 10; i++) {
			results.add(query + i);
		}
		
		return results;
	}
	
	
	public void handleSelect(SelectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected:" + event.getObject().toString(), null);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

  

  
	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}

	public String getTxt2() {
		return txt2;
	}

	public void setTxt2(String txt2) {
		this.txt2 = txt2;
	}

	public String getTxt3() {
		return txt3;
	}

	public void setTxt3(String txt3) {
		this.txt3 = txt3;
	}
	
	public String getTxt4() {
		return txt4;
	}

	public void setTxt4(String txt4) {
		this.txt4 = txt4;
	}

	public String getTxt5() {
		return txt5;
	}

	public void setTxt5(String txt5) {
		this.txt5 = txt5;
	}

    public String getTxt6() {
        return txt6;
    }

    public void setTxt6(String txt6) {
        this.txt6 = txt6;
    }

    public String getTxt7() {
        return txt7;
    }

    public void setTxt7(String txt7) {
        this.txt7 = txt7;
    }
    
    
    public EstadosDetalles getEstadoDetalleSelected() {
	if (estadoDetalleSelected == null)
		estadoDetalleSelected = EstadosDetalles.PENDIENTE;
	return estadoDetalleSelected;
}

/**
 * @param estadoDetalleSelected
 *            the estadoDetalleSelected to set
 */
public void setEstadoDetalleSelected(EstadosDetalles estadoDetalleSelected) {
	this.estadoDetalleSelected = estadoDetalleSelected;
}
private EstadosDetalles estadoDetalleSelected;
private List<EstadosDetalles> listaEstadoDet;
/**
 * @return the listaEstadoDet
 */
public List<EstadosDetalles> getListaEstadoDet() {
	if (listaEstadoDet == null) {
		listaEstadoDet = new ArrayList<EstadosDetalles>();
		listaEstadoDet.add(EstadosDetalles.PENDIENTE);
		listaEstadoDet.add(EstadosDetalles.PROCESO);
		listaEstadoDet.add(EstadosDetalles.FINALIZADO);

	}
	return listaEstadoDet;
}


public enum EstadosDetalles {

	PENDIENTE("0"), PROCESO("1"), FINALIZADO("2");
	private final String codEstado;

	EstadosDetalles(String cod) {
		this.codEstado = cod;
	}

	/**
	 * @return the codEstado
	 */
	public String getCodTipo() {
		return codEstado;
	}
}
/**
 * @param listaEstadoDet
 *            the listaEstadoDet to set
 */
public void setListaEstadoDet(List<EstadosDetalles> listaEstadoDet) {
	this.listaEstadoDet = listaEstadoDet;
}
}
