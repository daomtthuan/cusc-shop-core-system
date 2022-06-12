package facade;

import entity.BillDetails;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BillDetailsFacadeLocal {
  void create(BillDetails billDetails);

  void edit(BillDetails billDetails);

  void remove(BillDetails billDetails);

  BillDetails find(Object id);

  List<BillDetails> findAll();

  List<BillDetails> findRange(int[] range);

  int count();
}
