/*
 * The MIT License
 *
 * Copyright 2021 DANG QUANG NGHI.
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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cscs.ejb.beans.facades.CategoryGroupFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.CategoryGroup;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author DANG QUANG NGHI
 */
@Named(value = "listCategoryGroupShopDashboardPresenter")
@ViewScoped
public class ListCategoryGroupShopDashboardPresenter implements Serializable {

  /**
   * Creates a new instance of ListCategoryGroupShopDashboardPresenter
   */
  public ListCategoryGroupShopDashboardPresenter() {
  }
  @EJB
  private CategoryGroupFacadeLocal categoryGroupFacade;

  @Inject
  private ViewHelper viewHelper;

  private int idCategoryGroup;

  public int getIdCategoryGroup() {
    return idCategoryGroup;
  }

  public void setIdCategoryGroup(int idCategoryGroup) {
    this.idCategoryGroup = idCategoryGroup;
  }

  @PostConstruct
  public void init() {

    idCategoryGroup = 0;

  }

  public List<CategoryGroup> getCategoryGroups() {
    return categoryGroupFacade.findAll();
  }

  public String removeCategoryGroup(Object id) {
    categoryGroupFacade.remove(categoryGroupFacade.find(id));
    return viewHelper.getPage("dashboard/shop/category-group/list");
  }

}
