/*
 * The MIT License
 *
 * Copyright 2021 NGUYEN PHUC DUY.
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
package vn.cusc.aptech.cscs.war.presenters.dashboard.shop.customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import vn.cusc.aptech.cscs.ejb.beans.facades.CustomerFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.InformationFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.RoleFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.session.AuthSessionBeanLocal;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.ejb.entities.Role;
import vn.cusc.aptech.cscs.war.app.helpers.DateHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author NGUYEN PHUC DUY
 */
@Named(value = "addCustomerShopDashBoardPresenter")
@Dependent
public class AddCustomerShopDashBoardPresenter implements Serializable {

  /**
   * Creates a new instance of AddCustomerDashBoardPresenter
   */
  public AddCustomerShopDashBoardPresenter() {
  }
  @EJB
  private AuthSessionBeanLocal authSessionBean;
  @EJB
  private RoleFacadeLocal roleFacade;
  @EJB
  private CustomerFacadeLocal customerFacade;

  @EJB
  private InformationFacadeLocal informationFacade;

  @Inject
  private ViewHelper viewHelper;

  private int idInformation;
  @Inject
  private DateHelper dateHelper;
  private int idRole;
  private boolean state;
  private String fullName;
  private boolean gender;
  private int dayBirthday;
  private int monthBirthday;
  private int yearBirthday;
  private LocalDate birthday;
  private String usernameFeedback;
  private String email;
  private String phone;
  private String address;
  private String roleInputStyleClass;
  private String fullNameInputStyleClass;
  private String birthdayInputStyleClass;
  private String emailInputStyleClass;
  private String phoneInputStyleClass;
  private String addressInputStyleClass;
  private String username;
  private String password;

  private String informationInputStyleClass;
  private String usernameInputStyleClass;
  private String passwordInputStyleClass;

  @PostConstruct
  public void init() {
    try {
      idInformation = 0;
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
      roleInputStyleClass = null;
      fullNameInputStyleClass = null;
      birthdayInputStyleClass = null;
      emailInputStyleClass = null;
      phoneInputStyleClass = null;
      addressInputStyleClass = null;
      username = null;
      password = null;
      informationInputStyleClass = null;
      usernameInputStyleClass = null;
      passwordInputStyleClass = null;
    } catch (NumberFormatException e) {
      viewHelper.redirect("errors/404");
    }

  }

  public InformationFacadeLocal getInformationFacade() {
    return informationFacade;
  }

  public void setInformationFacade(InformationFacadeLocal informationFacade) {
    this.informationFacade = informationFacade;
  }

  public int getIdInformation() {
    return idInformation;
  }

  public void setIdInformation(int idInformation) {
    this.idInformation = idInformation;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public String getInformationInputStyleClass() {
    return informationInputStyleClass;
  }

  public void setInformationInputStyleClass(String informationInputStyleClass) {
    this.informationInputStyleClass = informationInputStyleClass;
  }

  public String getUsernameInputStyleClass() {
    return usernameInputStyleClass;
  }

  public void setUsernameInputStyleClass(String usernameInputStyleClass) {
    this.usernameInputStyleClass = usernameInputStyleClass;
  }

  public String getPasswordInputStyleClass() {
    return passwordInputStyleClass;
  }

  public void setPasswordInputStyleClass(String passwordInputStyleClass) {
    this.passwordInputStyleClass = passwordInputStyleClass;
  }

  public CustomerFacadeLocal getCustomerFacade() {
    return customerFacade;
  }

  public void setCustomerFacade(CustomerFacadeLocal customerFacade) {
    this.customerFacade = customerFacade;
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

  public String getAddress() {
    return address;
  }

  public AuthSessionBeanLocal getAuthSessionBean() {
    return authSessionBean;
  }

  public void setAuthSessionBean(AuthSessionBeanLocal authSessionBean) {
    this.authSessionBean = authSessionBean;
  }

  public int getIdRole() {
    return idRole;
  }

  public void setIdRole(int idRole) {
    this.idRole = idRole;
  }

  public String getUsernameFeedback() {
    return usernameFeedback;
  }

  public void setUsernameFeedback(String usernameFeedback) {
    this.usernameFeedback = usernameFeedback;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRoleInputStyleClass() {
    return roleInputStyleClass;
  }

  public void setRoleInputStyleClass(String roleInputStyleClass) {
    this.roleInputStyleClass = roleInputStyleClass;
  }

  public String getPhoneInputStyleClass() {
    return phoneInputStyleClass;
  }

  public void setPhoneInputStyleClass(String phoneInputStyleClass) {
    this.phoneInputStyleClass = phoneInputStyleClass;
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

  public String getAddressInputStyleClass() {
    return addressInputStyleClass;
  }

  public void setAddressInputStyleClass(String addressInputStyleClass) {
    this.addressInputStyleClass = addressInputStyleClass;
  }

  public String add() {

    boolean usernameValid = Pattern.matches(ValidationHelper.RegexPattern.ANY_NAME, username);
    boolean passwordValid = Pattern.matches(ValidationHelper.RegexPattern.ANY_NAME, password);
    boolean fullNameValid = Pattern.matches(ValidationHelper.RegexPattern.NAME, fullName);
    boolean emailValid = Pattern.matches(ValidationHelper.RegexPattern.EMAIL, email);
    boolean phoneValid = Pattern.matches(ValidationHelper.RegexPattern.PHONE, phone);
    boolean addressValid = Pattern.matches(ValidationHelper.RegexPattern.ANY, address);
    boolean roleValid = idRole != 0;
    boolean birthdayValid = true;
    usernameFeedback = usernameValid ? null : "Invalid username";
    roleInputStyleClass = roleValid ? null : ValidationHelper.StyleClass.INVALID;
    usernameInputStyleClass = usernameValid ? null : ValidationHelper.StyleClass.INVALID;
    fullNameInputStyleClass = fullNameValid ? null : ValidationHelper.StyleClass.INVALID;
    emailInputStyleClass = emailValid ? null : ValidationHelper.StyleClass.INVALID;
    phoneInputStyleClass = phoneValid ? null : ValidationHelper.StyleClass.INVALID;
    addressInputStyleClass = addressValid ? null : ValidationHelper.StyleClass.INVALID;
    boolean informationValid = idInformation != 0;

    informationInputStyleClass = informationValid ? null : ValidationHelper.StyleClass.INVALID;
    try {
      birthday = dateHelper.localDateOf(yearBirthday, monthBirthday, dayBirthday);
    } catch (Exception e) {
      birthdayValid = false;
      birthdayInputStyleClass = ValidationHelper.StyleClass.INVALID;
    }
    if (!usernameValid || !passwordValid || !informationValid || !roleValid || !fullNameValid || !birthdayValid || !emailValid || !phoneValid || !addressValid) {
      return null;
    }
    usernameFeedback = authSessionBean.createAccount(username, idRole, state, fullName, dateHelper.dateOf(birthday), gender, email, phone, address);
    //Customer customer = new Customer(null, username, password, state);
    //customer.setInformation(informationFacade.find(idInformation));

    //customerFacade.create(customer);
    if (usernameFeedback != null) {
      usernameInputStyleClass = ValidationHelper.StyleClass.INVALID;
      return null;
    }
    return viewHelper.getPage("dashboard/shop/customer/list");
  }

  public List<Customer> getCustomers() {
    return customerFacade.findAll();
  }

  public List<Role> getRoles() {
    return roleFacade.findCustomer();
  }

}
