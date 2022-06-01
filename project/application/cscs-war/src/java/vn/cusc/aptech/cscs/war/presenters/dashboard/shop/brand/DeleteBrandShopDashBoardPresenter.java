/*
 * The MIT License
 *
 * Copyright 2021 ASUS.
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
package vn.cusc.aptech.cscs.war.presenters.dashboard.shop.brand;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import vn.cusc.aptech.cscs.ejb.beans.facades.BrandFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Brand;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author ASUS
 */
@Named(value = "deleteBrandShopDashBoardPresenter")
@ViewScoped
public class DeleteBrandShopDashBoardPresenter implements Serializable {

  /**
   * Creates a new instance of DeleteBrandShopDashBoardPresenter
   */
  public DeleteBrandShopDashBoardPresenter() {
  }
  @EJB
  private BrandFacadeLocal brandFacade;

  @Inject
  private ViewHelper viewHelper;

  private Brand brands;

  public Brand getBrand() {
    return brands;
  }

  public void setBrand(Brand brand) {
    this.brands = brand;
  }

  @PostConstruct
  public void init() {
    try {
      brands = brandFacade.find(Integer.valueOf(viewHelper.getParameters().get("id")));
      if (brands == null) {
        viewHelper.redirect("errors/404");
      }
    } catch (NumberFormatException e) {
      viewHelper.redirect("errors/404");
    }
  }

  public String delete() {
    brandFacade.remove(brands);
    return viewHelper.getPage("dashboard/shop/brand/list");
  }

  public Brand getBrands() {
    return brands;
  }

}
