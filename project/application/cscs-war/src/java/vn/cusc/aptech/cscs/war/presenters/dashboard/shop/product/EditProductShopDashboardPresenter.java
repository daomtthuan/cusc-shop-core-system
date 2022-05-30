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
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ViewHelper;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "editProductShopDashboardPresenter")
@ViewScoped
public class EditProductShopDashboardPresenter implements Serializable {

  @EJB
  private ProductFacadeLocal productFacade;

  @EJB
  private CategoryFacadeLocal categoryFacade;

  @EJB
  private CategoryGroupFacadeLocal categoryGroupFacade;

  @EJB
  private BrandFacadeLocal brandFacade;

  @Inject
  private ViewHelper viewHelper;

  private Product product;

  private String name;
  private int idCategoryGroup;
  private int idCategory;
  private int idBrand;
  private String price;
  private String quantity;
  private boolean state;

  private String nameInputStyleClass;
  private String categoryGroupInputStyleClass;
  private String categoryInputStyleClass;
  private String priceInputStyleClass;
  private String quantityInputStyleClass;

  @PostConstruct
  public void init() {
    try {
      product = productFacade.find(Integer.valueOf(viewHelper.getParameters().get("id")));
      if (product == null) {
        viewHelper.redirect("errors/404");
        return;
      }

      Category category = product.getCategory();

      name = product.getName();
      idCategoryGroup = category.getCategoryGroup().getId();
      idCategory = category.getId();
      idBrand = product.getBrand().getId();
      price = String.valueOf(product.getPrice());
      quantity = String.valueOf(product.getQuantity());
      state = product.getState();

      nameInputStyleClass = null;
      categoryGroupInputStyleClass = null;
      categoryInputStyleClass = null;
      priceInputStyleClass = null;
      quantityInputStyleClass = null;
    } catch (NumberFormatException e) {
      viewHelper.redirect("errors/404");
    }
  }

  public String edit() {
    float priceValue = 0;
    int quantityValue = 0;

    boolean nameValid = Pattern.matches(ValidationHelper.RegexPattern.ANY_NAME, name);
    boolean brandValid = idBrand != 0;
    boolean categoryValid = idCategory != 0;
    boolean pricelValid = false;
    boolean quantityValid = false;

    try {
      priceValue = Float.valueOf(this.price);
      quantityValue = Integer.valueOf(this.quantity);

      pricelValid = priceValue >= 0;
      quantityValid = quantityValue >= 0;

      priceInputStyleClass = pricelValid ? null : ValidationHelper.StyleClass.INVALID;
      quantityInputStyleClass = quantityValid ? null : ValidationHelper.StyleClass.INVALID;
    } catch (NumberFormatException e) {
      priceInputStyleClass = ValidationHelper.StyleClass.INVALID;
      quantityInputStyleClass = ValidationHelper.StyleClass.INVALID;
    }

    nameInputStyleClass = nameValid ? null : ValidationHelper.StyleClass.INVALID;
    categoryInputStyleClass = categoryValid ? null : ValidationHelper.StyleClass.INVALID;

    if (!nameValid || !categoryValid || !brandValid || !pricelValid || !quantityValid) {
      return null;
    }

    product.setName(name);
    product.setCategory(categoryFacade.find(idCategory));
    product.setBrand(brandFacade.find(idBrand));
    product.setPrice(priceValue);
    product.setQuantity(quantityValue);
    product.setState(state);
    productFacade.edit(product);

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

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
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
