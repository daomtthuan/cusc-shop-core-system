/*
 * The MIT License
 *
 * Copyright 2021 Daomtthuan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package vn.cusc.aptech.cscs.war.app.config;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import vn.cusc.aptech.cscs.war.app.config.modules.*;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "configApp")
@ApplicationScoped
public class ConfigApp implements Serializable {

  private Meta meta;
  private Directory directory;
  private Asset resource;

  @PostConstruct
  public void init() {
    meta = new Meta(
      "CUSC Shop", // name
      "1.0.0", // version
      "CUSC Shop Core System", // description
      "Dao Minh Trung Thuan, Dang Quang Nghi, Nguyen Phuc Duy, Ho Quang Tam", // author
      "cusc, eproject, aptech, cusc shpp, core system", // keywords
      "width=device-width, initial-scale=1" // viewport
    );

    directory = new Directory(
      "/WEB-INF/layouts", // layouts
      "/WEB-INF/components" // components
    );

    resource = new Asset(
      new StyleAsset[] {
        new StyleAsset("shortcut icon", // rel
          directory.getRoot() + "/assets/icons/favicon.ico" // href
        ),
        new StyleAsset("preconnect", // rel
          "https://fonts.gstatic.com"
        ),
        new StyleAsset("stylesheet", // rel
          "https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" // href
        ),
        new StyleAsset("stylesheet", // rel
          "https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.2/css/all.min.css" // href
        ),
        new StyleAsset("stylesheet", // rel
          directory.getRoot() + "/assets/styles/theme.css" // href
        ),
        new StyleAsset("stylesheet", // rel
          directory.getRoot() + "/assets/styles/custom.css" // href
        ),
        new StyleAsset("stylesheet", // rel
          "https://cdn.jsdelivr.net/npm/malihu-custom-scrollbar-plugin@3.1.5/jquery.mCustomScrollbar.min.css", // href
          new String[] {"blank"} // excludes
        )
      }, //styles
      new ScriptAsset[] {
        new ScriptAsset("https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"),
        new ScriptAsset("https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"),
        new ScriptAsset(directory.getRoot() + "/assets/scripts/custom.js"),
        new ScriptAsset("https://cdn.jsdelivr.net/npm/malihu-custom-scrollbar-plugin@3.1.5/jquery.mCustomScrollbar.concat.min.js", // src
          new String[] {"blank"} // excludes
        )
      } // scripts
    );
  }

  public String getPlugin(String name) {
    if (name.equalsIgnoreCase("datatables.min.js")) {
      return "https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js";
    }

    if (name.equalsIgnoreCase("datatables.bootstrap4.min.js")) {
      return "https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js";
    }

    if (name.equalsIgnoreCase("dataTables.bootstrap4.min.css")) {
      return "https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css";
    }

    return null;
  }

  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }

  public Directory getDirectory() {
    return directory;
  }

  public void setDirectory(Directory directory) {
    this.directory = directory;
  }

  public Asset getResource() {
    return resource;
  }

  public void setResource(Asset resource) {
    this.resource = resource;
  }

}
