package vn.cusc.aptech.cuscshop.coresystem.ejb.entity;

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
@NamedQueries({
    @NamedQuery(name = "BillDetails.findAll", query = "SELECT b FROM BillDetails b")
    , @NamedQuery(name = "BillDetails.findById", query = "SELECT b FROM BillDetails b WHERE b.id = :id")
    , @NamedQuery(name = "BillDetails.findByQuantity", query = "SELECT b FROM BillDetails b WHERE b.quantity = :quantity")
    , @NamedQuery(name = "BillDetails.findByState", query = "SELECT b FROM BillDetails b WHERE b.state = :state")})
public class BillDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "state")
    private boolean state;
    @JoinColumn(name = "id_bill", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bill idBill;
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product idProduct;

    public BillDetails() {
    }

    public BillDetails(Integer id) {
        this.id = id;
    }

    public BillDetails(Integer id, int quantity, boolean state) {
        this.id = id;
        this.quantity = quantity;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Bill getIdBill() {
        return idBill;
    }

    public void setIdBill(Bill idBill) {
        this.idBill = idBill;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillDetails)) {
            return false;
        }
        BillDetails other = (BillDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vn.cusc.aptech.cuscshop.coresystem.ejb.entity.BillDetails[ id=" + id + " ]";
    }
    
}
