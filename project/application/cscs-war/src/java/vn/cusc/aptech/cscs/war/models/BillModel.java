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

import java.util.Date;
import vn.cusc.aptech.cscs.ejb.entities.Bill;
import vn.cusc.aptech.cscs.ejb.entities.Employee;
import vn.cusc.aptech.cscs.war.app.helpers.DateHelper;

/**
 *
 * @author Daomtthuan
 */
public class BillModel implements Model {

  private int id;
  private String customer;
  private String salesman;
  private String shipper;
  private String createDate;
  private String payDate;
  private String status;
  private String state;

  private static DateHelper dateHelper = new DateHelper();
  private static String[] statuses = {"Canceled", "Pending", "Accepted", "Shipping", "Paid"};

  public BillModel() {
  }

  public BillModel(Bill bill) {
    Employee salesmanBill = bill.getSalesman();
    Employee shipperBill = bill.getShipper();
    Date payDateBill = bill.getPayDate();

    id = bill.getId();
    customer = bill.getCustomer().getInformation().getFullName();
    salesman = salesmanBill != null ? salesmanBill.getInformation().getFullName() : null;
    shipper = shipperBill != null ? shipperBill.getInformation().getFullName() : null;
    createDate = dateHelper.stringDateTimeOf(bill.getCreateDate());
    payDate = payDateBill != null ? dateHelper.stringDateTimeOf(payDateBill) : null;
    status = statuses[bill.getStatus()];
    state = bill.getState() ? "Enabled" : "Disabled";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getSalesman() {
    return salesman;
  }

  public void setSalesman(String salesman) {
    this.salesman = salesman;
  }

  public String getShipper() {
    return shipper;
  }

  public void setShipper(String shipper) {
    this.shipper = shipper;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getPayDate() {
    return payDate;
  }

  public void setPayDate(String payDate) {
    this.payDate = payDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public boolean isEmpty() {
    return id == 0
      || customer == null || customer.isEmpty()
      || createDate == null || createDate.isEmpty()
      || customer == null || customer.isEmpty()
      || status == null || status.isEmpty()
      || state == null || state.isEmpty();
  }

}
