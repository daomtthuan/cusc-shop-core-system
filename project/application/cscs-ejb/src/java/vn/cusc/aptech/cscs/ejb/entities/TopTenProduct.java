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
import java.math.BigInteger;
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
@Table(name = "top_ten_product", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TopTenProduct.findAll", query = "SELECT t FROM TopTenProduct t"),
  @NamedQuery(name = "TopTenProduct.findById", query = "SELECT t FROM TopTenProduct t WHERE t.id = :id"),
  @NamedQuery(name = "TopTenProduct.findByName", query = "SELECT t FROM TopTenProduct t WHERE t.name = :name"),
  @NamedQuery(name = "TopTenProduct.findByTotalQuantity", query = "SELECT t FROM TopTenProduct t WHERE t.totalQuantity = :totalQuantity"),
  @NamedQuery(name = "TopTenProduct.findByTotalPrice", query = "SELECT t FROM TopTenProduct t WHERE t.totalPrice = :totalPrice")})
public class TopTenProduct implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  @Id
  private int id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "name")
  private String name;
  @Column(name = "total_quantity")
  private BigInteger totalQuantity;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "total_price")
  private Double totalPrice;

  public TopTenProduct() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigInteger getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(BigInteger totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

}
