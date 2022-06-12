package facade;

import entity.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmployeeFacade
  extends AbstractFacade<Employee>
  implements EmployeeFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager entityManager;

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public EmployeeFacade() {
    super(Employee.class);
  }
}
