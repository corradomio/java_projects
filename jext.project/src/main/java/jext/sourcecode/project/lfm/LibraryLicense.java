package jext.sourcecode.project.lfm;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;

public class LibraryLicense {

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static final LibraryLicense NO_LICENSE = new LibraryLicense("", "");

    public static LibraryLicense none() {
        return NO_LICENSE;
    }

    public static LibraryLicense of(String type) {
        return of(type, "");
    }

    public static LibraryLicense of(String type, File file) {
        String url = String.format("file:///%s", file.getAbsolutePath());
        url = url.replace('\\', '/');
        return of(type, url);
    }

    public static LibraryLicense of(String type, String url) {
        if (type == null)
            type = "";
        if (type.isEmpty() && url.isEmpty())
            return NO_LICENSE;
        else
            return new LibraryLicense(type, url);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String type;
    private final String url;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private LibraryLicense(String type, String url) {
        this.type = type;
        this.url = url;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @JsonIgnore
    public boolean isValid() {
        if (type.isEmpty() && url.isEmpty())
            return true;
        if (!type.isEmpty())
            return true;
        else
            return false;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("%s (%s)", type, url);
    }

    @Override
    public int hashCode() {
        return type.hashCode()*31 + url.hashCode();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
