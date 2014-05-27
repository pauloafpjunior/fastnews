/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import bean.UserBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Paulo
 */
public class AuthenticationPhaseListener implements PhaseListener {

    private List<String> allowedPages = new ArrayList<String>();

    public AuthenticationPhaseListener() {
        allowedPages.add("/login.xhtml");
        allowedPages.add("/addUser.xhtml");
        allowedPages.add("/index.xhtml");
        allowedPages.add("/about.xhtml");
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        if (!allowedPages.contains(viewId)) {
            UserBean userBean = (UserBean) context.getELContext().getELResolver().getValue(context.getELContext(), null, "userBean");
            if (userBean != null && userBean.getLoggedUser() == null) {
                NavigationHandler navigator = context
                        .getApplication()
                        .getNavigationHandler();
                navigator.handleNavigation(context, null, "login");
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event
    ) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
