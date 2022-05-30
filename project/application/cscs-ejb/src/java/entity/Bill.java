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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daomtthuan
 */
@Entity
@Table(name = "bill", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
  @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
  @NamedQuery(name = "Bill.findByCreateDate", query = "SELECT b FROM Bill b WHERE b.createDate = :createDate"),
  @NamedQuery(name = "Bill.findByPayDate", query = "SELECT b FROM Bill b WHERE b.payDate = :payDate"),
  @NamedQuery(name = "Bill.findByStatus", query = "SELECT b FROM Bill b WHERE b.status = :status"),
  @NamedQuery(name = "Bill.findByState", query = "SELECT b FROM Bill b WHERE b.state = :state")})
public class Bill implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "create_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;
  @Column(name = "pay_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date payDate;
  @Basic(optional = false)
  @NotNull
  @Column(name = "status")
  private int status;
  @Basic(optional = false)
  @NotNull
  @Column(name = "state")
  private boolean state;
  @JoinColumn(name = "customer", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Customer customer;
  @JoinColumn(name = "salesman", referencedColumnName = "id")
  @ManyToOne
  private Employee salesman;
  @JoinColumn(name = "shipper", referencedColumnName = "id")
  @ManyToOne
  private Employee shipper;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
  private List<BillDetails> billDetailsList;

  public Bill() {
  }

  public Bill(Integer id) {
    this.id = id;
  }

  public Bill(Integer id, Date createDate, int status, boolean state) {
    this.id = id;
    this.createDate = createDate;
    this.status = status;
    this.state = state;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public boolean getState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Employee getSalesman() {
    return salesman;
  }

  public void setSalesman(Employee salesman) {
    this.salesman = salesman;
  }

  public Employee getShipper() {
    return shipper;
  }

  public void setShipper(Employee shipper) {
    this.shipper = shipper;
  }

  @XmlTransient
  public List<BillDetails> getBillDetailsList() {
    return billDetailsList;
  }

  public void setBillDetailsList(List<BillDetails> billDetailsList) {
    this.billDetailsList = billDetailsList;
  }

  public double getTotalPrice() {
    double totalPrice = 0;
    for (BillDetails billDetails : getBillDetailsList()) {
      totalPrice += billDetails.getPrice() * billDetails.getQuantity();
    }
    return totalPrice;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Bill)) {
      return false;
    }
    Bill other = (Bill) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "vn.cusc.aptech.cscs.ejb.entities.Bill[ id=" + id + " ]";
  }

}
