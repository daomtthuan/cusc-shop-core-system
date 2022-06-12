package facade;

import entity.TopTenCustomer;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TopTenCustomerFacadeLocal {
  void create(TopTenCustomer topTenCustomer);

  void edit(TopTenCustomer topTenCustomer);

  void remove(TopTenCustomer topTenCustomer);

  TopTenCustomer find(Object id);

  List<TopTenCustomer> findAll();

  List<TopTenCustomer> findRange(int[] range);

  int count();
}
