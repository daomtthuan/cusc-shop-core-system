/*
 * The MIT License
 *
 * Copyright 2021 NGUYEN PHUC DUY.
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
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import vn.cusc.aptech.cscs.ejb.beans.facades.BrandFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Brand;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author NGUYEN PHUC DUY
 */
@Named(value = "editBrandDashBoardPresenter")
@ViewScoped
public class EditBrandShopDashBoardPresenter implements Serializable {

  @EJB
  private BrandFacadeLocal brandFacade;

  private Brand brand;

  private String brandNameInputStyleClass;

  public String getBrandNameInputStyleClass() {
    return brandNameInputStyleClass;
  }

  public void setBrandNameInputStyleClass(String brandNameInputStyleClass) {
    this.brandNameInputStyleClass = brandNameInputStyleClass;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  private String name;

  private boolean state;

  @Inject
  private ViewHelper viewHelper;

  @PostConstruct
  public void init() {
    try {
      brand = brandFacade.find(Integer.valueOf(viewHelper.getParameters().get("id")));
      if (brand == null) {
        viewHelper.redirect("errors/404");
      }
      name = brand.getName();
      state = brand.getState();
    } catch (NumberFormatException e) {
      viewHelper.redirect("errors/404");
    }
  }

  public String edit() {
    boolean brandNameValid = Pattern.matches(ValidationHelper.RegexPattern.NAME, name);

    brandNameInputStyleClass = brandNameValid ? null : ValidationHelper.StyleClass.INVALID;

    brand.setName(name);
    brand.setState(state);
    brandFacade.edit(brand);

    return viewHelper.getPage("dashboard/shop/brand/list");
  }

}
