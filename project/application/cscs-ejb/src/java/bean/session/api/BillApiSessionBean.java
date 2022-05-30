package bean.session.api;

import java.util.ArrayList;
import java.util.Date;
import javafx.util.Pair;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import entity.facade.BillDetailsFacadeLocal;
import entity.facade.BillFacadeLocal;
import entity.facade.ProductFacadeLocal;
import entity.Bill;
import entity.BillDetails;
import entity.Customer;
import entity.Product;

@Stateless
public class BillApiSessionBean implements BillApiSessionBeanLocal {

  @EJB
  private ProductFacadeLocal productFacade;

  @EJB
  private BillFacadeLocal billFacade;

  @EJB
  private BillDetailsFacadeLocal billDetailsFacade;

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Bill addBill(Customer customer, ArrayList<Pair<Integer, Integer>> cart) {
    Bill bill = new Bill(null, new Date(), 1, true);
    bill.setCustomer(customer);
    billFacade.create(bill);

    for (Pair<Integer, Integer> cartDetails : cart) {
      Product product = productFacade.find(cartDetails.getKey());
      int quantity = cartDetails.getValue();

      BillDetails billDetails = new BillDetails(null, product.getPrice(), quantity);
      billDetails.setBill(bill);
      billDetails.setProduct(product);
      billDetailsFacade.create(billDetails);

      product.setQuantity(product.getQuantity() - quantity);
      productFacade.edit(product);
    }

    return bill;

  }

}
