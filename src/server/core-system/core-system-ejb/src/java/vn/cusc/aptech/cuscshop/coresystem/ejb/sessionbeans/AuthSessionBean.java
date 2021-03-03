package vn.cusc.aptech.cuscshop.coresystem.ejb.sessionbeans;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import org.mindrot.jbcrypt.BCrypt;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;
import vn.cusc.aptech.cuscshop.coresystem.ejb.facades.AccountFacadeLocal;

@Stateful
public class AuthSessionBean implements AuthSessionBeanLocal {

    @EJB
    private AccountFacadeLocal accountFacade;

    private Account account;

    @Override
    public String login(final String username, final String password) {
        this.account = this.accountFacade.findByUsername(username);
        if (this.account != null) {
            System.out.println("Text: ---- " + BCrypt.hashpw(password, BCrypt.gensalt()));
            if (BCrypt.checkpw(password, this.account.getPassword())) {
                return null;
            }
        }
        return "Username or password incorrect.";
    }

    @Override
    public Account getAccount() {
        return this.account;
    }

    @Override
    public void logout() {
        this.account = null;
    }

}
