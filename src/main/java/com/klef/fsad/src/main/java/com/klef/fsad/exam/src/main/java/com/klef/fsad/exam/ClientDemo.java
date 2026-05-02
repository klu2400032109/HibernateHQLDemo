package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // INSERT
        Delivery d1 = new Delivery("Order1", new Date(), "Delivered", "Hyderabad", 500);
        Delivery d2 = new Delivery("Order2", new Date(), "Pending", "Delhi", 800);

        session.save(d1);
        session.save(d2);

        System.out.println("Records Inserted");

        // DELETE using HQL positional parameter
        int deleteId = 1;

        String hql = "delete from Delivery where id = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, deleteId);

        int result = query.executeUpdate();

        System.out.println(result + " Record Deleted");

        tx.commit();

        session.close();
        sf.close();
    }
}
