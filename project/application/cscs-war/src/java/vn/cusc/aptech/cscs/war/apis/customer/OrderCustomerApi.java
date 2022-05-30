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
package vn.cusc.aptech.cscs.war.apis.customer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entity.facade.ProductFacadeLocal;
import bean.session.api.BillApiSessionBeanLocal;
import entity.Bill;
import entity.Customer;
import entity.Product;
import vn.cusc.aptech.cscs.war.app.helpers.AuthApiHelper;
import vn.cusc.aptech.cscs.war.models.customer.CartDetailsModel;
import vn.cusc.aptech.cscs.war.models.customer.CartModel;

/**
 *
 * @author Daomtthuan
 */
@Path("customer/order")
public class OrderCustomerApi extends AuthApiHelper {

  private final ProductFacadeLocal productFacade;
  private final BillApiSessionBeanLocal billApiSessionBean;

  public OrderCustomerApi() {
    super();
    productFacade = lookupProductFacadeLocal();
    billApiSessionBean = lookupBillApiSessionBeanLocal();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(@QueryParam("key") String hashKey, String body) {
    if (isEmptyParam(hashKey)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Customer account = authApiSessionBean.authenticateByCustomerLocalAccount(hashKey);
    if (account == null) {
      return sendResponse(Response.Status.UNAUTHORIZED);
    }

    CartModel cartModel = getBody(body, CartModel.class);
    System.out.println(body);
    if (isEmptyBody(cartModel)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    ArrayList<Pair<Integer, Integer>> cart = new ArrayList<>();
    for (CartDetailsModel cartDetailsModel : cartModel) {
      Product product = productFacade.find(cartDetailsModel.getIdProduct());
      if (product == null || product.getQuantity() < cartDetailsModel.getQuantity()) {
        return sendResponse(Response.Status.BAD_REQUEST);
      }
      cart.add(new Pair<>(product.getId(), cartDetailsModel.getQuantity()));
    }
    Bill bill = billApiSessionBean.addBill(account, cart);
    if (bill == null) {
      return sendResponse(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return sendResponse(Response.Status.OK);
  }

  private ProductFacadeLocal lookupProductFacadeLocal() {
    try {
      Context c = new InitialContext();
      return (ProductFacadeLocal) c.lookup("java:global/application/cscs-ejb/ProductFacade!vn.cusc.aptech.cscs.ejb.beans.facades.ProductFacadeLocal");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

  private BillApiSessionBeanLocal lookupBillApiSessionBeanLocal() {
    try {
      Context c = new InitialContext();
      return (BillApiSessionBeanLocal) c.lookup("java:global/application/cscs-ejb/BillApiSessionBean!vn.cusc.aptech.cscs.ejb.beans.session.api.BillApiSessionBeanLocal");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
