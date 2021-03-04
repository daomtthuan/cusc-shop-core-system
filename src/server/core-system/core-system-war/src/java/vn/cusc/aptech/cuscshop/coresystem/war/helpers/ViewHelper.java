package vn.cusc.aptech.cuscshop.coresystem.war.helpers;

public class ViewHelper {

    public static String redirect(String name) {
        return name + ".html?faces-redirect=true";
    }

}
