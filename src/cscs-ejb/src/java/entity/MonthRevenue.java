package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Month Revenue
 */
@Entity
@Table(name = "month_revenue", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "MonthRevenue.findAll", query = "SELECT m FROM MonthRevenue m")
  , @NamedQuery(name = "MonthRevenue.findByDate", query = "SELECT m FROM MonthRevenue m WHERE m.date = :date")
  , @NamedQuery(name = "MonthRevenue.findByTotalPrice", query = "SELECT m FROM MonthRevenue m WHERE m.totalPrice = :totalPrice")})
public class MonthRevenue implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Temporal(TemporalType.DATE)
  private Date date;

  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "total_price", precision = 22, scale = 0)
  private Double totalPrice;

  public MonthRevenue() {
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

}
