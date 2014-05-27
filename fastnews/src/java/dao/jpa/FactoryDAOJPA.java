/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jpa;

import dao.NewsDAO;
import dao.UserDAO;

/**
 *
 * @author Paulo
 */
public class FactoryDAOJPA implements FactoryDAO {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOJPA();
    }
    @Override
    public NewsDAO getNewsDAO() {
        return new NewsDAOJPA();        
    }
}
