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

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import vn.cusc.aptech.cscs.war.app.config.ConfigApp;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "viewHelper")
@ApplicationScoped
public class ViewHelper implements Serializable {

  @Inject
  private ConfigApp configApp;

  private ExternalContext getContext() {
    return FacesContext.getCurrentInstance().getExternalContext();
  }

  public Map<String, String> getParameters() {
    return getContext().getRequestParameterMap();
  }

  public void redirect(String name) {
    ExternalContext context = getContext();
    try {
      context.redirect(context.getRequestContextPath() + "/pages/" + name + ".html");
    } catch (IOException e) {
    }
  }

  public String getPage(String name) {
    return "/pages/" + name + ".html?faces-redirect=true";
  }

  public String getComponentStyle(String name) {
    return "components/" + name + "/style.css";
  }

  public String getComponentScript(String name) {
    return "components/" + name + "/script.js";
  }

}
