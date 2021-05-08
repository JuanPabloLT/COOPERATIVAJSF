/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.dao;

import com.cooperativajsf.entity.Coop$Persona;
import com.cooperativajsf.entity.Coop$Logueopersona;
import com.cooperativajsf.entity.Coop$Personajuridica;
import com.cooperativajsf.entity.Coop$Personanatural;
import com.cooperativajsf.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juan.londoño
 */
public class ImpRegistrar implements IfaceRegistrar {

    @Override
    public void insertpl(Coop$Persona objp, Coop$Logueopersona objlog) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            objp.setLogueopersona(objlog);
            objlog.setPersona(objp);
            session.save(objlog);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public void insertpj(Coop$Persona objp, Coop$Personajuridica objpj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            objp.setPersonajuridica(objpj);
            objpj.setPersona(objp);
            session.save(objpj);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public void insertpn(Coop$Persona objp, Coop$Personanatural objpn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            objp.setPersonanatural(objpn);
            objpn.setPersona(objp);
            session.save(objpn);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public void delete(Coop$Persona obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public void update(Coop$Persona obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(obj);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public List<Coop$Persona> getAll() {
        List<Coop$Persona> personas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Coop$Persona as a "
                + "inner join fetch a.coop$Logueopersonas "
                + "inner join fetch a.coop$Personajuridicas "
                + "inner join fetch a.coop$Personanaturals";
        try {
            personas = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return personas;
    }
}
