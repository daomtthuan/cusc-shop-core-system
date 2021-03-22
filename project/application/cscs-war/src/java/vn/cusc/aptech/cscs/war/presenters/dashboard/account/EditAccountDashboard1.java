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
package vn.cusc.aptech.cscs.war.presenters.dashboard.account;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cscs.ejb.beans.facades.InformationFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Employee;
import vn.cusc.aptech.cscs.ejb.entities.Information;
import vn.cusc.aptech.cscs.war.app.helpers.DateHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;
import vn.cusc.aptech.cscs.war.session.AuthSession;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "editAccountDashboard")
@ViewScoped
public class EditAccountDashboard1 implements Serializable {

  @EJB
  private InformationFacadeLocal informationFacade;

  @Inject
  private AuthSession authSession;

  @Inject
  private ViewHelper viewHelper;

  @Inject
  private DateHelper dateHelper;

  private String fullName;
  private boolean gender;
  private int dayBirthday;
  private int monthBirthday;
  private int yearBirthday;
  private LocalDate birthday;
  private String email;
  private String phone;
  private String address;

  private String fullNameInputStyleClass;
  private String birthdayInputStyleClass;
  private String emailInputStyleClass;
  private String phoneInputStyleClass;
  private String addressInputStyleClass;

  @PostConstruct
  public void init() {
    Information information = authSession.getAccount().getInformation();

    fullName = information.getFullName();
    gender = information.getGender();
    birthday = dateHelper.localDateOf(information.getBirthday());
    dayBirthday = birthday.getDayOfMonth();
    monthBirthday = birthday.getMonthValue();
    yearBirthday = birthday.getYear();
    email = information.getEmail();
    phone = information.getPhone();
    address = information.getAddress();

    fullNameInputStyleClass = null;
    birthdayInputStyleClass = null;
    emailInputStyleClass = null;
    phoneInputStyleClass = null;
    addressInputStyleClass = null;
  }

  public Employee getAccount() {
    return authSession.getAccount();
  }

  public String edit() {
    boolean fullNameValid = Pattern.matches(ValidationHelper.RegexPattern.NAME, fullName);
    boolean emailValid = Pattern.matches(ValidationHelper.RegexPattern.EMAIL, email);
    boolean phoneValid = Pattern.matches(ValidationHelper.RegexPattern.PHONE, phone);
    boolean addressValid = Pattern.matches(ValidationHelper.RegexPattern.ANY, address);
    boolean birthdayValid = true;

    fullNameInputStyleClass = fullNameValid ? null : ValidationHelper.StyleClass.INVALID;
    emailInputStyleClass = emailValid ? null : ValidationHelper.StyleClass.INVALID;
    phoneInputStyleClass = phoneValid ? null : ValidationHelper.StyleClass.INVALID;
    addressInputStyleClass = addressValid ? null : ValidationHelper.StyleClass.INVALID;
    try {
      birthday = dateHelper.localDateOf(yearBirthday, monthBirthday, dayBirthday);
    } catch (Exception e) {
      birthdayValid = false;
      birthdayInputStyleClass = ValidationHelper.StyleClass.INVALID;
    }

    if (!fullNameValid || !birthdayValid || !emailValid || !phoneValid || !addressValid) {
      return null;
    }

    Information information = authSession.getAccount().getInformation();
    information.setFullName(fullName);
    information.setGender(gender);
    information.setBirthday(dateHelper.dateOf(birthday));
    information.setEmail(email);
    information.setPhone(phone);
    information.setAddress(address);
    informationFacade.edit(information);

    return viewHelper.getPage(
      "dashboard/account/information");
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

}
