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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "total_sell", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(
      name = "TotalSell.findAll",
      query = "SELECT t FROM TotalSell t"
    ),
    @NamedQuery(
      name = "TotalSell.findByProduct",
      query = "SELECT t FROM TotalSell t WHERE t.product = :product"
    ),
    @NamedQuery(
      name = "TotalSell.findByTotalQuantity",
      query = "SELECT t FROM TotalSell t WHERE t.totalQuantity = :totalQuantity"
    ),
    @NamedQuery(
      name = "TotalSell.findByTotalPrice",
      query = "SELECT t FROM TotalSell t WHERE t.totalPrice = :totalPrice"
    ),
  }
)
public class TotalSell implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "product")
  private int product;

  @Column(name = "total_quantity")
  private BigInteger totalQuantity;

  @Min(value = 0)
  @Column(name = "total_price")
  private Double totalPrice;

  public int getProduct() {
    return product;
  }

  public void setProduct(int product) {
    this.product = product;
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
