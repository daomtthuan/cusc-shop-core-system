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
package vn.cusc.aptech.cscs.war.presenters;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;
import vn.cusc.aptech.cscs.war.session.AuthSession;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "loginPresenter")
@ViewScoped
public class LoginPresenter implements Serializable {

  @Inject
  private ViewHelper viewHelper;
  @Inject
  private AuthSession authSession;

  private String username;
  private String usernameInputStyleClass;

  private String password;
  private String passwordInputStyleClass;

  private String authMessage;

  @PostConstruct
  public void init() {
    username = null;
    usernameInputStyleClass = null;

    password = null;
    passwordInputStyleClass = null;

    authMessage = null;
  }

  public String login() {
    boolean usernameValid = Pattern.matches(ValidationHelper.RegexPattern.USERNAME, username);
    boolean passwordValid = Pattern.matches(ValidationHelper.RegexPattern.PASSWORD, password);

    usernameInputStyleClass = usernameValid ? null : ValidationHelper.StyleClass.INVALID;
    passwordInputStyleClass = passwordValid ? null : ValidationHelper.StyleClass.INVALID;

    if (!usernameValid || !passwordValid) {
      authMessage = null;
      return null;
    }

    authSession.login(username, password);
    if (authSession.isLoggedIn()) {
      authMessage = null;
      return viewHelper.getPage("dashboard/home");
    } else {
      authMessage = authSession.getMessage();
      return null;
    }
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsernameInputStyleClass() {
    return usernameInputStyleClass;
  }

  public void setUsernameInputStyleClass(String usernameInputStyleClass) {
    this.usernameInputStyleClass = usernameInputStyleClass;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordInputStyleClass() {
    return passwordInputStyleClass;
  }

  public void setPasswordInputStyleClass(String passwordInputStyleClass) {
    this.passwordInputStyleClass = passwordInputStyleClass;
  }

  public String getAuthMessage() {
    return authMessage;
  }

  public void setAuthMessage(String authMessage) {
    this.authMessage = authMessage;
  }

}
