package facade;

import entity.Brand;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BrandFacade
  extends AbstractFacade<Brand>
  implements BrandFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public BrandFacade() {
    super(Brand.class);
  }
}
