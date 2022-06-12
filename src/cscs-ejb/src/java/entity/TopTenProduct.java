package entity;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "top_ten_product", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(
      name = "TopTenProduct.findAll",
      query = "SELECT t FROM TopTenProduct t"
    ),
    @NamedQuery(
      name = "TopTenProduct.findById",
      query = "SELECT t FROM TopTenProduct t WHERE t.id = :id"
    ),
    @NamedQuery(
      name = "TopTenProduct.findByName",
      query = "SELECT t FROM TopTenProduct t WHERE t.name = :name"
    ),
    @NamedQuery(
      name = "TopTenProduct.findByTotalQuantity",
      query = "SELECT t FROM TopTenProduct t WHERE t.totalQuantity = :totalQuantity"
    ),
    @NamedQuery(
      name = "TopTenProduct.findByTotalPrice",
      query = "SELECT t FROM TopTenProduct t WHERE t.totalPrice = :totalPrice"
    ),
  }
)
public class TopTenProduct implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private int id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "name")
  private String name;

  @Column(name = "total_quantity")
  private BigInteger totalQuantity;

  @Min(value = 0)
  @Column(name = "total_price")
  private Double totalPrice;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigInteger getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(BigInteger totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
