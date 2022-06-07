package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Day Revenue
 */
@Entity
@Table(name = "day_revenue", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "DayRevenue.findAll", query = "SELECT d FROM DayRevenue d")
  , @NamedQuery(name = "DayRevenue.findByDate", query = "SELECT d FROM DayRevenue d WHERE d.date = :date")
  , @NamedQuery(name = "DayRevenue.findByTotalPrice", query = "SELECT d FROM DayRevenue d WHERE d.totalPrice = :totalPrice")})
public class DayRevenue implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Temporal(TemporalType.DATE)
  private Date date;

  @Min(value = 0)
  @Column(name = "total_price", precision = 22, scale = 0)
  private Double totalPrice;

  public DayRevenue() {
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
