package facade;

import entity.CategoryGroup;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CategoryGroupFacadeLocal {
  void create(CategoryGroup categoryGroup);

  void edit(CategoryGroup categoryGroup);

  void remove(CategoryGroup categoryGroup);

  CategoryGroup find(Object id);

  List<CategoryGroup> findAll();

  List<CategoryGroup> findRange(int[] range);

  int count();
}
