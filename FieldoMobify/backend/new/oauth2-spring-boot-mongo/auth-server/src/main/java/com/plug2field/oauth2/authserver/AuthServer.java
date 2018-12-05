package com.plug2field.oauth2.authserver;

import com.plug2field.oauth2.authserver.document.account.Account;
import com.plug2field.oauth2.authserver.document.token.MongoAccessToken;
import com.plug2field.oauth2.authserver.document.token.MongoAuthorizationCode;
import com.plug2field.oauth2.authserver.document.token.MongoClientDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
@ComponentScan("com.plug2field.oauth2.authserver")
public class AuthServer {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(AuthServer.class, args);

        if (args .length > 0 && "init".equalsIgnoreCase(args[0])) {

            MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

            mongoTemplate.dropCollection(Account.class);
            mongoTemplate.dropCollection(MongoClientDetails.class);
            mongoTemplate.dropCollection(MongoAccessToken.class);
            mongoTemplate.dropCollection(MongoAuthorizationCode.class);

            // init the users
            Account mongoUser = new Account();
            mongoUser.setCompany("user");
            mongoUser.setPassword("user");
            mongoUser.setRoles(Collections.singleton(("ROLE_USER")));
            mongoTemplate.save(mongoUser);

            // init the client details
            MongoClientDetails clientDetails = new MongoClientDetails();
            clientDetails.setClientId("web-client");
            clientDetails.setClientSecret("web-client-secret");
            clientDetails.setSecretRequired(true);
            clientDetails.setResourceIds(Collections.singleton("plug2Field-resource"));
            clientDetails.setScope(Collections.singleton("plug2Field"));
            clientDetails.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList("authorization_code", "refresh_token",
                    "password", "client_credentials")));
            clientDetails.setRegisteredRedirectUri(Collections.singleton("http://localhost:8082/resource-service"));
            clientDetails.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
            clientDetails.setAccessTokenValiditySeconds(60);
            clientDetails.setRefreshTokenValiditySeconds(14400);
            clientDetails.setAutoApprove(false);
            mongoTemplate.save(clientDetails);

        }
    }
}
