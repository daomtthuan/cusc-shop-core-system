package facade;

import entity.Category;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CategoryFacadeLocal {
  void create(Category category);

  void edit(Category category);

  void remove(Category category);

  Category find(Object id);

  List<Category> findAll();

  List<Category> findRange(int[] range);

  int count();
}
