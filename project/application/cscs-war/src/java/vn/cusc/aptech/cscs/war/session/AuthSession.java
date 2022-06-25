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
package vn.cusc.aptech.cscs.war.session;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import vn.cusc.aptech.cscs.ejb.beans.session.AuthSessionBeanLocal;
import vn.cusc.aptech.cscs.ejb.entities.Employee;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "authSession")
@SessionScoped
public class AuthSession implements Serializable {

  @EJB
  private AuthSessionBeanLocal auth;

  @Inject
  private ViewHelper viewHelper;

  private Employee account;

  @PostConstruct
  public void init() {
    account = null;
  }

  public void login(final String username, final String password) {
    account = auth.authenticateByLocalAccount(username, password);
  }

  public String logout() {
    account = null;
    return viewHelper.getPage("login");
  }

  public Employee getAccount() {
    return account;
  }

  public boolean isLoggedIn() {
    return account != null && account.getState();
  }

  public String getMessage() {
    if (account == null) {
      return "Username or password incorrect";
    }

    if (!account.getState()) {
      return "Account disabled";
    }

    return null;
  }

}
