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

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import vn.cusc.aptech.cscs.ejb.entities.Revenue;
import vn.cusc.aptech.cscs.ejb.entities.YearRevenue;

/**
 *
 * @author Daomtthuan
 */
@Stateless
public class YearRevenueFacade extends AbstractFacade<YearRevenue> implements YearRevenueFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public YearRevenueFacade() {
    super(YearRevenue.class);
  }

  @Override
  public List<Revenue> findBetween(Date fromDate, Date toDate) {
    return em.createQuery("SELECT y FROM YearRevenue y WHERE y.date BETWEEN :fromDate AND :toDate")
      .setParameter("fromDate", fromDate, TemporalType.DATE)
      .setParameter("toDate", toDate, TemporalType.DATE)
      .getResultList();
  }

}
