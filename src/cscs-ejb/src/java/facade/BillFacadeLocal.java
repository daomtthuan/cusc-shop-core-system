package facade;

import entity.Bill;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BillFacadeLocal {
  void create(Bill bill);

  void edit(Bill bill);

  void remove(Bill bill);

  Bill find(Object id);

  List<Bill> findAll();

  List<Bill> findRange(int[] range);

  int count();
}
