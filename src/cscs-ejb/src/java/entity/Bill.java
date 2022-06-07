package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Bill
 */
@Entity
@Table(catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
  , @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id")
  , @NamedQuery(name = "Bill.findByCreateDate", query = "SELECT b FROM Bill b WHERE b.createDate = :createDate")
  , @NamedQuery(name = "Bill.findByPayDate", query = "SELECT b FROM Bill b WHERE b.payDate = :payDate")
  , @NamedQuery(name = "Bill.findByStatus", query = "SELECT b FROM Bill b WHERE b.status = :status")
  , @NamedQuery(name = "Bill.findByState", query = "SELECT b FROM Bill b WHERE b.state = :state")})
public class Bill implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Column(name = "create_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Column(name = "pay_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date payDate;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private int status;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private boolean state;

  @JoinColumn(name = "customer", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false)
  private Customer customer;

  @JoinColumn(name = "salesman", referencedColumnName = "id")
  @ManyToOne
  private Employee salesman;

  @JoinColumn(name = "shipper", referencedColumnName = "id")
  @ManyToOne
  private Employee shipper;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
  private Collection<BillDetails> billDetailsCollection;

  public Bill() {
  }

  public Bill(Integer id) {
    this.id = id;
  }

  public Bill(Integer id, Date createDate, int status, boolean state) {
    this.id = id;
    this.createDate = createDate;
    this.status = status;
    this.state = state;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public boolean getState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Employee getSalesman() {
    return salesman;
  }

  public void setSalesman(Employee salesman) {
    this.salesman = salesman;
  }

  public Employee getShipper() {
    return shipper;
  }

  public void setShipper(Employee shipper) {
    this.shipper = shipper;
  }

  @XmlTransient
  public Collection<BillDetails> getBillDetailsCollection() {
    return billDetailsCollection;
  }

  public void setBillDetailsCollection(Collection<BillDetails> billDetailsCollection) {
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
    if (!(object instanceof Bill)) {
      return false;
    }

    Bill other = (Bill) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "entity.Bill[ id=" + id + " ]";
  }

}
