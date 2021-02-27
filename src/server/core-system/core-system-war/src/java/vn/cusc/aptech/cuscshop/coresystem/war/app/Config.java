package vn.cusc.aptech.cuscshop.coresystem.war.app;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import vn.cusc.aptech.cuscshop.coresystem.war.app.config.*;

@Named(value = "config")
@ApplicationScoped
public class Config implements Serializable {

    private final AppConfig app;
    private final DirectoryConfig directory;
    private final VendorsConfig vendors;

    public Config() {
        this.app = new AppConfig(
                "CUSC Shop", // name
                "CUSC Shop Core System", // description
                "Dao Minh Trung Thuan, Dang Quang Nghi, Nguyen Phuc Duy, Ho Quang Tam", // author
                "cusc, eproject, aptech, cusc shpp, core system" // keywords
        );

        this.directory = new DirectoryConfig(
                "/WEB-INF/layouts", // layouts
                "/WEB-INF/components" // components
        );

        this.vendors = new VendorsConfig(
                new String[]{
                    "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
                }, // styles
                new String[]{
                    "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
                } // scripts
        );
    }

    public AppConfig getApp() {
        return app;
    }

    public DirectoryConfig getDirectory() {
        return directory;
    }

    public VendorsConfig getVendors() {
        return vendors;
    }

}
