package vn.cusc.aptech.cuscshop.coresystem.ejb.facades;

import java.util.List;
import javax.ejb.Local;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.BillDetails;

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
