/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Paulo
 */
public class JPAUtil {    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("fastnewsPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
