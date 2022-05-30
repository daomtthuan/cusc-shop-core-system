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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entity.Bill;
import entity.Customer;
import vn.cusc.aptech.cscs.war.app.helpers.AuthApiHelper;
import vn.cusc.aptech.cscs.war.models.BillModel;

/**
 *
 * @author Daomtthuan
 */
@Path("customer/bill")
public class BillCustomerApi extends AuthApiHelper {

  public BillCustomerApi() {
    super();
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
      statusValue = Integer.valueOf(status);
      if (statusValue < 0 || statusValue > 4) {
        return sendResponse(Response.Status.BAD_REQUEST);
      }
    } catch (NumberFormatException e) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Customer account = authApiSessionBean.authenticateByCustomerLocalAccount(hashKey);
    ArrayList<BillModel> billModels = new ArrayList<>();
    for (Bill bill : account.getBillList()) {
      if (bill.getStatus() == statusValue) {
        billModels.add(new BillModel(bill));
      }
    }
    return sendResponse(Response.Status.OK, billModels);
  }

}
