/*
 * The MIT License
 *
 * Copyright 2021 Daomtthuan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package vn.cusc.aptech.cscs.ejb.beans.session;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.mindrot.jbcrypt.BCrypt;
import vn.cusc.aptech.cscs.ejb.beans.facades.CustomerFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.EmployeeFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.InformationFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.RoleFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Account;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.ejb.entities.Employee;
import vn.cusc.aptech.cscs.ejb.entities.Information;
import vn.cusc.aptech.cscs.ejb.helpers.KeyHelper;

/**
 *
 * @author Daomtthuan
 */
@Stateless
public class AuthSessionBean implements AuthSessionBeanLocal {

  @EJB
  private CustomerFacadeLocal customerFacade;

  @EJB
  private RoleFacadeLocal roleFacade;

  @EJB
  private InformationFacadeLocal informationFacade;

  @EJB
  private EmployeeFacadeLocal employeeFacade;

  private boolean authenticate(Account account, String password) {
    if (account != null) {
      if (BCrypt.checkpw(password, account.getPassword())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Employee authenticateByEmployeeLocalAccount(String username, String password) {
    Employee account = employeeFacade.findByUsername(username);
    return authenticate(account, password) ? account : null;
  }

  @Override
  public String authenticateByCustomerLocalAccount(String username, String password) {
    Customer account = customerFacade.findByUsername(username);
    if (!authenticate(account, password)) {
      return null;
    }

    String key = account.getId() + " " + BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
    try {
      return KeyHelper.getInstance().encrypt(key);
    } catch (Exception ex) {
      return null;
    }
  }

  @Override
  public Customer authenticateByCustomerLocalAccount(String hashKey) {
    String key;
    try {
      key = KeyHelper.getInstance().decrypt(hashKey);
    } catch (Exception ex) {
      Logger.getLogger(AuthSessionBean.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }

    String[] keyParts = key.split(" ");
    String id = keyParts[0];
    String hashOfHashPassword = keyParts[1];

    Customer account = customerFacade.find(Integer.valueOf(id));
    if (account == null) {
      return null;
    }
    return BCrypt.checkpw(account.getPassword(), hashOfHashPassword) ? account : null;
  }

  @Override
  public String changePassword(Object id, String oldPassword, String newPassword) {
    Employee account = employeeFacade.find(id);
    if (account == null) {
      return "Account not found";
    }

    if (!authenticate(account, oldPassword)) {
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
