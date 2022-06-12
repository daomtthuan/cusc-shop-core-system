package facade;

import entity.Employee;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EmployeeFacadeLocal {
  void create(Employee employee);

  void edit(Employee employee);

  void remove(Employee employee);

  Employee find(Object id);

  List<Employee> findAll();

  List<Employee> findRange(int[] range);

  int count();
}
