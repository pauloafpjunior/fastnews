/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.News;
import model.User;

/**
 *
 * @author Paulo
 */
public interface NewsDAO {
    public void save(News n);
    public List<News> searchAllNews(int n);
    public List<News> searchNewsByUser(User u);
}
