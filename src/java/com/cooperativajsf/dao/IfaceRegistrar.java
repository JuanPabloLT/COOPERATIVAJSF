/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.dao;

import com.cooperativajsf.entity.Coop$Logueopersona;
import com.cooperativajsf.entity.Coop$Persona;
import com.cooperativajsf.entity.Coop$Personajuridica;
import com.cooperativajsf.entity.Coop$Personanatural;
import java.util.List;

/**
 *
 * @author juan.londoño
 */
public interface IfaceRegistrar {

    public void insertpl(Coop$Persona objp, Coop$Logueopersona objlog) throws Exception;

    public void insertpj(Coop$Persona objp, Coop$Personajuridica objpj) throws Exception;

    public void insertpn(Coop$Persona objp, Coop$Personanatural objpn) throws Exception;

    public void delete(Coop$Persona obj) throws Exception;

    public void update(Coop$Persona obj) throws Exception;

    public List<Coop$Persona> getAll() throws Exception;
}
