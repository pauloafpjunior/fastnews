/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jpa;

import dao.NewsDAO;
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
public class NewsDAOJPA implements NewsDAO {

    @Override
    public void save(News n) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (n.getId() == null) em.persist(n);
            else em.merge(n);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<News> searchAllNews(int n) {
        EntityManager em = JPAUtil.getEntityManager();
        Query q = em.createNamedQuery(News.SEARCH_ALL_NEWS, News.class);
        q.setMaxResults(n);
        return q.getResultList();
    }    

    @Override
    public List<News> searchNewsByUser(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        Query q = em.createNamedQuery(News.SEARCH_NEWS_BY_USER, News.class);
        q.setParameter("user", u);
        return q.getResultList();
    }    

}
