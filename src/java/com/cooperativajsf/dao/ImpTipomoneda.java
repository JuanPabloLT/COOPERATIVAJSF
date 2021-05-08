/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.dao;

import com.cooperativajsf.entity.Coop$Moneda;
import com.cooperativajsf.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan.londoño
 */
public class ImpTipomoneda implements IfaceTipomoneda {

    @Override
    public List<Coop$Moneda> getMonedas() {
        List<Coop$Moneda> monedas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Coop$Moneda";
        try {
            monedas = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return monedas;
    }
}
