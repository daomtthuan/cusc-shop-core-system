package facade;

import entity.TopTenProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TopTenProductFacade
  extends AbstractFacade<TopTenProduct>
  implements TopTenProductFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public TopTenProductFacade() {
    super(TopTenProduct.class);
  }
}
