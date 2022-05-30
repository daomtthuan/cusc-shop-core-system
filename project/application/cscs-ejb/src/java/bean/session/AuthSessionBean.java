package bean.session;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.mindrot.jbcrypt.BCrypt;
import entity.facade.EmployeeFacadeLocal;
import entity.facade.InformationFacadeLocal;
import entity.facade.RoleFacadeLocal;
import entity.Account;
import entity.Employee;
import entity.Information;

@Stateless
public class AuthSessionBean implements AuthSessionBeanLocal {

  @EJB
  private RoleFacadeLocal roleFacade;

  @EJB
  private InformationFacadeLocal informationFacade;

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

  @Override
  public Employee authenticateByLocalAccount(String username, String password) {
    Employee account = employeeFacade.findNotShipperByUsername(username);

    return authenticateLocalAccount(account, password) ? account : null;
  }

  @Override
  public String changePassword(Object id, String oldPassword, String newPassword) {
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

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public String createAccount(String username, Object idRole, boolean state, String fullName, Date birthday, boolean gender, String email, String phone, String address) {
    if (employeeFacade.findByUsername(username) != null) {
      return "Username already exists";
    }

    Information information = new Information(null, fullName, birthday, gender, email, address, phone);
    informationFacade.create(information);

    String hashPassword = BCrypt.hashpw("1234", BCrypt.gensalt());
    Employee account = new Employee(null, username, hashPassword, state);
    account.setInformation(information);
    account.setRole(roleFacade.find(idRole));
    employeeFacade.create(account);

    return null;
  }

}
