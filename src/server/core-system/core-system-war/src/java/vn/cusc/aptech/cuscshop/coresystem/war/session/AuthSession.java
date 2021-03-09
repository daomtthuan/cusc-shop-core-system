package vn.cusc.aptech.cuscshop.coresystem.war.session;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;
import vn.cusc.aptech.cuscshop.coresystem.ejb.sessionbeans.AuthSessionBeanLocal;

@Named(value = "authSession")
@SessionScoped
public class AuthSession implements Serializable {

    @EJB
    private AuthSessionBeanLocal auth;
    private Account account;

    @PostConstruct
    public void init() {
        account = null;
    }

    public void login(final String username, final String password) {
        account = auth.authenticateLocal(username, password);
    }

    public void logout() {
        account = null;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isLoggedIn() {
        return account != null;
    }

}
