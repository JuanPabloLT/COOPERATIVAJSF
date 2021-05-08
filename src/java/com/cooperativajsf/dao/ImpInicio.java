/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativajsf.dao;

import com.cooperativajsf.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cooperativajsf.entity.Coop$Tipomovimiento;
import com.cooperativajsf.entity.Coop$Persona;
import com.cooperativajsf.entity.Coop$Cuenta;
import com.cooperativajsf.entity.Coop$Moneda;
import com.cooperativajsf.entity.Coop$Movimiento;

/**
 *
 * @author juan.londoño
 */
public class ImpInicio implements IfaceInicio {

    @Override
    public List<Coop$Cuenta> getCuentas(String obj) {
        List<Coop$Cuenta> cuentas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Coop$Cuenta c "
                + "inner join fetch c.coop$Persona p "
                + "inner join fetch c.coop$Moneda m WHERE p.documento = '" + obj + "'";
        try {
            cuentas = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return cuentas;
    }

    @Override
    public List<Coop$Movimiento> getMovimientos(String obj) {
        List<Coop$Movimiento> movimientos = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Coop$Movimiento m "
                + "inner join fetch m.coop$Cuenta c "
                + "inner join fetch c.coop$Persona p "
                + "inner join fetch c.coop$Moneda mo "
                + "inner join fetch m.coop$Tipomovimiento tm WHERE p.documento = '" + obj + "'";
        try {
            movimientos = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return movimientos;
    }

    @Override
    public Coop$Cuenta ConsultarCuenta(String obj) {
        List<Coop$Cuenta> cuentas = null;
        Coop$Cuenta cuenta = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Coop$Cuenta c "
                + "inner join fetch c.coop$Persona p "
                + "inner join fetch c.coop$Moneda m WHERE c.numerocuenta = '" + obj + "'";
        try {
            cuentas = session.createQuery(hql).list();
            transaction.commit();
            cuenta = cuentas.get(0);
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return cuenta;
    }

    @Override
    public Coop$Persona ConsultarPersona(String obj) {
        List<Coop$Persona> personas = null;
        Coop$Persona persona = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Coop$Persona p WHERE p.documento = '" + obj + "'";
        try {
            personas = session.createQuery(hql).list();
            transaction.commit();
            persona = personas.get(0);
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return persona;
    }

    @Override
    public void insertMovimiento(Coop$Movimiento objm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(objm);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public void updateSaldo(Coop$Cuenta objm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(objm);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public void deleteCuenta(Coop$Cuenta obj) {
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
    public void insertCuenta(Coop$Cuenta obj, Coop$Moneda objmo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            obj.setCoop$Moneda(objmo);
            session.save(obj);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public Coop$Tipomovimiento ConsultarTipoMovimiento(String obj) {
        List<Coop$Tipomovimiento> tiposmovimientos = null;
        Coop$Tipomovimiento tipomovimiento = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Coop$Tipomovimiento c WHERE c.descripcion = '" + obj + "'";
        try {
            tiposmovimientos = session.createQuery(hql).list();
            transaction.commit();
            tipomovimiento = tiposmovimientos.get(0);
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return tipomovimiento;
    }
}
