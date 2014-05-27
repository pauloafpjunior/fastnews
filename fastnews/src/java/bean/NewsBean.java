/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.NewsDAO;
import dao.jpa.FactoryDAOJPA;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.News;
import model.User;

/**
 *
 * @author Paulo
 */
@ManagedBean(name = "newsBean")
@ViewScoped
public class NewsBean implements Serializable {

    private List<News> news = new ArrayList<News>();
    private final NewsDAO newsDAO = (new FactoryDAOJPA()).getNewsDAO();
    private News anotherNews = new News();
    private final int NEWS_BY_PAGE = 10;
    private final int TOP_NEWS = 3;
    private int page = 1;

    public User getLoggedUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserBean userBean = (UserBean) context.getELContext().getELResolver().getValue(context.getELContext(), null, "userBean");
        if (userBean != null) {
            return userBean.getLoggedUser();
        }
        return null;
    }

    public String sendNews() {
        anotherNews.setUser(getLoggedUser());
        newsDAO.save(anotherNews);
        anotherNews = new News();
        return "news";
    }

    private List<News> getNews(int n) {
        news = newsDAO.searchAllNews(n);
        return news;
    }

    public List<News> getMyNews() {
        news = newsDAO.searchNewsByUser(getLoggedUser());
        return news;
    }

    public List<News> getAllNews() {
        return getNews(page * NEWS_BY_PAGE);
    }

    public void moreNews() {
        page++;
    }

    public List<News> getTopNews() {
        return getNews(TOP_NEWS);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public News getAnotherNews() {
        return anotherNews;
    }

    public void setAnotherNews(News anotherNews) {
        this.anotherNews = anotherNews;
    }

}
