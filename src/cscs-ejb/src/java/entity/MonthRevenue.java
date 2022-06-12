package entity;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "month_revenue", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(
      name = "MonthRevenue.findAll",
      query = "SELECT m FROM MonthRevenue m"
    ),
    @NamedQuery(
      name = "MonthRevenue.findByDate",
      query = "SELECT m FROM MonthRevenue m WHERE m.date = :date"
    ),
    @NamedQuery(
      name = "MonthRevenue.findByTotalPrice",
      query = "SELECT m FROM MonthRevenue m WHERE m.totalPrice = :totalPrice"
    ),
  }
)
public class MonthRevenue implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "date")
  @Temporal(TemporalType.DATE)
  private Date date;

  @Min(value = 0)
  @Column(name = "total_price")
  private Double totalPrice;

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

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
