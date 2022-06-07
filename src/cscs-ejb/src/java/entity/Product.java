package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Product
 */
@Entity
@Table(catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
  , @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
  , @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")
  , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")
  , @NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Product p WHERE p.quantity = :quantity")
  , @NamedQuery(name = "Product.findByState", query = "SELECT p FROM Product p WHERE p.state = :state")})
public class Product implements Serializable {

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
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private float price;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private int quantity;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private boolean state;

  @JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false)
  private Category category;

  @JoinColumn(name = "brand", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false)
  private Brand brand;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
  private Collection<BillDetails> billDetailsCollection;

  public Product() {
  }

  public Product(Integer id) {
    this.id = id;
  }

  public Product(Integer id, String name, float price, int quantity, boolean state) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.state = state;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public boolean getState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  @XmlTransient
  public Collection<BillDetails> getBillDetailsCollection() {
    return billDetailsCollection;
  }

  public void setBillDetailsCollection(Collection<BillDetails> billDetailsCollection) {
    this.billDetailsCollection = billDetailsCollection;
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
    if (!(object instanceof Product)) {
      return false;
    }
    Product other = (Product) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Product[ id=" + id + " ]";
  }

}
