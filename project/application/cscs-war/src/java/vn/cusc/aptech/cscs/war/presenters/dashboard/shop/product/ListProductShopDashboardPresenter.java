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
package vn.cusc.aptech.cscs.war.presenters.dashboard.shop.product;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entity.facade.BrandFacadeLocal;
import entity.facade.CategoryFacadeLocal;
import entity.facade.CategoryGroupFacadeLocal;
import entity.facade.ProductFacadeLocal;
import entity.Brand;
import entity.Category;
import entity.CategoryGroup;
import entity.Product;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "listProductShopDashboardPresenter")
@ViewScoped
public class ListProductShopDashboardPresenter implements Serializable {

  @EJB
  private BrandFacadeLocal brandFacade;

  @EJB
  private CategoryGroupFacadeLocal categoryGroupFacade;

  @EJB
  private CategoryFacadeLocal categoryFacade;

  @EJB
  private ProductFacadeLocal productFacade;

  @Inject
  private ViewHelper viewHelper;

  private int idBrand;
  private int idCategoryGroup;
  private int idCategory;

  @PostConstruct
  public void init() {
    idBrand = 0;
    idCategoryGroup = 0;
    idCategory = 0;
  }

  public List<Brand> getBrands() {
    return brandFacade.findAll();
  }

  public List<CategoryGroup> getCategoryGroups() {
    return categoryGroupFacade.findAll();
  }

  public List<Category> getCategories() {
    return categoryFacade.findByFilter(idCategoryGroup);
  }

  public List<Product> getProducts() {
    return productFacade.findByFilter(idBrand, idCategoryGroup, idCategory);
  }

  public int getIdBrand() {
    return idBrand;
  }

  public void setIdBrand(int idBrand) {
    this.idBrand = idBrand;
  }

  public int getIdCategoryGroup() {
    return idCategoryGroup;
  }

  public void setIdCategoryGroup(int idCategoryGroup) {
    this.idCategoryGroup = idCategoryGroup;
  }

  public int getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(int idCategory) {
    this.idCategory = idCategory;
  }

}
