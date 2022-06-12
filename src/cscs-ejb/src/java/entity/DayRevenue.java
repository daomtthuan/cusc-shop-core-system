package entity;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "day_revenue", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(
      name = "DayRevenue.findAll",
      query = "SELECT d FROM DayRevenue d"
    ),
    @NamedQuery(
      name = "DayRevenue.findByDate",
      query = "SELECT d FROM DayRevenue d WHERE d.date = :date"
    ),
    @NamedQuery(
      name = "DayRevenue.findByTotalPrice",
      query = "SELECT d FROM DayRevenue d WHERE d.totalPrice = :totalPrice"
    ),
  }
)
public class DayRevenue implements Serializable {

  private static final long serialVersionUID = 1L;

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
