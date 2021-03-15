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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Daomtthuan
 */
@Embeddable
public class BillDetailsPK implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Column(name = "bill")
  private int bill;
  @Basic(optional = false)
  @NotNull
  @Column(name = "product")
  private int product;

  public BillDetailsPK() {
  }

  public BillDetailsPK(int bill, int product) {
    this.bill = bill;
    this.product = product;
  }

  public int getBill() {
    return bill;
  }

  public void setBill(int bill) {
    this.bill = bill;
  }

  public int getProduct() {
    return product;
  }

  public void setProduct(int product) {
    this.product = product;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (int) bill;
    hash += (int) product;
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof BillDetailsPK)) {
      return false;
    }
    BillDetailsPK other = (BillDetailsPK) object;
    if (this.bill != other.bill) {
      return false;
    }
    if (this.product != other.product) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "vn.cusc.aptech.cscs.ejb.entities.BillDetailsPK[ bill=" + bill + ", product=" + product + " ]";
  }

}
