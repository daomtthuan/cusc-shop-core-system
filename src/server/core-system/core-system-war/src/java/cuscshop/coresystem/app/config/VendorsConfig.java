package cuscshop.coresystem.app.config;

public class VendorsConfig {

    private String[] styles;
    private String[] scripts;

    public VendorsConfig(String[] styles, String[] scripts) {
        this.styles = styles;
        this.scripts = scripts;
    }

    public String[] getStyles() {
        return styles;
    }

    public void setStyles(String[] styles) {
        this.styles = styles;
    }

    public String[] getScripts() {
        return scripts;
    }

    public void setScripts(String[] scripts) {
        this.scripts = scripts;
    }

}
