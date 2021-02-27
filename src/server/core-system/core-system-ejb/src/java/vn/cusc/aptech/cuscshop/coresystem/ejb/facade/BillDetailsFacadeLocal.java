package vn.cusc.aptech.cuscshop.coresystem.ejb.facade;

import java.util.List;
import javax.ejb.Local;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entity.BillDetails;

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
