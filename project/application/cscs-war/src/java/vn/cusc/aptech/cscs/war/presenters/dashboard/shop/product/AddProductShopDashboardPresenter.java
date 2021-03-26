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
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cscs.ejb.beans.facades.BrandFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.CategoryFacadeLocal;
import vn.cusc.aptech.cscs.ejb.beans.facades.CategoryGroupFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.Brand;
import vn.cusc.aptech.cscs.ejb.entities.Category;
import vn.cusc.aptech.cscs.ejb.entities.CategoryGroup;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "addProductShopDashboardPresenter")
@ViewScoped
public class AddProductShopDashboardPresenter implements Serializable {

  @EJB
  private CategoryFacadeLocal categoryFacade;

  @EJB
  private CategoryGroupFacadeLocal categoryGroupFacade;

  @EJB
  private BrandFacadeLocal brandFacade;

  @Inject
  private ViewHelper viewHelper;

  private String name;
  private int idCategoryGroup;
  private int idCategory;
  private int idBrand;
  private double price;
  private int quantity;
  private boolean state;

  private String nameInputStyleClass;
  private String categoryGroupInputStyleClass;
  private String categoryInputStyleClass;
  private String brandInputStyleClass;
  private String priceInputStyleClass;
  private String quantityInputStyleClass;

  @PostConstruct
  public void init() {
    name = null;
    idCategoryGroup = 0;
    idCategory = 0;
    idBrand = 0;
    price = 0;
    quantity = 0;
    state = true;

    nameInputStyleClass = null;
    categoryGroupInputStyleClass = null;
    categoryInputStyleClass = null;
    brandInputStyleClass = null;
    priceInputStyleClass = null;
    quantityInputStyleClass = null;
  }

  public String add() {
    boolean nameValid = Pattern.matches(ValidationHelper.RegexPattern.ANY_NAME, name);
    boolean categoryValid = idCategory != 0;
    boolean brandValid = idBrand != 0;
    boolean pricelValid = price >= 0;
    boolean quantityValid = quantity >= 0;

    nameInputStyleClass = nameValid ? null : ValidationHelper.StyleClass.INVALID;
    categoryInputStyleClass = categoryValid ? null : ValidationHelper.StyleClass.INVALID;
    brandInputStyleClass = brandValid ? null : ValidationHelper.StyleClass.INVALID;
    priceInputStyleClass = pricelValid ? null : ValidationHelper.StyleClass.INVALID;
    quantityInputStyleClass = quantityValid ? null : ValidationHelper.StyleClass.INVALID;

    if (!nameValid || !categoryValid || !brandValid || !pricelValid || !quantityValid) {
      return null;
    }

    return viewHelper.getPage("dashboard/shop/product/list");
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public int getIdBrand() {
    return idBrand;
  }

  public void setIdBrand(int idBrand) {
    this.idBrand = idBrand;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public String getNameInputStyleClass() {
    return nameInputStyleClass;
  }

  public void setNameInputStyleClass(String nameInputStyleClass) {
    this.nameInputStyleClass = nameInputStyleClass;
  }

  public String getCategoryGroupInputStyleClass() {
    return categoryGroupInputStyleClass;
  }

  public void setCategoryGroupInputStyleClass(String categoryGroupInputStyleClass) {
    this.categoryGroupInputStyleClass = categoryGroupInputStyleClass;
  }

  public String getCategoryInputStyleClass() {
    return categoryInputStyleClass;
  }

  public void setCategoryInputStyleClass(String categoryInputStyleClass) {
    this.categoryInputStyleClass = categoryInputStyleClass;
  }

  public String getBrandInputStyleClass() {
    return brandInputStyleClass;
  }

  public void setBrandInputStyleClass(String brandInputStyleClass) {
    this.brandInputStyleClass = brandInputStyleClass;
  }

  public String getPriceInputStyleClass() {
    return priceInputStyleClass;
  }

  public void setPriceInputStyleClass(String priceInputStyleClass) {
    this.priceInputStyleClass = priceInputStyleClass;
  }

  public String getQuantityInputStyleClass() {
    return quantityInputStyleClass;
  }

  public void setQuantityInputStyleClass(String quantityInputStyleClass) {
    this.quantityInputStyleClass = quantityInputStyleClass;
  }

}
