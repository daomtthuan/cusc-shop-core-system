package facade;

import entity.DayRevenue;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DayRevenueFacadeLocal {
  void create(DayRevenue dayRevenue);

  void edit(DayRevenue dayRevenue);

  void remove(DayRevenue dayRevenue);

  DayRevenue find(Object id);

  List<DayRevenue> findAll();

  List<DayRevenue> findRange(int[] range);

  int count();
}
