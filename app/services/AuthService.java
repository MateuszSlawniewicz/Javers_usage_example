package services;

import models.User;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.password.JBCryptPasswordEncoder;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.pac4j.sql.profile.DbProfile;
import org.pac4j.sql.profile.service.DbProfileService;
import org.postgresql.ds.PGSimpleDataSource;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;

import static play.mvc.Results.ok;
import static play.mvc.Results.unauthorized;


//*******
// {
//   "mail":"testowy@user.pl",
//   "password":"admin"
//   }
//   *******//

//TODO raport
//TODO front IONIC

// playKamila
//   http://paperquotes.com/pricing/
//  https://enxg4s9nzv55o.x.pipedream.net/


public class AuthService {

    private static DbProfileService dbProfileService;
    private final static String JWT_SALT = "12345678901234567890123456789012";
    private final static SecretSignatureConfiguration ssc = new SecretSignatureConfiguration(JWT_SALT);

    static {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/login");
        ds.setPassword("admin");
        ds.setUser("postgres");
        dbProfileService = new DbProfileService(ds);
        dbProfileService.setPasswordEncoder(new JBCryptPasswordEncoder());
    }


    public Result login(final Http.Request request, final WebContext webContext) {
        Optional<User> optionalUser = request.body().parseJson(User.class);
        return optionalUser.map(user ->
        {
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user.getMail(), user.getPassword());
            try {
                return validUserCredentials(credentials, webContext);
            } catch (Exception e) {
                return unauthorized(e.getMessage());
            }
        }).orElseThrow(() -> new RuntimeException("wrong type of user"));
    }

    private Result validUserCredentials(UsernamePasswordCredentials credentials, WebContext webContext) {
        dbProfileService.validate(credentials, webContext);
        CommonProfile commonProfile = new CommonProfile();
        commonProfile.setClientName(credentials.getUsername());
        String token = prepareTokenForUser();
        return ok(token);
    }

    private String prepareTokenForUser() {
        DbProfile dbProfile = new DbProfile();
        JwtGenerator<DbProfile> generator = new JwtGenerator<>(ssc);
        return generator.generate(dbProfile);
    }


    public boolean jwtValidation(Optional<String> optionalToken) {
        return optionalToken.map(e -> {
            JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(ssc);
            CommonProfile commonProfile = jwtAuthenticator.validateToken(optionalToken.get());
            return !(commonProfile == null);
        }).orElse(false);
    }
}




