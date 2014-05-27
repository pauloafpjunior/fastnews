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
public interface FactoryDAO {
    public UserDAO getUserDAO();
    public NewsDAO getNewsDAO();    
}
