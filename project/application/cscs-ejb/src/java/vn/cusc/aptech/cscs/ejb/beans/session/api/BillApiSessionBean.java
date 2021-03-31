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
package vn.cusc.aptech.cscs.ejb.beans.session.api;

import java.util.ArrayList;
import java.util.Date;
import javafx.util.Pair;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import vn.cusc.aptech.cscs.ejb.beans.facades.BillDetailsFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.BillFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.ProductFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Bill;
import vn.cusc.aptech.cscs.ejb.entities.BillDetails;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.ejb.entities.Product;

/**
 *
 * @author Daomtthuan
 */
@Stateless
public class BillApiSessionBean implements BillApiSessionBeanLocal {

  @EJB
  private ProductFacadeLocal productFacade;

  @EJB
  private BillFacadeLocal billFacade;

  @EJB
  private BillDetailsFacadeLocal billDetailsFacade;

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Bill addBill(Customer customer, ArrayList<Pair<Product, Integer>> cart) {
    Bill bill = new Bill(null, new Date(), 1, true);
    bill.setCustomer(customer);
    billFacade.create(bill);

    for (Pair<Product, Integer> cartDetails : cart) {
      Product product = cartDetails.getKey();
      int quantity = cartDetails.getValue();

      BillDetails billDetails = new BillDetails(null, product.getPrice(), quantity);
      billDetails.setBill(bill);
      billDetails.setProduct(product);
      billDetailsFacade.create(billDetails);
    }

    return bill;

  }

}
