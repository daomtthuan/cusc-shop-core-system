package vn.cusc.aptech.cuscshop.coresystem.war.session;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;
import vn.cusc.aptech.cuscshop.coresystem.ejb.sessionbeans.AuthSessionBeanLocal;
import vn.cusc.aptech.cuscshop.coresystem.war.app.helpers.ViewHelper;

@Named(value = "authSession")
@SessionScoped
public class AuthSession implements Serializable {

  @Inject
  private ViewHelper viewHelper;

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

  public String logout() {
    account = null;
    return viewHelper.getPage("login");
  }

  public Account getAccount() {
    return account;
  }

  public boolean isLoggedIn() {
    return account != null;
  }

}
