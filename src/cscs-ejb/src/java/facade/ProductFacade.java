package facade;

import entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductFacade
  extends AbstractFacade<Product>
  implements ProductFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public ProductFacade() {
    super(Product.class);
  }
}
