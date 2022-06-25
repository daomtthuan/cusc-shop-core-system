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
package vn.cusc.aptech.cscs.war.app.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Daomtthuan
 */
@ApplicationPath("apis")
public class ServiceConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method. It is automatically populated with all resources defined in the project. If required, comment out calling
   * this method in getClasses().
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(vn.cusc.aptech.cscs.war.apis.BrandApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.CategoryApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.CategoryGroupApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.ProductApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.customer.AuthCustomerApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.customer.BillCustomerApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.customer.InformationCustomerApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.customer.OrderCustomerApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.shipper.AuthShipperApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.shipper.InformationShipperApi.class);
    resources.add(vn.cusc.aptech.cscs.war.apis.shipper.ShipShipperApi.class);
  }

}
