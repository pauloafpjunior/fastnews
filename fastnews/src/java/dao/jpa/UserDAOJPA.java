/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jpa;

import dao.UserDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import model.News;
import model.User;

/**
 *
 * @author Paulo
 */
public class UserDAOJPA implements UserDAO {

    @Override
    public void save(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (u.getId() == null) {
                em.persist(u);
            } else {
                em.merge(u);
            }
            tx.commit();
        } catch (Exception ex) {
            System.out.println("xxxxxxxxxxxx:");
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public User searchByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        Query q = em.createNamedQuery(User.SEARCH_BY_EMAIL, User.class);
        q.setParameter("email", email);
        try {
            return (User) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

}
