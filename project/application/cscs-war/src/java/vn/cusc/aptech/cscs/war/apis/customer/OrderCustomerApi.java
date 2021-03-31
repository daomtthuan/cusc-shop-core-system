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
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import vn.cusc.aptech.cscs.ejb.beans.facades.ProductFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.session.api.BillApiSessionBeanLocal;
import vn.cusc.aptech.cscs.ejb.entities.Bill;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.ejb.entities.Product;
import vn.cusc.aptech.cscs.war.app.helpers.ApiHelper;
import vn.cusc.aptech.cscs.war.models.BillModel;
import vn.cusc.aptech.cscs.war.models.customer.CartDetailsModel;
import vn.cusc.aptech.cscs.war.models.customer.CartModel;

/**
 *
 * @author Daomtthuan
 */
@Path("customer/order")
public class OrderCustomerApi extends ApiHelper {

  private final ProductFacadeLocal productFacade;
  private final BillApiSessionBeanLocal billApiSessionBean;

  public OrderCustomerApi() {
    super();
    try {
      productFacade = (ProductFacadeLocal) context.lookup("java:global/application/cscs-ejb/ProductFacade!vn.cusc.aptech.cscs.ejb.beans.facades.ProductFacadeLocal");
      billApiSessionBean = (BillApiSessionBeanLocal) context.lookup("java:global/application/cscs-ejb/BillApiSessionBean!vn.cusc.aptech.cscs.ejb.beans.session.api.BillApiSessionBeanLocal");
    } catch (NamingException e) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
      throw new RuntimeException(e);
    }
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
    if (isEmptyBody(cartModel)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    ArrayList<Pair<Product, Integer>> cart = new ArrayList<>();
    for (CartDetailsModel cartDetailsModel : cartModel) {
      Product product = productFacade.find(cartDetailsModel.getIdProduct());
      if (product == null) {
        return sendResponse(Response.Status.BAD_REQUEST);
      }
      cart.add(new Pair<>(product, cartDetailsModel.getQuantity()));
    }
    Bill bill = billApiSessionBean.addBill(account, cart);
    if (bill == null) {
      return sendResponse(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return sendResponse(Response.Status.OK, new BillModel(bill));
  }

}
