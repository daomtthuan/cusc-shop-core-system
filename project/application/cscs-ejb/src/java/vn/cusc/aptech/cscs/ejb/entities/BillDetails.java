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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daomtthuan
 */
@Entity
@Table(name = "bill_details", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "BillDetails.findAll", query = "SELECT b FROM BillDetails b"),
  @NamedQuery(name = "BillDetails.findByBill", query = "SELECT b FROM BillDetails b WHERE b.billDetailsPK.bill = :bill"),
  @NamedQuery(name = "BillDetails.findByProduct", query = "SELECT b FROM BillDetails b WHERE b.billDetailsPK.product = :product"),
  @NamedQuery(name = "BillDetails.findByPrice", query = "SELECT b FROM BillDetails b WHERE b.price = :price"),
  @NamedQuery(name = "BillDetails.findByQuantity", query = "SELECT b FROM BillDetails b WHERE b.quantity = :quantity")})
public class BillDetails implements Serializable {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected BillDetailsPK billDetailsPK;
  @Basic(optional = false)
  @NotNull
  @Column(name = "price")
  private float price;
  @Basic(optional = false)
  @NotNull
  @Column(name = "quantity")
  private int quantity;
  @JoinColumn(name = "bill", referencedColumnName = "id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Bill bill1;
  @JoinColumn(name = "product", referencedColumnName = "id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Product product1;

  public BillDetails() {
  }

  public BillDetails(BillDetailsPK billDetailsPK) {
    this.billDetailsPK = billDetailsPK;
  }

  public BillDetails(BillDetailsPK billDetailsPK, float price, int quantity) {
    this.billDetailsPK = billDetailsPK;
    this.price = price;
    this.quantity = quantity;
  }

  public BillDetails(int bill, int product) {
    this.billDetailsPK = new BillDetailsPK(bill, product);
  }

  public BillDetailsPK getBillDetailsPK() {
    return billDetailsPK;
  }

  public void setBillDetailsPK(BillDetailsPK billDetailsPK) {
    this.billDetailsPK = billDetailsPK;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Bill getBill1() {
    return bill1;
  }

  public void setBill1(Bill bill1) {
    this.bill1 = bill1;
  }

  public Product getProduct1() {
    return product1;
  }

  public void setProduct1(Product product1) {
    this.product1 = product1;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (billDetailsPK != null ? billDetailsPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof BillDetails)) {
      return false;
    }
    BillDetails other = (BillDetails) object;
    if ((this.billDetailsPK == null && other.billDetailsPK != null) || (this.billDetailsPK != null && !this.billDetailsPK.equals(other.billDetailsPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "vn.cusc.aptech.cscs.ejb.entities.BillDetails[ billDetailsPK=" + billDetailsPK + " ]";
  }

}
