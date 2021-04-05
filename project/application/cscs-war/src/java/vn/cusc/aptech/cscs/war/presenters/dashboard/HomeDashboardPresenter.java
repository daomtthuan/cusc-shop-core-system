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
package vn.cusc.aptech.cscs.war.presenters.dashboard;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import vn.cusc.aptech.cscs.ejb.beans.facades.BillFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.TopTenCustomerFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.TopTenProductFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.TopTenCustomer;
import vn.cusc.aptech.cscs.ejb.entities.TopTenProduct;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "homeDashboardPresenter")
@ViewScoped
public class HomeDashboardPresenter implements Serializable {

  @EJB
  private BillFacadeLocal billFacade;

  @EJB
  private TopTenCustomerFacadeLocal topTenCustomerFacade;

  @EJB
  private TopTenProductFacadeLocal topTenProductFacade;

  private List<TopTenProduct> topTenProducts;
  private List<TopTenCustomer> topTenCustomers;
  private int pendingBillQuantity;
  private int notify;

  @PostConstruct
  public void init() {
    topTenProducts = topTenProductFacade.findAll();
    topTenCustomers = topTenCustomerFacade.findAll();
    pendingBillQuantity = billFacade.findByStatus(1).size();
    this.notify = pendingBillQuantity;
  }

  public List<TopTenProduct> getTopTenProducts() {
    return topTenProducts;
  }

  public void setTopTenProducts(List<TopTenProduct> topTenProducts) {
    this.topTenProducts = topTenProducts;
  }

  public List<TopTenCustomer> getTopTenCustomers() {
    return topTenCustomers;
  }

  public void setTopTenCustomers(List<TopTenCustomer> topTenCustomers) {
    this.topTenCustomers = topTenCustomers;
  }

  public int getPendingBillQuantity() {
    return pendingBillQuantity;
  }

  public void setPendingBillQuantity(int pendingBillQuantity) {
    this.pendingBillQuantity = pendingBillQuantity;
  }

  public int getNotify() {
    return notify;
  }

  public void setNotify(int notify) {
    this.notify = notify;
  }

}
