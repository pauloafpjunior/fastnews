/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paulo
 */
@NamedQueries({
        @NamedQuery(name = News.SEARCH_ALL_NEWS, query = "select n from News n order by n.moment desc"),
        @NamedQuery(name = News.SEARCH_NEWS_BY_USER, query = "select n from News n where n.user = :user order by n.moment desc")
})
@Entity
public class News implements Serializable {

    public static final String SEARCH_ALL_NEWS = "News.searchAllNews";
    public static final String SEARCH_NEWS_BY_USER = "News.searchNewsByUser";

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long views;
    private String link;
    @Temporal(TemporalType.TIMESTAMP)
    private Date moment;
    @ManyToOne
    private User user;

    public News() {
        this.moment = Calendar.getInstance().getTime();        
        this.views = new Long(0);
    }
    
    public String getPastTime() {
        long dtCurrent = Calendar.getInstance().getTimeInMillis();
        long dtPrevious = this.moment.getTime();
        long dif = dtCurrent - dtPrevious;
        if (dif < 1000 * 60) return (dif / 1000 + "s ");
        else if (dif < 1000 * 60 * 60) return (dif / (1000 * 60) + "m ");
        else if (dif < 1000 * 60 * 60 * 24) return (dif / (1000 * 60 * 60) + "h ");
        else return (dif / (1000 * 60 * 60 * 24) + "d ");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }   
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    
    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
