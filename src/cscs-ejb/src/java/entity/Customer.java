package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Customer
 */
@Entity
@Table(catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
  , @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id")
  , @NamedQuery(name = "Customer.findByUsername", query = "SELECT c FROM Customer c WHERE c.username = :username")
  , @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password")
  , @NamedQuery(name = "Customer.findByState", query = "SELECT c FROM Customer c WHERE c.state = :state")})
public class Customer implements Serializable {

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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private Collection<Bill> billCollection;

  @JoinColumn(name = "information", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false)
  private Information information;

  public Customer() {
  }

  public Customer(Integer id) {
    this.id = id;
  }

  public Customer(Integer id, String username, String password, boolean state) {
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
    if (!(object instanceof Customer)) {
      return false;
    }

    Customer other = (Customer) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "entity.Customer[ id=" + id + " ]";
  }

}
