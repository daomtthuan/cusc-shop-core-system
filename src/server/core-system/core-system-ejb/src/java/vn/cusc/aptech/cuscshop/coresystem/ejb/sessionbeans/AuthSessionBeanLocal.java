package vn.cusc.aptech.cuscshop.coresystem.ejb.sessionbeans;

import javax.ejb.Local;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;

@Local
public interface AuthSessionBeanLocal {

    String login(final String username, final String password);

    Account getAccount();

    void logout();

}
