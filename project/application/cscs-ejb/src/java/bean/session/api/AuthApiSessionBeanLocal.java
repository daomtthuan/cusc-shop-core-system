package bean.session.api;

import javax.ejb.Local;
import entity.Customer;
import entity.Employee;

/**
 *
 * @author Daomtthuan
 */
@Local
public interface AuthApiSessionBeanLocal {

  String authenticateByCustomerLocalAccount(String username, String password);

  Customer authenticateByCustomerLocalAccount(String hashKey);

  String changePasswordCustomer(Object id, String oldPassword, String newPassword);

  String authenticateByShipperLocalAccount(String username, String password);

  Employee authenticateByShipperLocalAccount(String hashKey);

  String changePasswordShipper(Object id, String oldPassword, String newPassword);

}
