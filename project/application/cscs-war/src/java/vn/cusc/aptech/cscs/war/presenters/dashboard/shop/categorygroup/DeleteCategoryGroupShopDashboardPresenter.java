/*
 * The MIT License
 *
 * Copyright 2021 Dang Quang Nghi.
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
package vn.cusc.aptech.cscs.war.presenters.dashboard.shop.categorygroup;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import vn.cusc.aptech.cscs.ejb.beans.facades.CategoryGroupFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.CategoryGroup;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author Dang Quang Nghi
 */
@Named(value = "deleteCategoryGroupShopDashboardPresenter")
@ViewScoped
public class DeleteCategoryGroupShopDashboardPresenter implements Serializable {

  /**
   * Creates a new instance of DeleteCategoryGroupShopDashboardPresenter
   */
  public DeleteCategoryGroupShopDashboardPresenter() {
  }
  @EJB
  private CategoryGroupFacadeLocal categoryGroupFacade;

  @Inject
  private ViewHelper viewHelper;

  private CategoryGroup categoryGroup;

  @PostConstruct
  public void init() {
    try {
      categoryGroup = categoryGroupFacade.find(Integer.valueOf(viewHelper.getParameters().get("id")));
      if (categoryGroup == null) {
        viewHelper.redirect("errors/404");
      }
    } catch (NumberFormatException e) {
      viewHelper.redirect("errors/404");
    }
  }

  public String delete() {
    categoryGroupFacade.remove(categoryGroup);
    return viewHelper.getPage("dashboard/shop/category-group/list");
  }

  public CategoryGroup getCategoryGroups() {
    return categoryGroup;
  }

}
