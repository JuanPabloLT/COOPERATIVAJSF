/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.dao;

import com.cooperativajsf.entity.Coop$Cuenta;
import com.cooperativajsf.entity.Coop$Persona;
import com.cooperativajsf.entity.Coop$Movimiento;
import com.cooperativajsf.entity.Coop$Razonsocial;
import com.cooperativajsf.entity.Coop$Moneda;
import com.cooperativajsf.entity.Coop$Tipomovimiento;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author juan.londoño
 */
public interface IfaceInicio {

    //Consultas objetos para insertar
    public Coop$Cuenta ConsultarCuenta(String obj);

    public Coop$Persona ConsultarPersona(String obj);

    public Coop$Tipomovimiento ConsultarTipoMovimiento(String obj);

    public void insertMovimiento(Coop$Movimiento objm);

    public void insertCuenta(Coop$Cuenta objm, Coop$Moneda objmon);

    public void updateSaldo(Coop$Cuenta objm);

    public void deleteCuenta(Coop$Cuenta obj);

    //Listas para tablas
    public List<Coop$Cuenta> getCuentas(String obj);

    public List<Coop$Movimiento> getMovimientos(String obj);
}
