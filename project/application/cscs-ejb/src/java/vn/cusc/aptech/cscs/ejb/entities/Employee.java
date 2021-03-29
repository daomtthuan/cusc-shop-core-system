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
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daomtthuan
 */
@Entity
@Table(name = "employee", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
  @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
  @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username"),
  @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
  @NamedQuery(name = "Employee.findByState", query = "SELECT e FROM Employee e WHERE e.state = :state")})
public class Employee implements Account, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "username")
  private String username;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "password")
  private String password;

  @Basic(optional = false)
  @NotNull
  @Column(name = "state")
  private boolean state;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesman")
  private Collection<Bill> sellBillCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipper")
  private Collection<Bill> shipBillCollection;

  @JoinColumn(name = "role", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Role role;

  @JoinColumn(name = "information", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Information information;

  public Employee() {
  }

  public Employee(Integer id) {
    this.id = id;
  }

  public Employee(Integer id, String username, String password, boolean state) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.state = state;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean getState() {
    return state;
  }

  @Override
  public void setState(boolean state) {
    this.state = state;
  }

  @XmlTransient
  public Collection<Bill> getSellBillCollection() {
    return sellBillCollection;
  }

  public void setSellBillCollection(Collection<Bill> sellBillCollection) {
    this.sellBillCollection = sellBillCollection;
  }

  @XmlTransient
  public Collection<Bill> getShipBillCollection() {
    return shipBillCollection;
  }

  public void setShipBillCollection(Collection<Bill> shipBillCollection) {
    this.shipBillCollection = shipBillCollection;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public Information getInformation() {
    return information;
  }

  @Override
  public void setInformation(Information information) {
    this.information = information;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Employee)) {
      return false;
    }

    Employee other = (Employee) object;
    return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
  }

  @Override
  public String toString() {
    return "vn.cusc.aptech.cscs.ejb.entities.Employee[ id=" + id + " ]";
  }

}
