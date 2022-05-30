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
package bean.session;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.mindrot.jbcrypt.BCrypt;
import entity.facade.CustomerFacadeLocal;
import entity.facade.InformationFacadeLocal;
import entity.Customer;
import entity.Information;

/**
 *
 * @author Daomtthuan
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanLocal {

  @EJB
  private InformationFacadeLocal informationFacade;

  @EJB
  private CustomerFacadeLocal customerFacade;

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public String creatCustomer(String username, boolean state, String fullName, Date birthday, boolean gender, String email, String phone, String address) {
    if (customerFacade.findByUsername(username) != null) {
      return "Username already exists";
    }
    Information information = new Information(null, fullName, birthday, gender, email, address, phone);
    informationFacade.create(information);
    String hashPassword = BCrypt.hashpw("1234", BCrypt.gensalt());
    Customer customer = new Customer(null, username, hashPassword, state);
    customer.setInformation(information);
    customerFacade.create(customer);
    return null;
  }

}
