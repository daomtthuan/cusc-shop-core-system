package facade;

import entity.CategoryGroup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryGroupFacade
  extends AbstractFacade<CategoryGroup>
  implements CategoryGroupFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public CategoryGroupFacade() {
    super(CategoryGroup.class);
  }
}
