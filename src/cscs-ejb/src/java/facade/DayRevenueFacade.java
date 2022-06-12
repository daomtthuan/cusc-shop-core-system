package facade;

import entity.DayRevenue;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DayRevenueFacade
  extends AbstractFacade<DayRevenue>
  implements DayRevenueFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public DayRevenueFacade() {
    super(DayRevenue.class);
  }
}
