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
package vn.cusc.aptech.cscs.war.apis.shipper;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import vn.cusc.aptech.cscs.ejb.beans.facades.BillFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Bill;
import vn.cusc.aptech.cscs.ejb.entities.Employee;
import vn.cusc.aptech.cscs.war.app.helpers.AuthApiHelper;
import vn.cusc.aptech.cscs.war.models.BillModel;
import vn.cusc.aptech.cscs.war.models.shipper.StatusModel;

/**
 *
 * @author Daomtthuan
 */
@Path("shipper/ship")
public class ShipShipperApi extends AuthApiHelper {

  private final BillFacadeLocal billFacade;

  public ShipShipperApi() {
    super();
    billFacade = lookupBillFacadeLocal();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@QueryParam("key") String hashKey, @QueryParam("status") String status) {
    if (isEmptyParam(hashKey) || isEmptyParam(status)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }
    int statusValue;
    try {
      statusValue = Integer.parseInt(status);
      if (statusValue < 2 || statusValue > 4) {
        return sendResponse(Response.Status.BAD_REQUEST);
      }
    } catch (NumberFormatException e) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Employee account = authApiSessionBean.authenticateByShipperLocalAccount(hashKey);
    if (account == null) {
      return sendResponse(Response.Status.UNAUTHORIZED);
    }

    ArrayList<BillModel> billModelses = new ArrayList<>();
    for (Bill bill : account.getShipBillList()) {
      if (bill.getStatus() == statusValue) {
        billModelses.add(new BillModel(bill));
      }
    }

    return sendResponse(Response.Status.OK, billModelses);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response put(@QueryParam("key") String hashKey, String body) {
    if (isEmptyParam(hashKey)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Employee account = authApiSessionBean.authenticateByShipperLocalAccount(hashKey);
    if (account == null) {
      return sendResponse(Response.Status.UNAUTHORIZED);
    }

    StatusModel statusModel = getBody(body, StatusModel.class);
    if (isEmptyBody(statusModel)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Bill bill = billFacade.find(statusModel.getIdBill());
    if (bill == null) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }
    bill.setStatus(statusModel.getStatus());
    if (statusModel.getStatus() == 4) {
      bill.setPayDate(new Date());
    }
    billFacade.edit(bill);

    return sendResponse(Response.Status.OK);
  }

  private BillFacadeLocal lookupBillFacadeLocal() {
    try {
      Context c = new InitialContext();
      return (BillFacadeLocal) c.lookup("java:global/application/cscs-ejb/BillFacade!vn.cusc.aptech.cscs.ejb.beans.facades.BillFacadeLocal");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
