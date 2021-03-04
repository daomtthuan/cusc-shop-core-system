/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.cusc.aptech.cuscshop.coresystem.war.presenters;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import vn.cusc.aptech.cuscshop.coresystem.ejb.entities.Account;
import vn.cusc.aptech.cuscshop.coresystem.ejb.facades.AccountFacadeLocal;

/**
 *
 * @author Nguyen Phuc Duy
 */
@Named(value = "testPresenter")
@ViewScoped
public class TestPresenter implements Serializable{

    @EJB
    private AccountFacadeLocal accountFacade;

    /**
     * Creates a new instance of TestPresenter
     */
    public TestPresenter() {
    }
    public List<Account> retrieveAllContacts(){
        return accountFacade.findAll();
    }
}
