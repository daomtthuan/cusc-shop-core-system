package facade;

import entity.Information;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InformationFacade
  extends AbstractFacade<Information>
  implements InformationFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public InformationFacade() {
    super(Information.class);
  }
}
