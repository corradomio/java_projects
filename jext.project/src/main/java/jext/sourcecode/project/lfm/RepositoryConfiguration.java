package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;

public class RepositoryConfiguration {

    private String name;
    private String url;

    public void configure(Configuration configuration) {
        this.name = configuration.getString("@name", "");
        this.url = configuration.getString("@url");
        if (this.name.isEmpty())
            this.name = this.url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
