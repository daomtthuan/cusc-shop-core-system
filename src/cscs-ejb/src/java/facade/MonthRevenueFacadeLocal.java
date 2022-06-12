package facade;

import entity.MonthRevenue;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MonthRevenueFacadeLocal {
  void create(MonthRevenue monthRevenue);

  void edit(MonthRevenue monthRevenue);

  void remove(MonthRevenue monthRevenue);

  MonthRevenue find(Object id);

  List<MonthRevenue> findAll();

  List<MonthRevenue> findRange(int[] range);

  int count();
}
