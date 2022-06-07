package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Information
 */
@Entity
@Table(catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Information.findAll", query = "SELECT i FROM Information i")
  , @NamedQuery(name = "Information.findById", query = "SELECT i FROM Information i WHERE i.id = :id")
  , @NamedQuery(name = "Information.findByFullName", query = "SELECT i FROM Information i WHERE i.fullName = :fullName")
  , @NamedQuery(name = "Information.findByBirthday", query = "SELECT i FROM Information i WHERE i.birthday = :birthday")
  , @NamedQuery(name = "Information.findByGender", query = "SELECT i FROM Information i WHERE i.gender = :gender")
  , @NamedQuery(name = "Information.findByEmail", query = "SELECT i FROM Information i WHERE i.email = :email")
  , @NamedQuery(name = "Information.findByAddress", query = "SELECT i FROM Information i WHERE i.address = :address")
  , @NamedQuery(name = "Information.findByPhone", query = "SELECT i FROM Information i WHERE i.phone = :phone")})
public class Information implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "full_name", nullable = false, length = 100)
  private String fullName;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date birthday;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private boolean gender;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(nullable = false, length = 100)
  @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
  private String email;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 500)
  @Column(nullable = false, length = 500)
  private String address;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(nullable = false, length = 100)
  @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Invalid phone/fax format, should be as xxx-xxx-xxxx")
  private String phone;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "information")
  private Collection<Employee> employeeCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "information")
  private Collection<Customer> customerCollection;

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
  public Collection<Employee> getEmployeeCollection() {
    return employeeCollection;
  }

  public void setEmployeeCollection(Collection<Employee> employeeCollection) {
    this.employeeCollection = employeeCollection;
  }

  @XmlTransient
  public Collection<Customer> getCustomerCollection() {
    return customerCollection;
  }

  public void setCustomerCollection(Collection<Customer> customerCollection) {
    this.customerCollection = customerCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
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
    return "entity.Information[ id=" + id + " ]";
  }

}
