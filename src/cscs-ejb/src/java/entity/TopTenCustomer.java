package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Top Ten Customer
 */
@Entity
@Table(name = "top_ten_customer", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TopTenCustomer.findAll", query = "SELECT t FROM TopTenCustomer t")
  , @NamedQuery(name = "TopTenCustomer.findById", query = "SELECT t FROM TopTenCustomer t WHERE t.id = :id")
  , @NamedQuery(name = "TopTenCustomer.findByFullName", query = "SELECT t FROM TopTenCustomer t WHERE t.fullName = :fullName")
  , @NamedQuery(name = "TopTenCustomer.findByTotalBill", query = "SELECT t FROM TopTenCustomer t WHERE t.totalBill = :totalBill")
  , @NamedQuery(name = "TopTenCustomer.findByTotalBuy", query = "SELECT t FROM TopTenCustomer t WHERE t.totalBuy = :totalBuy")})
public class TopTenCustomer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private int id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "full_name", nullable = false, length = 100)
  private String fullName;

  @Basic(optional = false)
  @NotNull
  @Column(name = "total_bill", nullable = false)
  private long totalBill;

  @Min(value = 0)
  @Column(name = "total_buy", precision = 22, scale = 0)
  private Double totalBuy;

  public TopTenCustomer() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public long getTotalBill() {
    return totalBill;
  }

  public void setTotalBill(long totalBill) {
    this.totalBill = totalBill;
  }

  public Double getTotalBuy() {
    return totalBuy;
  }

  public void setTotalBuy(Double totalBuy) {
    this.totalBuy = totalBuy;
  }

}
