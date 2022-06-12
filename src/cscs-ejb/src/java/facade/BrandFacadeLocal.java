package facade;

import entity.Brand;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BrandFacadeLocal {
  void create(Brand brand);

  void edit(Brand brand);

  void remove(Brand brand);

  Brand find(Object id);

  List<Brand> findAll();

  List<Brand> findRange(int[] range);

  int count();
}
