package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Brand
 */
@Entity
@Table(catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b")
  , @NamedQuery(name = "Brand.findById", query = "SELECT b FROM Brand b WHERE b.id = :id")
  , @NamedQuery(name = "Brand.findByName", query = "SELECT b FROM Brand b WHERE b.name = :name")
  , @NamedQuery(name = "Brand.findByState", query = "SELECT b FROM Brand b WHERE b.state = :state")})
public class Brand implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(nullable = false, length = 100)
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private boolean state;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
  private Collection<Product> productCollection;

  public Brand() {
  }

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
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "entity.Brand[ id=" + id + " ]";
  }

}
