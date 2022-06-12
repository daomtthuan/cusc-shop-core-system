package facade;

import entity.TotalSell;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TotalSellFacadeLocal {
  void create(TotalSell totalSell);

  void edit(TotalSell totalSell);

  void remove(TotalSell totalSell);

  TotalSell find(Object id);

  List<TotalSell> findAll();

  List<TotalSell> findRange(int[] range);

  int count();
}
