/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.dao;

import com.cooperativajsf.entity.Coop$Logueopersona;
import com.cooperativajsf.util.HibernateUtil;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author juan.londoño
 */
public class ImpLogin implements IfaceLogin {

    @Override
    public String ValidaLogueo(Coop$Logueopersona obj) {
        String user = "";
        List<String> logueo;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "Select documento FROM Coop$Logueopersona as a where a.documento = '" + obj.getDocumento() + "'"
                + " and contrasena = '" + obj.getContrasena() + "'";
        try {
            logueo = session.createQuery(hql).list();
            //System.out.println(logueo.get(0));

            if (logueo.size() > 0) {
                user = logueo.get(0);
            }
            //System.out.println(user);
            session.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
