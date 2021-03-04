package vn.cusc.aptech.cuscshop.coresystem.war.application;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import vn.cusc.aptech.cuscshop.coresystem.war.application.config.*;
import vn.cusc.aptech.cuscshop.coresystem.war.application.config.moudles.*;

@Named(value = "config")
@ApplicationScoped
public class Config implements Serializable {

    private final AppConfig app;
    private final DirectoryConfig directory;
    private final VendorConfig vendor;
    private final AssetConfig asset;

    public Config() {
        this.app = new AppConfig(
                "CUSC Shop", // name
                "1.0.0", // version
                "CUSC Shop Core System", // description
                "Dao Minh Trung Thuan, Dang Quang Nghi, Nguyen Phuc Duy, Ho Quang Tam", // author
                "cusc, eproject, aptech, cusc shpp, core system" // keywords
        );

        this.directory = new DirectoryConfig(
                "/WEB-INF/layouts", // layouts
                "/WEB-INF/components" // components
        );

        this.vendor = new VendorConfig(
                new StyleModule[]{
                    new StyleModule("preconnect", "https://fonts.gstatic.com"),
                    new StyleModule("stylesheet", "https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"),
                    new StyleModule("stylesheet", "https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.2/css/all.min.css"),
                    new StyleModule("stylesheet", "https://cdn.jsdelivr.net/npm/simple-line-icons@2.5.5/css/simple-line-icons.css"),
                    new StyleModule("stylesheet", "https://cdn.jsdelivr.net/npm/@icon/themify-icons@1.0.1-alpha.3/themify-icons.css")
                }, // styles
                new ScriptModule[]{
                    new ScriptModule("https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"),
                    new ScriptModule("https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js")
                } // scripts
        );

        this.asset = new AssetConfig(
                new StyleModule[]{
                    new StyleModule("shortcut icon", "icons/favicon.ico"),
                    new StyleModule("stylesheet", "styles/theme.css")
                }, // styles
                new ScriptModule[]{} // scripts
        );
    }

    public AppConfig getApp() {
        return app;
    }

    public DirectoryConfig getDirectory() {
        return directory;
    }

    public VendorConfig getVendor() {
        return vendor;
    }

    public AssetConfig getAsset() {
        return asset;
    }

}
