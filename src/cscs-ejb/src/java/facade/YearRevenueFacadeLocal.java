package facade;

import entity.YearRevenue;
import java.util.List;
import javax.ejb.Local;

@Local
public interface YearRevenueFacadeLocal {
  void create(YearRevenue yearRevenue);

  void edit(YearRevenue yearRevenue);

  void remove(YearRevenue yearRevenue);

  YearRevenue find(Object id);

  List<YearRevenue> findAll();

  List<YearRevenue> findRange(int[] range);

  int count();
}
