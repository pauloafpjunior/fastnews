/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.User;

/**
 *
 * @author Paulo
 */
public interface UserDAO {
    public void save(User u);
    public User searchByEmail(String email);
}
