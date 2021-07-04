package org.hls.check;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.ClientAuthenticator;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.authorization.client.resource.ProtectedResource;
import org.keycloak.util.SystemPropertiesJsonParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class App {

    static Configuration getConfiguration() throws IOException {
        InputStream configStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("keycloak.json");

        ObjectMapper mapper = new ObjectMapper(new SystemPropertiesJsonParserFactory());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        Configuration configuration = mapper.readValue(configStream, Configuration.class);

        return configuration;
    }

    public static void main(String[] args) throws IOException {
        Configuration configuration = getConfiguration();
        ClientAuthenticator authenticator = new JwtClientAuthenticator(configuration);
        // configuration.setAuthServerUrl("http://localhost/keycloak");

        AuthzClient client = AuthzClient.create(configuration, authenticator);

        ProtectedResource resource = client.protection().resource();
    }
}
