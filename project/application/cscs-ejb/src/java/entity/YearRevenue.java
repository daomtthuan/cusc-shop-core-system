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
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daomtthuan
 */
@Entity
@Table(name = "year_revenue", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "YearRevenue.findAll", query = "SELECT y FROM YearRevenue y"),
  @NamedQuery(name = "YearRevenue.findByDate", query = "SELECT y FROM YearRevenue y WHERE y.date = :date"),
  @NamedQuery(name = "YearRevenue.findByTotalPrice", query = "SELECT y FROM YearRevenue y WHERE y.totalPrice = :totalPrice")})
public class YearRevenue implements Revenue, Serializable {

  private static final long serialVersionUID = 1L;
  @Column(name = "date")
  @Temporal(TemporalType.DATE)
  @Id
  private Date date;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "total_price")
  private Double totalPrice;

  public YearRevenue() {
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public Double getTotalPrice() {
    return totalPrice;
  }

  @Override
  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

}
