package jext.sourcecode.project.csharp.util;

import jext.util.logging.Logger;
import jext.maven.MavenCoords;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    <?xml version="1.0" encoding="utf-8"?>
    <packages>
        <package id="CNTK.CPUOnly" version="2.7.0" targetFramework="net45" />
        <package id="CNTK.Deps.MKL" version="2.7.0" targetFramework="net45" />
        <package id="CNTK.Deps.OpenCV.Zip" version="2.7.0" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi.Client" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi.Core" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi.WebHost" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.Web.Infrastructure" version="1.0.0.0" targetFramework="net45" />
        <package id="Newtonsoft.Json" version="6.0.8" targetFramework="net45" />
        <package id="Swashbuckle" version="5.3.1" targetFramework="net45" />
        <package id="Swashbuckle.Core" version="5.3.1" targetFramework="net45" />
        <package id="System.IdentityModel.Tokens.Jwt" version="4.0.0" targetFramework="net45" />
        <package id="WebActivatorEx" version="2.0.6" targetFramework="net45" />
    </packages>
 */

public class PackagesConfig {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(PackagesConfig.class);

    private File packagesConfigFile;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PackagesConfig(File packagesConfigFile) {
        if (packagesConfigFile.isDirectory())
            packagesConfigFile = new File(packagesConfigFile, "packages.config");
        this.packagesConfigFile = packagesConfigFile;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public List<MavenCoords> getLibraries() {
        if (!packagesConfigFile.exists())
            return Collections.emptyList();

        List<MavenCoords> libraries = new ArrayList<>();
        try {
            Element packages = XPathUtils.parse(packagesConfigFile).getDocumentElement();
            XPathUtils.selectElements(packages, "package")
                    .forEach(pkg -> {
                        String name = XPathUtils.getValue(pkg, "@id");
                        String version = XPathUtils.getValue(pkg, "@version");

                        libraries.add(MavenCoords.of(name, version));
                    });
        }
        catch(Exception e) {
            logger.warnf("Unable to parse %s", FileUtils.getAbsolutePath(packagesConfigFile));
        }

        return libraries;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
