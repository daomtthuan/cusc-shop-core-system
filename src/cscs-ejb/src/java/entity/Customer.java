package entity;

import com.google.gson.Gson;
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

@Entity
@Table(name = "customer", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(
      name = "Customer.findById",
      query = "SELECT c FROM Customer c WHERE c.id = :id"
    ),
    @NamedQuery(
      name = "Customer.findByUsername",
      query = "SELECT c FROM Customer c WHERE c.username = :username"
    ),
    @NamedQuery(
      name = "Customer.findByPassword",
      query = "SELECT c FROM Customer c WHERE c.password = :password"
    ),
    @NamedQuery(
      name = "Customer.findByState",
      query = "SELECT c FROM Customer c WHERE c.state = :state"
    ),
  }
)
public class Customer implements Serializable {

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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private Collection<Bill> billCollection;

  @JoinColumn(name = "information", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Information information;

  public Customer() {}

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
