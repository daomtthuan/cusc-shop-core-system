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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daomtthuan
 */
@Entity
@Table(name = "information", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Information.findAll", query = "SELECT i FROM Information i"),
  @NamedQuery(name = "Information.findById", query = "SELECT i FROM Information i WHERE i.id = :id"),
  @NamedQuery(name = "Information.findByFullName", query = "SELECT i FROM Information i WHERE i.fullName = :fullName"),
  @NamedQuery(name = "Information.findByBirthday", query = "SELECT i FROM Information i WHERE i.birthday = :birthday"),
  @NamedQuery(name = "Information.findByGender", query = "SELECT i FROM Information i WHERE i.gender = :gender"),
  @NamedQuery(name = "Information.findByEmail", query = "SELECT i FROM Information i WHERE i.email = :email"),
  @NamedQuery(name = "Information.findByAddress", query = "SELECT i FROM Information i WHERE i.address = :address"),
  @NamedQuery(name = "Information.findByPhone", query = "SELECT i FROM Information i WHERE i.phone = :phone")})
public class Information implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "full_name")
  private String fullName;
  @Basic(optional = false)
  @NotNull
  @Column(name = "birthday")
  @Temporal(TemporalType.TIMESTAMP)
  private Date birthday;
  @Basic(optional = false)
  @NotNull
  @Column(name = "gender")
  private boolean gender;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "email")
  private String email;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 500)
  @Column(name = "address")
  private String address;
  // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "phone")
  private String phone;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "information")
  private List<Employee> employeeList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "information")
  private List<Customer> customerList;

  public Information() {
  }

  public Information(Integer id) {
    this.id = id;
  }

  public Information(Integer id, String fullName, Date birthday, boolean gender, String email, String address, String phone) {
    this.id = id;
    this.fullName = fullName;
    this.birthday = birthday;
    this.gender = gender;
    this.email = email;
    this.address = address;
    this.phone = phone;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public boolean getGender() {
    return gender;
  }

  public void setGender(boolean gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @XmlTransient
  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public void setEmployeeList(List<Employee> employeeList) {
    this.employeeList = employeeList;
  }

  @XmlTransient
  public List<Customer> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Customer> customerList) {
    this.customerList = customerList;
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
    if (!(object instanceof Information)) {
      return false;
    }
    Information other = (Information) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "vn.cusc.aptech.cscs.ejb.entities.Information[ id=" + id + " ]";
  }

}
