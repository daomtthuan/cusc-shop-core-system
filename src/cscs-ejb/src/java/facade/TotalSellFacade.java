package facade;

import entity.TotalSell;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TotalSellFacade
  extends AbstractFacade<TotalSell>
  implements TotalSellFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public TotalSellFacade() {
    super(TotalSell.class);
  }
}
