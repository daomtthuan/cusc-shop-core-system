package entity;

import com.google.gson.Gson;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "bill_details", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(
      name = "BillDetails.findAll",
      query = "SELECT b FROM BillDetails b"
    ),
    @NamedQuery(
      name = "BillDetails.findById",
      query = "SELECT b FROM BillDetails b WHERE b.id = :id"
    ),
    @NamedQuery(
      name = "BillDetails.findByPrice",
      query = "SELECT b FROM BillDetails b WHERE b.price = :price"
    ),
    @NamedQuery(
      name = "BillDetails.findByQuantity",
      query = "SELECT b FROM BillDetails b WHERE b.quantity = :quantity"
    ),
  }
)
public class BillDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Column(name = "price")
  private float price;

  @Basic(optional = false)
  @NotNull
  @Column(name = "quantity")
  private int quantity;

  @JoinColumn(name = "bill", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Bill bill;

  @JoinColumn(name = "product", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Product product;

  public BillDetails() {}

  public BillDetails(Integer id) {
    this.id = id;
  }

  public BillDetails(Integer id, float price, int quantity) {
    this.id = id;
    this.price = price;
    this.quantity = quantity;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Bill getBill() {
    return bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof BillDetails)) {
      return false;
    }

    BillDetails other = (BillDetails) object;
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
