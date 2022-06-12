package facade;

import entity.TopTenProduct;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TopTenProductFacadeLocal {
  void create(TopTenProduct topTenProduct);

  void edit(TopTenProduct topTenProduct);

  void remove(TopTenProduct topTenProduct);

  TopTenProduct find(Object id);

  List<TopTenProduct> findAll();

  List<TopTenProduct> findRange(int[] range);

  int count();
}
