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
package vn.cusc.aptech.cscs.ejb.beans.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vn.cusc.aptech.cscs.ejb.entities.Category;
import vn.cusc.aptech.cscs.ejb.entities.CategoryGroup;

/**
 *
 * @author Daomtthuan
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CategoryFacade() {
    super(Category.class);
  }

  @Override
  public List<Category> findByFilter(Object idCategoryGroup) {
    List<Category> categories = em.createQuery("SELECT c FROM Category c WHERE c.categoryGroup = :categoryGroup OR :idCategoryGroup = 0")
      .setParameter("idCategoryGroup", idCategoryGroup)
      .setParameter("categoryGroup", em.find(CategoryGroup.class, idCategoryGroup))
      .getResultList();
    return categories;
  }

  @Override
  public List<Category> findByFilters(int idCategory, int idCategoryGroup) {
    List<Category> categorys = em.createQuery("SELECT c FROM Category c WHERE c.categoryGroup = :categoryGroup OR :idCategoryGroup = 0")
      .setParameter("idCategoryGroup", idCategoryGroup)
      .setParameter("categoryGroup", em.find(CategoryGroup.class, idCategoryGroup))
      .getResultList();
    return categorys;
  }

}
