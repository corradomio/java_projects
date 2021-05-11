package jext.versioning.util;

import java.util.Properties;

public class Authentication {

    private static final String USER = "user";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private Properties props;

    public Authentication(Properties props) {
        this.props = props;
    }

    public boolean isAuthenticated() {
        return getUsername() != null && getPassword() != null;
    }

    public String getUsername() {
        return props.getProperty(USER,
            props.getProperty(USERNAME, null));
    }

    public String getPassword() {
        return props.getProperty(PASSWORD, null);
    }
}