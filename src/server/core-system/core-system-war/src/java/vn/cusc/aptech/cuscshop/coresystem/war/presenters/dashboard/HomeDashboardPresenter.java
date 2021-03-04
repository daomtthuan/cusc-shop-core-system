package vn.cusc.aptech.cuscshop.coresystem.war.presenters.dashboard;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cuscshop.coresystem.war.helpers.ViewHelper;
import vn.cusc.aptech.cuscshop.coresystem.war.session.AuthenticationSession;

@Named(value = "homeDashboardPresenter")
@ViewScoped
public class HomeDashboardPresenter implements Serializable {

    @Inject
    private AuthenticationSession authenticationSession;

    public String logout() {
        this.authenticationSession.logout();
        return ViewHelper.redirect("/pages/login");
    }
}
