package vn.cusc.aptech.cuscshop.coresystem.war.app.config;

public class DirectoryConfig {

    private final String layouts;
    private final String components;

    public DirectoryConfig(String layouts, String components) {
        this.layouts = layouts;
        this.components = components;
    }

    public String getLayouts() {
        return layouts;
    }

    public String getComponents() {
        return components;
    }
}
