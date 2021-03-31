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
package vn.cusc.aptech.cscs.war.models.customer;

import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.war.models.InformationModel;
import vn.cusc.aptech.cscs.war.models.Model;

/**
 *
 * @author Daomtthuan
 */
public class CustomerModel implements Model {

  private int id;
  private String username;
  private String state;
  private InformationModel information;

  public CustomerModel() {
  }

  public CustomerModel(Customer customer) {
    id = customer.getId();
    username = customer.getUsername();
    state = customer.getState() ? "Enabled" : "Disabled";
    information = new InformationModel(customer.getInformation());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public InformationModel getInformation() {
    return information;
  }

  public void setInformation(InformationModel information) {
    this.information = information;
  }

  @Override
  public boolean isEmpty() {
    return id == 0
      || username == null || username.isEmpty()
      || information == null || information.isEmpty()
      || state == null || state.isEmpty();
  }

}
