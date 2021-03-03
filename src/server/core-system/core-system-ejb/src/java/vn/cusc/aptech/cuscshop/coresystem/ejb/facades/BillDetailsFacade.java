package vn.cusc.aptech.cuscshop.coresystem.ejb.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.BillDetails;

@Stateless
public class BillDetailsFacade extends AbstractFacade<BillDetails> implements BillDetailsFacadeLocal {

    @PersistenceContext(unitName = "core-system-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BillDetailsFacade() {
        super(BillDetails.class);
    }
    
}
