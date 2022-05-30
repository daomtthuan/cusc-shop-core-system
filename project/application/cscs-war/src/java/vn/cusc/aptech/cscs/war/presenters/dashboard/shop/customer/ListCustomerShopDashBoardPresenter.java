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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entity.facade.CustomerFacadeLocal;
import entity.facade.RoleFacadeLocal;
import entity.Customer;
import entity.Role;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author NGUYEN PHUC DUY
 */
@Named(value = "listCustomerShopDashBoardPresenter")
@ViewScoped
public class ListCustomerShopDashBoardPresenter implements Serializable {

  /**
   * Creates a new instance of ListCustomerDashBoardPresenter
   */
  public ListCustomerShopDashBoardPresenter() {
  }
  @EJB
  private RoleFacadeLocal roleFacade;

  @EJB
  private CustomerFacadeLocal customerFacade;

  @Inject
  private ViewHelper viewHelper;

  private int idRole;

  @PostConstruct
  public void init() {
    try {
      idRole = 0;
    } catch (Exception e) {
    }

  }

  public List<Customer> getCustomers() {
    return customerFacade.findAll();
  }

  public List<Role> getRoles() {
    return roleFacade.findCustomer();
  }

  public String removeAccount(int id) {
    customerFacade.remove(customerFacade.find(id));
    return viewHelper.getPage("dashboard/shop/customer/list");
  }

  public int getIdRole() {
    return idRole;
  }

  public void setIdRole(int idRole) {
    this.idRole = idRole;
  }

}
