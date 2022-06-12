package facade;

import entity.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryFacade
  extends AbstractFacade<Category>
  implements CategoryFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public CategoryFacade() {
    super(Category.class);
  }
}
