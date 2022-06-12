package facade;

import entity.MonthRevenue;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MonthRevenueFacade
  extends AbstractFacade<MonthRevenue>
  implements MonthRevenueFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public MonthRevenueFacade() {
    super(MonthRevenue.class);
  }
}
