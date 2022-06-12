package facade;

import entity.Bill;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BillFacade
  extends AbstractFacade<Bill>
  implements BillFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public BillFacade() {
    super(Bill.class);
  }
}
