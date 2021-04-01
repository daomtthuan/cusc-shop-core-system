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

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.war.app.helpers.ApiHelper;
import vn.cusc.aptech.cscs.war.models.AuthModel;
import vn.cusc.aptech.cscs.war.models.ChangePasswordModel;
import vn.cusc.aptech.cscs.war.models.ErrorModel;
import vn.cusc.aptech.cscs.war.models.KeyAuthModel;
import vn.cusc.aptech.cscs.war.models.customer.CustomerModel;

/**
 *
 * @author Daomtthuan
 */
@Path("customer/auth")
public class AuthCustomerApi extends ApiHelper {

  public AuthCustomerApi() {
    super();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@QueryParam("key") String hashKey) {
    if (isEmptyParam(hashKey)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Customer account = authApiSessionBean.authenticateByCustomerLocalAccount(hashKey);
    return account == null ? sendResponse(Response.Status.UNAUTHORIZED) : sendResponse(Response.Status.OK, new CustomerModel(account));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(String body) {
    AuthModel authModel = getBody(body, AuthModel.class);
    if (isEmptyBody(authModel)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    String key = authApiSessionBean.authenticateByCustomerLocalAccount(authModel.getUsername(), authModel.getPassword());
    return key == null ? sendResponse(Response.Status.UNAUTHORIZED) : sendResponse(Response.Status.OK, new KeyAuthModel(key));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response put(@QueryParam("key") String hashKey, String body) {
    if (isEmptyParam(hashKey)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    ChangePasswordModel changePasswordModel = getBody(body, ChangePasswordModel.class);
    if (isEmptyBody(changePasswordModel)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Customer account = authApiSessionBean.authenticateByCustomerLocalAccount(hashKey);
    if (account == null) {
      return sendResponse(Response.Status.UNAUTHORIZED);
    }
    String error = authApiSessionBean.changePasswordCustomer(account.getId(), changePasswordModel.getOldPassword(), changePasswordModel.getNewPassword());
    if (error == null) {
      return sendResponse(Response.Status.OK, new KeyAuthModel(authApiSessionBean.authenticateByCustomerLocalAccount(account.getUsername(), changePasswordModel.getNewPassword())));
    }
    return sendResponse(Response.Status.UNAUTHORIZED, new ErrorModel(error));
  }

}
