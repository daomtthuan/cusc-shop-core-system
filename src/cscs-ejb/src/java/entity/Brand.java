package entity;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "brand", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b"),
    @NamedQuery(
      name = "Brand.findById",
      query = "SELECT b FROM Brand b WHERE b.id = :id"
    ),
    @NamedQuery(
      name = "Brand.findByName",
      query = "SELECT b FROM Brand b WHERE b.name = :name"
    ),
    @NamedQuery(
      name = "Brand.findByState",
      query = "SELECT b FROM Brand b WHERE b.state = :state"
    ),
  }
)
public class Brand implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "name")
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(name = "state")
  private boolean state;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
  private Collection<Product> productCollection;

  public Brand() {}

  public Brand(Integer id) {
    this.id = id;
  }

  public Brand(Integer id, String name, boolean state) {
    this.id = id;
    this.name = name;
    this.state = state;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean getState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  @XmlTransient
  public Collection<Product> getProductCollection() {
    return productCollection;
  }

  public void setProductCollection(Collection<Product> productCollection) {
    this.productCollection = productCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Brand)) {
      return false;
    }

    Brand other = (Brand) object;
    return !(
      (this.id == null && other.id != null) ||
      (this.id != null && !this.id.equals(other.id))
    );
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
