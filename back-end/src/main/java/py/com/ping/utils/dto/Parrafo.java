/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utils.dto;

import java.util.List;

/**
 *
 * @author rudy
 */
public class Parrafo {
    private String txtPadre;
    private List<Parrafo> txtHijos;

    public Parrafo() {
    }

    public String getTxtPadre() {
        return txtPadre;
    }

    public void setTxtPadre(String txtPadre) {
        this.txtPadre = txtPadre;
    }

    public List<Parrafo> getTxtHijos() {
        return txtHijos;
    }

    public void setTxtHijos(List<Parrafo> txtHijos) {
        this.txtHijos = txtHijos;
    }
    
    
    
}
