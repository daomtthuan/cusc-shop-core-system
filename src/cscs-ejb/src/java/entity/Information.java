package entity;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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

@Entity
@Table(name = "information", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(
      name = "Information.findAll",
      query = "SELECT i FROM Information i"
    ),
    @NamedQuery(
      name = "Information.findById",
      query = "SELECT i FROM Information i WHERE i.id = :id"
    ),
    @NamedQuery(
      name = "Information.findByFullName",
      query = "SELECT i FROM Information i WHERE i.fullName = :fullName"
    ),
    @NamedQuery(
      name = "Information.findByBirthday",
      query = "SELECT i FROM Information i WHERE i.birthday = :birthday"
    ),
    @NamedQuery(
      name = "Information.findByGender",
      query = "SELECT i FROM Information i WHERE i.gender = :gender"
    ),
    @NamedQuery(
      name = "Information.findByEmail",
      query = "SELECT i FROM Information i WHERE i.email = :email"
    ),
    @NamedQuery(
      name = "Information.findByAddress",
      query = "SELECT i FROM Information i WHERE i.address = :address"
    ),
    @NamedQuery(
      name = "Information.findByPhone",
      query = "SELECT i FROM Information i WHERE i.phone = :phone"
    ),
  }
)
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

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "phone")
  private String phone;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "information")
  private Collection<Employee> employeeCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "information")
  private Collection<Customer> customerCollection;

  public Information() {}

  public Information(Integer id) {
    this.id = id;
  }

  public Information(
    Integer id,
    String fullName,
    Date birthday,
    boolean gender,
    String email,
    String address,
    String phone
  ) {
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
    return !(
      (this.id == null && other.id != null) ||
      (this.id != null && !this.id.equals(other.id))
    );
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
