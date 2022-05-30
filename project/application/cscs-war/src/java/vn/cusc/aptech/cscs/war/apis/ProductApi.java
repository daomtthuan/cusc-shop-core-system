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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entity.facade.ProductFacadeLocal;
import entity.Product;
import vn.cusc.aptech.cscs.war.app.helpers.ApiHelper;
import vn.cusc.aptech.cscs.war.models.ProductModel;

/**
 *
 * @author Daomtthuan
 */
@Path("product")
public class ProductApi extends ApiHelper {

  private final ProductFacadeLocal productFacade;

  public ProductApi() {
    super();
    productFacade = lookupProductFacadeLocal();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() {
    ArrayList<ProductModel> productModels = new ArrayList<>();
    for (Product product : productFacade.findAll()) {
      if (product.getState()) {
        productModels.add(new ProductModel(product));
      }
    }
    return sendResponse(Response.Status.OK, productModels);
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

}
