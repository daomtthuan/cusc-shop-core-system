package vn.cusc.aptech.cuscshop.coresystem.ejb.sessionbeans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;
import vn.cusc.aptech.cuscshop.coresystem.ejb.facades.AccountFacadeLocal;

@Stateless
public class AuthenticationSessionBean implements AuthenticationSessionBeanLocal {

    @EJB
    private AccountFacadeLocal accountFacade;

    @Override
    public Account authenticate(final String username, final String password) {
        Account account = this.accountFacade.findByUsername(username);
        if (account != null) {
            if (BCrypt.checkpw(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

}
