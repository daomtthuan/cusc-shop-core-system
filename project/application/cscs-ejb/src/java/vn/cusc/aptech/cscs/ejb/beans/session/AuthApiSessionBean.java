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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;
import vn.cusc.aptech.cscs.ejb.beans.facades.CustomerFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.EmployeeFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Account;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.ejb.entities.Employee;
import vn.cusc.aptech.cscs.ejb.helpers.KeyHelper;

/**
 *
 * @author Daomtthuan
 */
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
    return KeyHelper.encode(account.getUsername() + ":" + BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
  }

  private String[] analyzeHashKey(String hashKey) {
    return KeyHelper.decode(hashKey).split(":");
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
