package org.hls.check;

import org.keycloak.authorization.client.ClientAuthenticator;
import org.keycloak.authorization.client.Configuration;

import java.util.List;
import java.util.Map;

public class JwtClientAuthenticator implements ClientAuthenticator {

    private Map<String, Object> credentials;

    public JwtClientAuthenticator(Configuration configuration) {
        credentials = (Map<String, Object>) configuration.getCredentials().get("jwt");
    }

    private Map<String, Object> getCredentials() {
        return credentials;
    }

    @Override
    public void configureClientCredentials(Map<String, List<String>> requestParams, Map<String, String> requestHeaders) {
        String secret = (String) getCredentials().get("client-key-password");

        if (secret == null) {
            throw new RuntimeException("Client secret not provided.");
        }

        requestHeaders.put("Authorization", null);
    }
}
