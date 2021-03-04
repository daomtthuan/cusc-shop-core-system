package vn.cusc.aptech.cuscshop.coresystem.war.session;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;
import vn.cusc.aptech.cuscshop.coresystem.ejb.sessionbeans.AuthenticationSessionBeanLocal;

@Named(value = "authenticationSession")
@SessionScoped
public class AuthenticationSession implements Serializable {

    @EJB
    private AuthenticationSessionBeanLocal authenticationSessionBean;

    private Account account;

    @PostConstruct
    public void initialize() {
        this.account = null;
    }

    public void login(final String username, final String password) {
        Account accountTemp = this.authenticationSessionBean.authenticate(username, password);
        if (accountTemp != null) {
            this.account = accountTemp;
        }
    }

    public void logout() {
        this.account = null;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isLoggedIn() {
        return account != null;
    }

}
