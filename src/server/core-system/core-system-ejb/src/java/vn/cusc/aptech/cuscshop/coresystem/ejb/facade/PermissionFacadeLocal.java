package vn.cusc.aptech.cuscshop.coresystem.ejb.facade;

import java.util.List;
import javax.ejb.Local;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entity.Permission;

@Local
public interface PermissionFacadeLocal {

    void create(Permission permission);

    void edit(Permission permission);

    void remove(Permission permission);

    Permission find(Object id);

    List<Permission> findAll();

    List<Permission> findRange(int[] range);

    int count();
    
}
