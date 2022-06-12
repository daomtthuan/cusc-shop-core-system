package facade;

import entity.YearRevenue;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class YearRevenueFacade
  extends AbstractFacade<YearRevenue>
  implements YearRevenueFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public YearRevenueFacade() {
    super(YearRevenue.class);
  }
}
