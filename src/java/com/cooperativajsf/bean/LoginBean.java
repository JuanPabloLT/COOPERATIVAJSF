/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.bean;

import com.cooperativajsf.dao.IfaceLogin;
import com.cooperativajsf.dao.ImpLogin;
import com.cooperativajsf.entity.Coop$Logueopersona;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

/**
 *
 * @author juan.londoño
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private IfaceLogin loginDAO;

    private Coop$Logueopersona login;
    private Coop$Logueopersona us;

    public LoginBean() {
        loginDAO = new ImpLogin();
        us = new Coop$Logueopersona();
        login = new Coop$Logueopersona();

    }

    public String LoguearPersona() throws Exception {
        String resultado = "";
        String pagina = "";
        try {
            resultado = loginDAO.ValidaLogueo(this.login);
            if (resultado != "") {
                this.us.setDocumento(resultado);
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("usuario", this.getUs().getDocumento());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Logueo exitoso"));
                pagina = "inicio";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible registrar"));
            }
        } catch (Exception e) {
            throw e;
        }
        return pagina;

    }

    /**
     * @return the login
     */
    public Coop$Logueopersona getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Coop$Logueopersona login) {
        this.login = login;
    }

    /**
     * @return the us
     */
    public Coop$Logueopersona getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(Coop$Logueopersona us) {
        this.us = us;
    }

    /**
     * Retorna la lista de tipos de documentos registrados en la base de datos
     *
     * @return
     */
}
