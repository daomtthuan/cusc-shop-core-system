package vn.cusc.aptech.cuscshop.coresystem.war.app.config;

public class VendorsConfig {

    private final String[] styles;
    private final String[] scripts;

    public VendorsConfig(String[] styles, String[] scripts) {
        this.styles = styles;
        this.scripts = scripts;
    }

    public String[] getStyles() {
        return styles;
    }

    public String[] getScripts() {
        return scripts;
    }

}
