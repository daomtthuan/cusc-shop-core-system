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
package vn.cusc.aptech.cscs.war.presenters.dashboard.accesssystem.account;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entity.facade.RoleFacadeLocal;
import bean.session.AuthSessionBeanLocal;
import entity.Role;
import vn.cusc.aptech.cscs.war.app.helpers.DateHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "addAccountAccesssystemDashboardPresenter")
@ViewScoped
public class AddAccountAccesssystemDashboardPresenter implements Serializable {

  @EJB
  private AuthSessionBeanLocal authSessionBean;

  @EJB
  private RoleFacadeLocal roleFacade;

  @Inject
  private ViewHelper viewHelper;

  @Inject
  private DateHelper dateHelper;

  private String username;
  private int idRole;
  private boolean state;
  private String fullName;
  private boolean gender;
  private int dayBirthday;
  private int monthBirthday;
  private int yearBirthday;
  private LocalDate birthday;
  private String email;
  private String phone;
  private String address;

  private String usernameInputStyleClass;
  private String roleInputStyleClass;
  private String fullNameInputStyleClass;
  private String birthdayInputStyleClass;
  private String emailInputStyleClass;
  private String phoneInputStyleClass;
  private String addressInputStyleClass;

  private String usernameFeedback;

  @PostConstruct
  public void init() {
    username = null;
    idRole = 0;
    state = true;
    fullName = null;
    gender = true;
    dayBirthday = 0;
    monthBirthday = 0;
    yearBirthday = 0;
    birthday = null;
    email = null;
    phone = null;
    address = null;

    usernameInputStyleClass = null;
    roleInputStyleClass = null;
    fullNameInputStyleClass = null;
    birthdayInputStyleClass = null;
    emailInputStyleClass = null;
    phoneInputStyleClass = null;
    addressInputStyleClass = null;
  }

  public String add() {
    boolean usernameValid = Pattern.matches(ValidationHelper.RegexPattern.USERNAME, username);
    boolean roleValid = idRole != 0;
    boolean fullNameValid = Pattern.matches(ValidationHelper.RegexPattern.NAME, fullName);
    boolean emailValid = Pattern.matches(ValidationHelper.RegexPattern.EMAIL, email);
    boolean phoneValid = Pattern.matches(ValidationHelper.RegexPattern.PHONE, phone);
    boolean addressValid = Pattern.matches(ValidationHelper.RegexPattern.ANY, address);
    boolean birthdayValid = true;

    usernameFeedback = usernameValid ? null : "Invalid username";
    usernameInputStyleClass = usernameValid ? null : ValidationHelper.StyleClass.INVALID;
    roleInputStyleClass = roleValid ? null : ValidationHelper.StyleClass.INVALID;
    fullNameInputStyleClass = fullNameValid ? null : ValidationHelper.StyleClass.INVALID;
    emailInputStyleClass = emailValid ? null : ValidationHelper.StyleClass.INVALID;
    phoneInputStyleClass = phoneValid ? null : ValidationHelper.StyleClass.INVALID;
    addressInputStyleClass = addressValid ? null : ValidationHelper.StyleClass.INVALID;
    try {
      birthday = dateHelper.localDateOf(yearBirthday, monthBirthday, dayBirthday);
      birthdayInputStyleClass = null;
    } catch (Exception e) {
      birthdayValid = false;
      birthdayInputStyleClass = ValidationHelper.StyleClass.INVALID;
    }

    if (!usernameValid || !roleValid || !fullNameValid || !birthdayValid || !emailValid || !phoneValid || !addressValid) {
      return null;
    }

    usernameFeedback = authSessionBean.createAccount(username, idRole, state, fullName, dateHelper.dateOf(birthday), gender, email, phone, address);
    if (usernameFeedback != null) {
      usernameInputStyleClass = ValidationHelper.StyleClass.INVALID;
      return null;
    }

    return viewHelper.getPage("dashboard/access-system/account/list");
  }

  public List<Role> getRoles() {
    return roleFacade.findNotAdministrator();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getIdRole() {
    return idRole;
  }

  public void setIdRole(int idRole) {
    this.idRole = idRole;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public boolean isGender() {
    return gender;
  }

  public void setGender(boolean gender) {
    this.gender = gender;
  }

  public int getDayBirthday() {
    return dayBirthday;
  }

  public void setDayBirthday(int dayBirthday) {
    this.dayBirthday = dayBirthday;
  }

  public int getMonthBirthday() {
    return monthBirthday;
  }

  public void setMonthBirthday(int monthBirthday) {
    this.monthBirthday = monthBirthday;
  }

  public int getYearBirthday() {
    return yearBirthday;
  }

  public void setYearBirthday(int yearBirthday) {
    this.yearBirthday = yearBirthday;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getFullNameInputStyleClass() {
    return fullNameInputStyleClass;
  }

  public void setFullNameInputStyleClass(String fullNameInputStyleClass) {
    this.fullNameInputStyleClass = fullNameInputStyleClass;
  }

  public String getBirthdayInputStyleClass() {
    return birthdayInputStyleClass;
  }

  public void setBirthdayInputStyleClass(String birthdayInputStyleClass) {
    this.birthdayInputStyleClass = birthdayInputStyleClass;
  }

  public String getEmailInputStyleClass() {
    return emailInputStyleClass;
  }

  public void setEmailInputStyleClass(String emailInputStyleClass) {
    this.emailInputStyleClass = emailInputStyleClass;
  }

  public String getPhoneInputStyleClass() {
    return phoneInputStyleClass;
  }

  public void setPhoneInputStyleClass(String phoneInputStyleClass) {
    this.phoneInputStyleClass = phoneInputStyleClass;
  }

  public String getAddressInputStyleClass() {
    return addressInputStyleClass;
  }

  public void setAddressInputStyleClass(String addressInputStyleClass) {
    this.addressInputStyleClass = addressInputStyleClass;
  }

  public String getUsernameInputStyleClass() {
    return usernameInputStyleClass;
  }

  public void setUsernameInputStyleClass(String usernameInputStyleClass) {
    this.usernameInputStyleClass = usernameInputStyleClass;
  }

  public String getRoleInputStyleClass() {
    return roleInputStyleClass;
  }

  public void setRoleInputStyleClass(String roleInputStyleClass) {
    this.roleInputStyleClass = roleInputStyleClass;
  }

  public String getUsernameFeedback() {
    return usernameFeedback;
  }

  public void setUsernameFeedback(String usernameFeedback) {
    this.usernameFeedback = usernameFeedback;
  }

}
