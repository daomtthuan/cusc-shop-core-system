package vn.cusc.aptech.cuscshop.coresystem.ejb.sessionbeans;

import javax.ejb.Local;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;

@Local
public interface AuthenticationSessionBeanLocal {

    Account authenticate(final String username, final String password);

}
