package facade;

import entity.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RoleFacade
  extends AbstractFacade<Role>
  implements RoleFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public RoleFacade() {
    super(Role.class);
  }
}
