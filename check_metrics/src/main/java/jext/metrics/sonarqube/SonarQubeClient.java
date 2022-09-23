package jext.metrics.sonarqube;

import java.util.Properties;

public class SonarQubeClient {

    private static final String SONAR_URL = "sonar.url";
    private static final String SONAR_LOGIN = "sonar.login";

    private final String url;
    private final String login;
    private final Properties props;

    public SonarQubeClient(Properties props) {
        this.url = props.getProperty(SONAR_URL);
        this.login = props.getProperty(SONAR_LOGIN);
        this.props = props;
    }

    public void connect() {

    }
}
