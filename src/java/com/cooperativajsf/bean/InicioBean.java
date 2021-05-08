package com.cooperativajsf.bean;

import com.cooperativajsf.dao.IfaceInicio;
import com.cooperativajsf.dao.IfaceTipomoneda;
import com.cooperativajsf.dao.ImpInicio;
import com.cooperativajsf.dao.ImpTipomoneda;
import com.cooperativajsf.entity.Coop$Movimiento;
import com.cooperativajsf.entity.Coop$Persona;
import com.cooperativajsf.entity.Coop$Cuenta;
import com.cooperativajsf.entity.Coop$Logueopersona;
import com.cooperativajsf.entity.Coop$Moneda;
import java.io.Serializable;
import com.cooperativajsf.entity.Coop$Tipomovimiento;
import java.math.BigDecimal;
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
public class InicioBean implements Serializable {

    private IfaceInicio inicioDAO;

    private String seleccionmenu;
    private String cuentaorigen;
    private BigDecimal valor;
    private String cuentadestino;
    private Coop$Cuenta cuenta;
    private Coop$Cuenta cuentadest;
    private Coop$Logueopersona logueo;
    private Coop$Movimiento movimiento;
    private Coop$Tipomovimiento tipomovimiento;
    private List<Coop$Cuenta> listacuentas;
    private List<Coop$Movimiento> listamovimientos;
    private Object obj;
    private Coop$Moneda tipomoneda;
    private Coop$Persona persona;

    private List<SelectItem> listatipomoneda;

    public InicioBean() {
        obj = FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("usuario");
        inicioDAO = new ImpInicio();
        cuenta = new Coop$Cuenta();
        cuentadest = new Coop$Cuenta();
        logueo = new Coop$Logueopersona();
        movimiento = new Coop$Movimiento();
        persona = new Coop$Persona();
        tipomovimiento = new Coop$Tipomovimiento();
        tipomoneda = new Coop$Moneda();

    }

