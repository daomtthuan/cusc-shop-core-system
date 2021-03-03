package vn.cusc.aptech.cuscshop.coresystem.war.app.config;

import javax.faces.context.FacesContext;

public class DirectoryConfig {

    private final String layouts;
    private final String components;
    private final String root;

    public DirectoryConfig(final String layouts, final String components) {
        this.layouts = layouts;
        this.components = components;
        this.root = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public String getLayouts() {
        return layouts;
    }

    public String getComponents() {
        return components;
    }

    public String getRoot() {
        return root;
    }

}
