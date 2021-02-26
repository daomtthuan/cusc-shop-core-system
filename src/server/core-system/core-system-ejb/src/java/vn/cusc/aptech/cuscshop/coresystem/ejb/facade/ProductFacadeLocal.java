package vn.cusc.aptech.cuscshop.coresystem.ejb.facade;

import java.util.List;
import javax.ejb.Local;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entity.Product;

@Local
public interface ProductFacadeLocal {

    void create(Product product);

    void edit(Product product);

    void remove(Product product);

    Product find(Object id);

    List<Product> findAll();

    List<Product> findRange(int[] range);

    int count();
    
}
