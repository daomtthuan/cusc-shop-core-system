package facade;

import entity.TopTenCustomer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TopTenCustomerFacade
  extends AbstractFacade<TopTenCustomer>
  implements TopTenCustomerFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public TopTenCustomerFacade() {
    super(TopTenCustomer.class);
  }
}
