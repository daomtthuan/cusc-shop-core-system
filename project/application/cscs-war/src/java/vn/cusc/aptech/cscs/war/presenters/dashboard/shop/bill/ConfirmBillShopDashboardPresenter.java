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
package vn.cusc.aptech.cscs.war.presenters.dashboard.shop.bill;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import vn.cusc.aptech.cscs.ejb.beans.facades.BillFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.EmployeeFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Bill;
import vn.cusc.aptech.cscs.ejb.entities.BillDetails;
import vn.cusc.aptech.cscs.ejb.entities.Employee;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;
import vn.cusc.aptech.cscs.war.session.AuthSession;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "confirmBillShopDashboardPresenter")
@ViewScoped
public class ConfirmBillShopDashboardPresenter implements Serializable {

  @Inject
  private AuthSession authSession;

  @EJB
  private EmployeeFacadeLocal employeeFacade;

  @EJB
  private BillFacadeLocal billFacade;

  @Inject
  private ViewHelper viewHelper;

  private Bill bill;
  private int idShipper;
  private String shipperInputStyleClass;

  @PostConstruct
  public void init() {
    try {
      bill = billFacade.find(Integer.valueOf(viewHelper.getParameters().get("id")));
    } catch (NumberFormatException e) {
      viewHelper.redirect("errors/404");
    }
  }

  public List<Employee> getShippers() {
    return employeeFacade.findAllShipper();
  }

  public double getTotalPrice() {
    double totalPrice = 0;
    for (BillDetails billDetails : bill.getBillDetailsList()) {
      totalPrice += billDetails.getPrice() * billDetails.getQuantity();
    }
    return totalPrice;
  }

  public String accept() {
    boolean shipperValid = idShipper != 0;

    shipperInputStyleClass = shipperValid ? null : ValidationHelper.StyleClass.INVALID;
    if (!shipperValid) {
      return null;
    }
    bill.setShipper(employeeFacade.find(idShipper));
    bill.setStatus(2);
    bill.setSalesman(authSession.getAccount());
    billFacade.edit(bill);

    return viewHelper.getPage("dashboard/shop/bill/list");
  }

  public String cancel() {
    bill.setStatus(0);
    bill.setSalesman(authSession.getAccount());
    billFacade.edit(bill);

    return viewHelper.getPage("dashboard/shop/bill/list");
  }

  public Bill getBill() {
    return bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  public int getIdShipper() {
    return idShipper;
  }

  public void setIdShipper(int idShipper) {
    this.idShipper = idShipper;
  }

  public String getShipperInputStyleClass() {
    return shipperInputStyleClass;
  }

  public void setShipperInputStyleClass(String shipperInputStyleClass) {
    this.shipperInputStyleClass = shipperInputStyleClass;
  }

}
