package vn.cusc.aptech.cuscshop.coresystem.ejb.facade;

import java.util.List;
import javax.ejb.Local;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entity.Category;

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
