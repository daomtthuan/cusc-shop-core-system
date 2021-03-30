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
package vn.cusc.aptech.cscs.war.app.helpers;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;
import vn.cusc.aptech.cscs.ejb.beans.session.AuthApiSessionBeanLocal;
import vn.cusc.aptech.cscs.war.models.Model;

/**
 *
 * @author Daomtthuan
 */
public class ApiHelper {

  protected final Gson gson;
  protected final AuthApiSessionBeanLocal authApiSessionBean;

  protected ApiHelper() {
    gson = new Gson();
    authApiSessionBean = lookupAuthApiSessionBeanLocal();
  }

  protected boolean isEmptyParam(String param) {
    return param == null || param.isEmpty();
  }

  protected boolean isEmptyBody(Model body) {
    return body == null || body.isEmpty();
  }

  protected <T extends Object> T getBody(String body, Class<T> classOfT) {
    return gson.fromJson(body, classOfT);
  }

  protected Response sendResponse(Response.Status status) {
    return Response.status(status)
      .build();
  }

  protected Response sendResponse(Response.Status status, Object data) {
    return Response.status(status)
      .entity(gson.toJson(data))
      .build();
  }

  private AuthApiSessionBeanLocal lookupAuthApiSessionBeanLocal() {
    try {
      Context c = new InitialContext();
      return (AuthApiSessionBeanLocal) c.lookup("java:global/application/cscs-ejb/AuthApiSessionBean!vn.cusc.aptech.cscs.ejb.beans.session.AuthApiSessionBeanLocal");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
