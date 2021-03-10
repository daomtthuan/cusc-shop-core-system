package vn.cusc.aptech.cuscshop.coresystem.war.presenters.dashboard;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cuscshop.coresystem.war.app.helpers.ViewHelper;
import vn.cusc.aptech.cuscshop.coresystem.war.session.AuthSession;

@Named(value = "homeDashboardPresenter")
@ViewScoped
public class HomeDashboardPresenter implements Serializable {

    @Inject
    private ViewHelper viewHelper;
    @Inject
    private AuthSession authSession;

    public String logout() {
        authSession.logout();
        return viewHelper.getPage("login");
    }
}
