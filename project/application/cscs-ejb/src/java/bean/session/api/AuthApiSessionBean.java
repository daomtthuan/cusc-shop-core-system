package bean.session.api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;
import entity.facade.CustomerFacadeLocal;
import entity.facade.EmployeeFacadeLocal;
import entity.Account;
import entity.Customer;
import entity.Employee;
import common.Cryption;

@Stateless
public class AuthApiSessionBean implements AuthApiSessionBeanLocal {

  @EJB
  private CustomerFacadeLocal customerFacade;

  @EJB
  private EmployeeFacadeLocal employeeFacade;

  private boolean authenticateLocalAccount(Account account, String password) {
    if (account != null) {
      if (BCrypt.checkpw(password, account.getPassword())) {
        return true;
      }
    }
    return false;
  }

  private String createKey(Account account) {
    return Cryption.encode(account.getUsername() + ":" + BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
  }

  private String[] analyzeHashKey(String hashKey) {
    return Cryption.decode(hashKey).split(":");
  }

  @Override
  public String authenticateByCustomerLocalAccount(String username, String password) {
    Customer account = customerFacade.findByUsername(username);
    if (!authenticateLocalAccount(account, password)) {
      return null;
    }

    return createKey(account);
  }

  @Override
  public Customer authenticateByCustomerLocalAccount(String hashKey) {
    String[] keyParts = analyzeHashKey(hashKey);
    Customer account = customerFacade.findByUsername(keyParts[0]);
    if (account == null) {
      return null;
    }
    return BCrypt.checkpw(account.getPassword(), keyParts[1]) ? account : null;
  }

  @Override
  public String changePasswordCustomer(Object id, String oldPassword, String newPassword) {
    Customer account = customerFacade.find(id);
    if (account == null) {
      return "Account not found";
    }

    if (!authenticateLocalAccount(account, oldPassword)) {
      return "Password incorrect";
    }

    account.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    customerFacade.edit(account);
    return null;
  }

  @Override
  public String authenticateByShipperLocalAccount(String username, String password) {
    Employee account = employeeFacade.findShipperByUsername(username);
    if (!authenticateLocalAccount(account, password)) {
      return null;
    }

    return createKey(account);
  }

  @Override
  public Employee authenticateByShipperLocalAccount(String hashKey) {
    String[] keyParts = analyzeHashKey(hashKey);
    Employee account = employeeFacade.findShipperByUsername(keyParts[0]);
    if (account == null) {
      return null;
    }
    return BCrypt.checkpw(account.getPassword(), keyParts[1]) ? account : null;
  }

  @Override
  public String changePasswordShipper(Object id, String oldPassword, String newPassword) {
    Employee account = employeeFacade.find(id);
    if (account == null) {
      return "Account not found";
    }

    if (!authenticateLocalAccount(account, oldPassword)) {
      return "Password incorrect";
    }

    account.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    employeeFacade.edit(account);
    return null;
  }

}
