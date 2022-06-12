package facade;

import entity.BillDetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BillDetailsFacade
  extends AbstractFacade<BillDetails>
  implements BillDetailsFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public BillDetailsFacade() {
    super(BillDetails.class);
  }
}
