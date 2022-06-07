package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Category Group
 */
@Entity
@Table(name = "category_group", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "CategoryGroup.findAll", query = "SELECT c FROM CategoryGroup c")
  , @NamedQuery(name = "CategoryGroup.findById", query = "SELECT c FROM CategoryGroup c WHERE c.id = :id")
  , @NamedQuery(name = "CategoryGroup.findByName", query = "SELECT c FROM CategoryGroup c WHERE c.name = :name")
  , @NamedQuery(name = "CategoryGroup.findByState", query = "SELECT c FROM CategoryGroup c WHERE c.state = :state")})
public class CategoryGroup implements Serializable {

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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryGroup")
  private Collection<Category> categoryCollection;

  public CategoryGroup() {
  }

  public CategoryGroup(Integer id) {
    this.id = id;
  }

  public CategoryGroup(Integer id, String name, boolean state) {
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
  public Collection<Category> getCategoryCollection() {
    return categoryCollection;
  }

  public void setCategoryCollection(Collection<Category> categoryCollection) {
    this.categoryCollection = categoryCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof CategoryGroup)) {
      return false;
    }

    CategoryGroup other = (CategoryGroup) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "entity.CategoryGroup[ id=" + id + " ]";
  }

}
