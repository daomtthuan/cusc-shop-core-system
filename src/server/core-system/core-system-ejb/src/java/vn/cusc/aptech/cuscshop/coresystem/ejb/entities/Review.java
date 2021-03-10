/*
 * The MIT License
 *
 * Copyright 2021 Daomtthuan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package vn.cusc.aptech.cuscshop.coresystem.ejb.entities;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Review entity
 *
 * @author Daomtthuan
 * @version 1.0
 */
@Entity
@Table(name = "review", catalog = "cusc_shop", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
  @NamedQuery(name = "Review.findById", query = "SELECT r FROM Review r WHERE r.id = :id"),
  @NamedQuery(name = "Review.findByStar", query = "SELECT r FROM Review r WHERE r.star = :star"),
  @NamedQuery(name = "Review.findByContent", query = "SELECT r FROM Review r WHERE r.content = :content"),
  @NamedQuery(name = "Review.findByState", query = "SELECT r FROM Review r WHERE r.state = :state")})
public class Review implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "star")
  private short star;
  @Size(max = 500)
  @Column(name = "content")
  private String content;
  @Basic(optional = false)
  @NotNull
  @Column(name = "state")
  private boolean state;
  @JoinColumn(name = "id_account", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Account idAccount;
  @JoinColumn(name = "id_product", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Product idProduct;

  public Review() {
  }

  public Review(Integer id) {
    this.id = id;
  }

  public Review(Integer id, short star, boolean state) {
    this.id = id;
    this.star = star;
    this.state = state;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public short getStar() {
    return star;
  }

  public void setStar(short star) {
    this.star = star;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean getState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public Account getIdAccount() {
    return idAccount;
  }

  public void setIdAccount(Account idAccount) {
    this.idAccount = idAccount;
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
    if (!(object instanceof Review)) {
      return false;
    }
    Review other = (Review) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Review[ id=" + id + " ]";
  }

}
