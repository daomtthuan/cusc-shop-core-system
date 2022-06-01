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
package vn.cusc.aptech.cscs.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daomtthuan
 */
@Entity
@Table(name = "top_ten_customer", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TopTenCustomer.findAll", query = "SELECT t FROM TopTenCustomer t"),
  @NamedQuery(name = "TopTenCustomer.findById", query = "SELECT t FROM TopTenCustomer t WHERE t.id = :id"),
  @NamedQuery(name = "TopTenCustomer.findByFullName", query = "SELECT t FROM TopTenCustomer t WHERE t.fullName = :fullName"),
  @NamedQuery(name = "TopTenCustomer.findByTotalBill", query = "SELECT t FROM TopTenCustomer t WHERE t.totalBill = :totalBill"),
  @NamedQuery(name = "TopTenCustomer.findByTotalBuy", query = "SELECT t FROM TopTenCustomer t WHERE t.totalBuy = :totalBuy")})
public class TopTenCustomer implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  @Id
  private int id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "full_name")
  private String fullName;
  @Basic(optional = false)
  @NotNull
  @Column(name = "total_bill")
  private long totalBill;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "total_buy")
  private Double totalBuy;

  public TopTenCustomer() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public long getTotalBill() {
    return totalBill;
  }

  public void setTotalBill(long totalBill) {
    this.totalBill = totalBill;
  }

  public Double getTotalBuy() {
    return totalBuy;
  }

  public void setTotalBuy(Double totalBuy) {
    this.totalBuy = totalBuy;
  }

}
