package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Category
 */
@Entity
@Table(catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
  , @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id")
  , @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")
  , @NamedQuery(name = "Category.findByState", query = "SELECT c FROM Category c WHERE c.state = :state")})
public class Category implements Serializable {

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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  private Collection<Product> productCollection;

  @JoinColumn(name = "category_group", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false)
  private CategoryGroup categoryGroup;

  public Category() {
  }

  public Category(Integer id) {
    this.id = id;
  }

  public Category(Integer id, String name, boolean state) {
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

  public CategoryGroup getCategoryGroup() {
    return categoryGroup;
  }

  public void setCategoryGroup(CategoryGroup categoryGroup) {
    this.categoryGroup = categoryGroup;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Category)) {
      return false;
    }

    Category other = (Category) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "entity.Category[ id=" + id + " ]";
  }

}
