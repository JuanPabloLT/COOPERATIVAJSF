package com.cooperativajsf.bean;

import com.cooperativajsf.dao.IfaceRegistrar;
import com.cooperativajsf.entity.Coop$Persona;
import com.cooperativajsf.entity.Coop$Tipodocumento;
import com.cooperativajsf.entity.Coop$Razonsocial;
import com.cooperativajsf.dao.IfaceTipodocumento;
import com.cooperativajsf.dao.ImpTipodocumento;
import com.cooperativajsf.dao.IfaceTiporazonsocial;
import com.cooperativajsf.dao.ImpRegistrar;
import com.cooperativajsf.dao.ImpTiporazonsocial;
import com.cooperativajsf.entity.Coop$Logueopersona;
import com.cooperativajsf.entity.Coop$Personajuridica;
import com.cooperativajsf.entity.Coop$Personanatural;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author juan.londoño
 */
@ManagedBean
@ViewScoped
public class RegistrarBean implements Serializable {

    private IfaceRegistrar registrarDAO;

    private String tipopersona;
    private Coop$Persona persona;
    private Coop$Personajuridica personajuridica;
    private Coop$Personanatural personanatural;
    private Coop$Logueopersona logueopersona;
    private Coop$Tipodocumento tipodocumento;
    private Coop$Razonsocial razonsocial;

    private List<SelectItem> listatipodocumento;
    private List<SelectItem> listatiporazonsocial;

    public RegistrarBean() {
        registrarDAO = new ImpRegistrar();
        tipodocumento = new Coop$Tipodocumento();
        razonsocial = new Coop$Razonsocial();
        personajuridica = new Coop$Personajuridica();
        personanatural = new Coop$Personanatural();
        logueopersona = new Coop$Logueopersona();
        persona = new Coop$Persona();
    }

    public String RegistrarPersona() throws Exception {
        String pagina = "";
        try {

            this.logueopersona.setDocumento(persona.getDocumento());
            this.persona.setTipopersona(tipopersona);
            registrarDAO.insertpl(persona, logueopersona);
            if (getTipopersona().equals("J")) {
                this.personajuridica.setNit(persona.getDocumento());
                this.personajuridica.setCoop$Razonsocial(razonsocial);
                registrarDAO.insertpj(persona, personajuridica);
            } else {
                this.personanatural.setDocumento(persona.getDocumento());
                this.personanatural.setCoop$Tipodocumento(tipodocumento);
                registrarDAO.insertpn(persona, personanatural);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Persona correctamente registrada"));
            pagina = "inicio";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible registrar"));
        }
        return pagina;

    }

    /**
     * Retorna la lista de tipos de documentos registrados en la base de datos
     *
     * @return
     */
    public List<SelectItem> getListatipodocumento() {
        this.listatipodocumento = new ArrayList<SelectItem>();
        IfaceTipodocumento iface = new ImpTipodocumento();
        List<Coop$Tipodocumento> t = iface.getDocumentos();
        listatipodocumento.clear();
        for (Coop$Tipodocumento tdocs : t) {
            SelectItem tdItem = new SelectItem(tdocs.getIdtipodocumento(), tdocs.getDescripcion());
            this.listatipodocumento.add(tdItem);
        }

        return listatipodocumento;
    }

    public void setListatipodocumento(List<SelectItem> listatipodocumento) {
        this.listatipodocumento = listatipodocumento;
    }

    /**
     * Retorna la lista de tipos de razon social registrados en la base de datos
     *
     * @return
     */
    public List<SelectItem> getListatiporazonsocial() {
        this.listatiporazonsocial = new ArrayList<SelectItem>();
        IfaceTiporazonsocial ifacer = new ImpTiporazonsocial();
        List<Coop$Razonsocial> r = ifacer.getRazonesSociales();
        listatiporazonsocial.clear();
        for (Coop$Razonsocial rs : r) {
            SelectItem rsItem = new SelectItem(rs.getIdrazonsocial(), rs.getDescripcion());
            this.listatiporazonsocial.add(rsItem);
        }

        return listatiporazonsocial;
    }

    public void setListatiporazonsocial(List<SelectItem> listatiporazonsocial) {
        this.listatiporazonsocial = listatiporazonsocial;
    }

    public String getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(String tipopersona) {
        this.tipopersona = tipopersona;
    }

    public Coop$Persona getPersona() {
        return persona;
    }

    public void setPersona(Coop$Persona persona) {
        this.persona = persona;
    }

    public Coop$Personajuridica getPersonajuridica() {
        return personajuridica;
    }

    public void setPersonajuridica(Coop$Personajuridica personajuridica) {
        this.personajuridica = personajuridica;
    }

    public Coop$Personanatural getPersonanatural() {
        return personanatural;
    }

    public void setPersonanatural(Coop$Personanatural personanatural) {
        this.personanatural = personanatural;
    }

    public Coop$Logueopersona getLogueopersona() {
        return logueopersona;
    }

    public void setLogueopersona(Coop$Logueopersona logueopersona) {
        this.logueopersona = logueopersona;
    }

    public Coop$Tipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Coop$Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Coop$Razonsocial getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(Coop$Razonsocial razonsocial) {
        this.razonsocial = razonsocial;
    }
}