    public void RegistrarConsignacion() throws Exception {
        try {
            cuenta = inicioDAO.ConsultarCuenta(this.cuentadestino);
            if (cuenta != null) {
                cuenta.setSaldo(cuenta.getSaldo().add(valor));
                inicioDAO.updateSaldo(cuenta);
                tipomovimiento = inicioDAO.ConsultarTipoMovimiento("Consignacion");
                movimiento.setCoop$Cuenta(cuenta);
                movimiento.setCoop$Tipomovimiento(tipomovimiento);
                movimiento.setValor(valor);
                inicioDAO.insertMovimiento(movimiento);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Consignacion realizada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible consignar"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible consignar"));
        }

    }

    public void RegistrarTransferencia() throws Exception {
        try {
            //retiro de mi cuenta
            cuenta = inicioDAO.ConsultarCuenta(this.cuentaorigen);

            if (cuenta != null) {
                cuentadest = inicioDAO.ConsultarCuenta(this.cuentadestino);
                if (cuentadest != null) {

                    cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
                    inicioDAO.updateSaldo(cuenta);
                    cuentadest.setSaldo(cuentadest.getSaldo().add(valor));
                    inicioDAO.updateSaldo(cuentadest);

                    this.tipomovimiento = inicioDAO.ConsultarTipoMovimiento("Transferencia");
                    movimiento.setCoop$Cuenta(cuenta);
                    movimiento.setCoop$Tipomovimiento(tipomovimiento);
                    movimiento.setValor(valor);
                    inicioDAO.insertMovimiento(movimiento);

                    this.tipomovimiento = inicioDAO.ConsultarTipoMovimiento("Consignacion");
                    movimiento.setCoop$Cuenta(cuentadest);
                    movimiento.setCoop$Tipomovimiento(tipomovimiento);
                    movimiento.setValor(valor);
                    inicioDAO.insertMovimiento(movimiento);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Transferencia realizada"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "Cuenta origen no valida"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible transferir"));
        }

    }

    public void RegistrarRetiro() throws Exception {
        try {
            cuenta = inicioDAO.ConsultarCuenta(this.cuentaorigen);
            if (cuenta != null) {
                cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
                inicioDAO.updateSaldo(cuenta);
                tipomovimiento = inicioDAO.ConsultarTipoMovimiento("Retiro");
                movimiento.setCoop$Cuenta(cuenta);
                movimiento.setCoop$Tipomovimiento(tipomovimiento);
                movimiento.setValor(valor);
                inicioDAO.insertMovimiento(movimiento);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Retiro realizado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible retirar"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible retirar"));
        }

    }

    public void RegistrarCuenta() throws Exception {
        try {
            persona = inicioDAO.ConsultarPersona(this.obj.toString());
            if (persona != null) {
                this.cuenta.setCoop$Persona(persona);
                this.cuenta.setCoop$Moneda(tipomoneda);
                inicioDAO.insertCuenta(cuenta, tipomoneda);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Cuenta registrada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible registrar"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible registrar"));
        }

    }

    public void EliminarCuenta() {
        try {
            inicioDAO.deleteCuenta(cuenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Cuenta eliminada"));
            cuenta = new Coop$Cuenta();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Notificación", "No es posible eliminar cuenta"));
        }

    }

    public boolean VerificarSesion() {
        boolean estado;

        if (this.getObj() == null) {
            estado = false;
        } else {
            estado = true;
        }
        return estado;
    }

    public String CerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        return "index?faces-redirect=true";
    }

    /**
     * @return the listamovimientos
     */
    public List<Coop$Movimiento> getListamovimientos() {
        listamovimientos = inicioDAO.getMovimientos(this.obj.toString());
        return listamovimientos;
    }

    /**
     * @param listamovimientos the listamovimientos to set
     */
    public void setListamovimientos(List<Coop$Movimiento> listamovimientos) {
        this.listamovimientos = listamovimientos;
    }

    public List<Coop$Cuenta> getListacuentas() {
        this.setObj(FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("usuario"));
        listacuentas = inicioDAO.getCuentas(this.obj.toString());
        return listacuentas;
    }

    /**
     * @param listamovimientos the listamovimientos to set
     */
    public void setListacuentas(List<Coop$Cuenta> listacuentas) {
        this.listacuentas = listacuentas;
    }

    /**
     * @return the seleccionmenu
     */
    public String getSeleccionmenu() {
        return seleccionmenu;
    }

    /**
     * @param seleccionmenu the seleccionmenu to set
     */
    public void setSeleccionmenu(String seleccionmenu) {
        this.seleccionmenu = seleccionmenu;
    }

    /**
     * @return the confirmardocumento
     */
    public String getCuentaorigen() {
        return cuentaorigen;
    }

    /**
     * @param confirmardocumento the confirmardocumento to set
     */
    public void setCuentaorigen(String cuentaorigen) {
        this.cuentaorigen = cuentaorigen;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the cuentadestino
     */
    public String getCuentadestino() {
        return cuentadestino;
    }

    /**
     * @param cuentadestino the cuentadestino to set
     */
    public void setCuentadestino(String cuentadestino) {
        this.cuentadestino = cuentadestino;
    }

    /**
     * @return the cuenta
     */
    public Coop$Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Coop$Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the movimiento
     */
    public Coop$Movimiento getMovimiento() {
        return movimiento;
    }

    /**
     * @param movimiento the movimiento to set
     */
    public void setMovimiento(Coop$Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * @return the tipomovimiento
     */
    public Coop$Tipomovimiento getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(Coop$Tipomovimiento tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public Object getObj() {
        return obj.toString();
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public List<SelectItem> getListatipomoneda() {
        this.listatipomoneda = new ArrayList<SelectItem>();
        IfaceTipomoneda iface = new ImpTipomoneda();
        List<Coop$Moneda> t = iface.getMonedas();
        listatipomoneda.clear();
        for (Coop$Moneda tms : t) {
            SelectItem tmItem = new SelectItem(tms.getIdmoneda(), tms.getDescripcion());
            this.listatipomoneda.add(tmItem);
        }

        return listatipomoneda;
    }

    public void setListatipomoneda(List<SelectItem> listatipomoneda) {
        this.listatipomoneda = listatipomoneda;
    }

    /**
     * @return the tipomoneda
     */
    public Coop$Moneda getTipomoneda() {
        return tipomoneda;
    }

    /**
     * @param tipomoneda the tipomoneda to set
     */
    public void setTipomoneda(Coop$Moneda tipomoneda) {
        this.tipomoneda = tipomoneda;
    }

    /**
     * @return the persona
     */
    public Coop$Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Coop$Persona persona) {
        this.persona = persona;
    }

}
