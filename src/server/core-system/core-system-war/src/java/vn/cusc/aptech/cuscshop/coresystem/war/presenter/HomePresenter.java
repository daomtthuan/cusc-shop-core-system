package vn.cusc.aptech.cuscshop.coresystem.war.presenter;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import vn.cusc.aptech.cuscshop.coresystem.ejb.facade.AccountFacadeLocal;

@Named(value = "homePresenter")
@ViewScoped
public class HomePresenter implements Serializable {

    @EJB
    private AccountFacadeLocal accountFacade;

    public HomePresenter() {
    }

    public String sayHello() {
        return accountFacade.find(1).getUsername();
    }

}
