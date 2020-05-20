package modules;

import com.google.inject.AbstractModule;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.play.store.PlayCacheSessionStore;
import org.pac4j.play.store.PlaySessionStore;

public class SecurityModule extends AbstractModule {
    private final static String JWT_SALT = "12345678901234567890123456789012";

    @Override
    protected void configure() {
        bind(PlaySessionStore.class).to(PlayCacheSessionStore.class);
        ParameterClient parameterClient = new ParameterClient("token", new JwtAuthenticator(new SecretSignatureConfiguration(JWT_SALT)));
        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(true);

        HeaderClient headerClient = new HeaderClient("token", new JwtAuthenticator(new SecretSignatureConfiguration(JWT_SALT)));

        final Clients clients = new Clients(parameterClient, headerClient);
        final Config config = new Config(clients);

        bind(Config.class).toInstance(config);
    }


}






