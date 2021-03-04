package vn.cusc.aptech.cuscshop.coresystem.war.application.config.moudles;

public class StyleModule {

    private final String rel;
    private final String href;

    public StyleModule(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }

}
