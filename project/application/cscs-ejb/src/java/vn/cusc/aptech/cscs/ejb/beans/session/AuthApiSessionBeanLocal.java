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

import javax.ejb.Local;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.ejb.entities.Employee;

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
