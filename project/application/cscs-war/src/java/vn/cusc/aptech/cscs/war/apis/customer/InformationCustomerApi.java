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
import vn.cusc.aptech.cscs.ejb.beans.facades.InformationFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Customer;
import vn.cusc.aptech.cscs.ejb.entities.Information;
import vn.cusc.aptech.cscs.war.app.helpers.AuthApiHelper;
import vn.cusc.aptech.cscs.war.models.ChangeInformationModel;
import vn.cusc.aptech.cscs.war.models.InformationModel;

/**
 * `
 *
 * @author Daomtthuan
 */
@Path("customer/information")
public class InformationCustomerApi extends AuthApiHelper {

  private final InformationFacadeLocal informationFacade;

  public InformationCustomerApi() {
    super();
    informationFacade = lookupInformationFacadeLocal();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@QueryParam("key") String hashKey) {
    if (isEmptyParam(hashKey)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }
    Customer account = authApiSessionBean.authenticateByCustomerLocalAccount(hashKey);
    return account == null ? sendResponse(Response.Status.UNAUTHORIZED) : sendResponse(Response.Status.OK, new InformationModel(account.getInformation()));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response put(@QueryParam("key") String hashKey, String body) {
    if (isEmptyParam(hashKey)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }
    Customer account = authApiSessionBean.authenticateByCustomerLocalAccount(hashKey);
    ChangeInformationModel changeInformationModel = getBody(body, ChangeInformationModel.class);
    if (isEmptyBody(changeInformationModel)) {
      return sendResponse(Response.Status.BAD_REQUEST);
    }

    Information information = account.getInformation();
    information.setFullName(changeInformationModel.getFullName());
    information.setBirthday(dateHelper.dateOf(changeInformationModel.getBirthday().getYear(), changeInformationModel.getBirthday().getMonth(), changeInformationModel.getBirthday().getDay()));
    information.setGender(changeInformationModel.getGender());
    information.setEmail(changeInformationModel.getEmail());
    information.setAddress(changeInformationModel.getAddress());
    information.setPhone(changeInformationModel.getPhone());
    informationFacade.edit(information);
    return sendResponse(Response.Status.OK);
  }

  private InformationFacadeLocal lookupInformationFacadeLocal() {
    try {
      Context c = new InitialContext();
      return (InformationFacadeLocal) c.lookup("java:global/application/cscs-ejb/InformationFacade!vn.cusc.aptech.cscs.ejb.beans.facades.InformationFacadeLocal");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
