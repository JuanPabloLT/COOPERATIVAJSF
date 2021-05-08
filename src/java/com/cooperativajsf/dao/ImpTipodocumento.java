/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.dao;

import com.cooperativajsf.entity.Coop$Tipodocumento;
import com.cooperativajsf.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan.londoño
 */
public class ImpTipodocumento implements IfaceTipodocumento {

    @Override
    public List<Coop$Tipodocumento> getDocumentos() {
        List<Coop$Tipodocumento> documentos = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Coop$Tipodocumento";
        try {
            documentos = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return documentos;
    }
}
