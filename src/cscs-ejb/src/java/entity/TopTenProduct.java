package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Top Ten Product
 */
@Entity
@Table(name = "top_ten_product", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TopTenProduct.findAll", query = "SELECT t FROM TopTenProduct t")
  , @NamedQuery(name = "TopTenProduct.findById", query = "SELECT t FROM TopTenProduct t WHERE t.id = :id")
  , @NamedQuery(name = "TopTenProduct.findByName", query = "SELECT t FROM TopTenProduct t WHERE t.name = :name")
  , @NamedQuery(name = "TopTenProduct.findByTotalQuantity", query = "SELECT t FROM TopTenProduct t WHERE t.totalQuantity = :totalQuantity")
  , @NamedQuery(name = "TopTenProduct.findByTotalPrice", query = "SELECT t FROM TopTenProduct t WHERE t.totalPrice = :totalPrice")})
public class TopTenProduct implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private int id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(nullable = false, length = 100)
  private String name;

  @Column(name = "total_quantity")
  private BigInteger totalQuantity;

  @Min(value = 0)
  @Column(name = "total_price", precision = 22, scale = 0)
  private Double totalPrice;

  public TopTenProduct() {
  }

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

}
