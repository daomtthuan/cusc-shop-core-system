package cuscshop.coresystem.app;

import cuscshop.coresystem.app.config.*;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

@Named(value = "config")
@ApplicationScoped
public class Config implements Serializable {

    private AppConfig app;
    private DirectoryConfig directory;
    private VendorsConfig vendors;

    @PostConstruct
    public void initialize() {
        this.app = new AppConfig(
                "CUSC Shop", // name
                "CUSC Shop Core System", // description
                "Dao Minh Trung Thuan, Dang Quang Nghi, Nguyen Phuc Duy, Ho Quang Tam", // author
                "cusc, eproject, aptech, core system" // keywords
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

    public void setApp(AppConfig app) {
        this.app = app;
    }

    public DirectoryConfig getDirectory() {
        return directory;
    }

    public void setDirectory(DirectoryConfig directory) {
        this.directory = directory;
    }

    public VendorsConfig getVendors() {
        return vendors;
    }

    public void setVendors(VendorsConfig vendors) {
        this.vendors = vendors;
    }

}
