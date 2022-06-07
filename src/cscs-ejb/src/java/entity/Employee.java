package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Employee
 */
@Entity
@Table(catalog = "cusc_shop", schema = "", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
  , @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
  , @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username")
  , @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password")
  , @NamedQuery(name = "Employee.findByState", query = "SELECT e FROM Employee e WHERE e.state = :state")})
public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(nullable = false, length = 100)
  private String username;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(nullable = false, length = 100)
  private String password;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private boolean state;

  @OneToMany(mappedBy = "salesman")
  private Collection<Bill> billCollection;

  @OneToMany(mappedBy = "shipper")
  private Collection<Bill> billCollection1;

  @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false)
  private Role role;

  @JoinColumn(name = "information", referencedColumnName = "id", nullable = false)
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean getState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  @XmlTransient
  public Collection<Bill> getBillCollection() {
    return billCollection;
  }

  public void setBillCollection(Collection<Bill> billCollection) {
    this.billCollection = billCollection;
  }

  @XmlTransient
  public Collection<Bill> getBillCollection1() {
    return billCollection1;
  }

  public void setBillCollection1(Collection<Bill> billCollection1) {
    this.billCollection1 = billCollection1;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Information getInformation() {
    return information;
  }

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
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "entity.Employee[ id=" + id + " ]";
  }

}
