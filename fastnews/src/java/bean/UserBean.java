/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UserDAO;
import dao.jpa.FactoryDAOJPA;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Paulo
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private final UserDAO userDAO = (new FactoryDAOJPA()).getUserDAO();
    private User newUser = new User();
    private User loggedUser = null;
    private String verifyPassword = "";
    private String emailLogin = "";
    private String passwordLogin = "";

    public String getMessage(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        return bundle.getString(key);
    }

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        return "index.xhtml?faces-redirect=true";
    }

    public String login() {
        FacesMessage message;
        loggedUser = userDAO.searchByEmail(emailLogin);
        if (loggedUser != null) {
            if (loggedUser.validate(passwordLogin)) {
                return "news";
            } else {
                loggedUser = null;
                message = new FacesMessage(getMessage("invalidPassword"));
            }
        } else {
            message = new FacesMessage(getMessage("invalidUser"));
        }
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }

    public String addUser() {
        FacesMessage message;
        if (verifyPassword != null && verifyPassword.equals(newUser.getPassword())) {
            if (userDAO.searchByEmail(newUser.getEmail()) == null) {

                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                newUser.setIP(ipAddress);
                
                userDAO.save(newUser);
                newUser = new User();
                message = new FacesMessage(getMessage("addedUser"));
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login";
            } else {
                message = new FacesMessage(getMessage("alreadyUser"));
            }
        } else {
            message = new FacesMessage(getMessage("passwordsDoNotMatch"));
        }
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
