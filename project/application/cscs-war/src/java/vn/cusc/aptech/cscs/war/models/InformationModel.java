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
package vn.cusc.aptech.cscs.war.models;

import javax.inject.Inject;
import vn.cusc.aptech.cscs.ejb.entities.Information;
import vn.cusc.aptech.cscs.war.app.helpers.DateHelper;

/**
 *
 * @author Daomtthuan
 */
public class InformationModel implements Model {

  @Inject
  private DateHelper dateHelper;

  private String fullName;
  private String birthday;
  private boolean gender;
  private String email;
  private String address;
  private String phone;

  public InformationModel() {
  }

  public InformationModel(Information information) {
    this.fullName = information.getFullName();
    this.birthday = dateHelper.localDateOf(information.getBirthday()).toString();
    this.gender = information.getGender();
    this.email = information.getEmail();
    this.address = information.getAddress();
    this.phone = information.getPhone();
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public boolean isGender() {
    return gender;
  }

  public void setGender(boolean gender) {
    this.gender = gender;
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

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public boolean isEmpty() {
    return fullName == null || fullName.isEmpty()
      || birthday == null || birthday.isEmpty()
      || email == null || email.isEmpty()
      || address == null || address.isEmpty()
      || phone == null || phone.isEmpty();
  }

}
