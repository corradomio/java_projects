package org.hls.check;

import org.sonar.wsclient.SonarClient;

public class CheckSonarQube {

    public static void main(String[] args) {
        SonarClient sonarClient = SonarClient.create("http://localhost:9000");
        String sonarEndpoint = "";
    }
}
