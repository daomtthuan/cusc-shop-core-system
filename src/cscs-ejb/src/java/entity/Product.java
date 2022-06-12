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
@Table(name = "product", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(
      name = "Product.findById",
      query = "SELECT p FROM Product p WHERE p.id = :id"
    ),
    @NamedQuery(
      name = "Product.findByName",
      query = "SELECT p FROM Product p WHERE p.name = :name"
    ),
    @NamedQuery(
      name = "Product.findByPrice",
      query = "SELECT p FROM Product p WHERE p.price = :price"
    ),
    @NamedQuery(
      name = "Product.findByQuantity",
      query = "SELECT p FROM Product p WHERE p.quantity = :quantity"
    ),
    @NamedQuery(
      name = "Product.findByState",
      query = "SELECT p FROM Product p WHERE p.state = :state"
    ),
  }
)
public class Product implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "name")
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(name = "price")
  private float price;

  @Basic(optional = false)
  @NotNull
  @Column(name = "quantity")
  private int quantity;

  @Basic(optional = false)
  @NotNull
  @Column(name = "state")
  private boolean state;

  @JoinColumn(name = "category", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Category category;

  @JoinColumn(name = "brand", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Brand brand;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
  private Collection<BillDetails> billDetailsCollection;

  public Product() {}

  public Product(Integer id) {
    this.id = id;
  }

  public Product(
    Integer id,
    String name,
    float price,
    int quantity,
    boolean state
  ) {
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

  public void setBillDetailsCollection(
    Collection<BillDetails> billDetailsCollection
  ) {
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
    if (!(object instanceof Product)) {
      return false;
    }

    Product other = (Product) object;
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
