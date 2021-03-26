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
package vn.cusc.aptech.cscs.war.apis;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import vn.cusc.aptech.cscs.ejb.beans.facades.CustomerFacadeLocal;

/**
 * REST Web Service
 *
 * @author Daomtthuan
 */
@Path("auth")
public class AuthApi {

  CustomerFacadeLocal customerFacade = lookupCustomerFacadeLocal();

  @Context
  private UriInfo context;

  /*
  @GET
  @Path("retrieve/{uuid}")
  public Response retrieveSomething(@PathParam("uuid") String uuid) {
      if(uuid == null || uuid.trim().length() == 0) {
          return Response.serverError().entity("UUID cannot be blank").build();
      }
      Entity entity = service.getById(uuid);
      if(entity == null) {
          return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + uuid).build();
      }
      String json = //convert entity to json
      return Response.ok(json, MediaType.APPLICATION_JSON).build();
  }
   */
  @GET
  @Path("/")
  public Response get() {
    List<Map<String, Object>> customersJson = new ArrayList<>();
    customerFacade.findAll().stream().forEach(customer -> {
      Map<String, Object> customerJson = new HashMap<>();
      customerJson.put("username", customer.getUsername());
      customersJson.add(customerJson);
    });

    return Response.ok(new Gson().toJson(customersJson), MediaType.APPLICATION_JSON).build();
  }

  private CustomerFacadeLocal lookupCustomerFacadeLocal() {
    try {
      javax.naming.Context c = new InitialContext();
      return (CustomerFacadeLocal) c.lookup("java:global/application/cscs-ejb/CustomerFacade!vn.cusc.aptech.cscs.ejb.beans.facades.CustomerFacadeLocal");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
