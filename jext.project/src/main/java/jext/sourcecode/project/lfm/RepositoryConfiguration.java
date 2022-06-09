package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;

public class RepositoryConfiguration {

    private String name;
    private String url;

    public void configure(Configuration configuration) {
        name = configuration.getString("@name", "");
        url = configuration.getString("@url");
        if (name.isEmpty())
            urlToName();
    }

    private void urlToName() {
        int b, e;
        b = indexOf(":///");
        if (b == -1)
            b = indexOf("://");
        if (b == -1)
            b = indexOf(":/");
        if (b == -1)
            b = indexOf("/");
        if (b == -1)
            b = indexOf(":");

        e = indexOf("/", b+1);

        if (b != -1 && e != -1 && (b-e) > 1)
            name = url.substring(b, e);
        else
            name = url;
    }

    private int indexOf(String pat) {
        int p = url.indexOf(pat);
        if (p != -1)
            p += pat.length();
        return p;
    }

    private int indexOf(String pat, int start) {
        int p = url.indexOf(pat, start);
        return p;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
