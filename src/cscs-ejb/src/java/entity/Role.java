package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Role
 */
@Entity
@Table(catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
  , @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id")
  , @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")})
public class Role implements Serializable {

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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
  private Collection<Employee> employeeCollection;

  public Role() {
  }

  public Role(Integer id) {
    this.id = id;
  }

  public Role(Integer id, String name) {
    this.id = id;
    this.name = name;
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

  @XmlTransient
  public Collection<Employee> getEmployeeCollection() {
    return employeeCollection;
  }

  public void setEmployeeCollection(Collection<Employee> employeeCollection) {
    this.employeeCollection = employeeCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Role)) {
      return false;
    }

    Role other = (Role) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "entity.Role[ id=" + id + " ]";
  }

}
