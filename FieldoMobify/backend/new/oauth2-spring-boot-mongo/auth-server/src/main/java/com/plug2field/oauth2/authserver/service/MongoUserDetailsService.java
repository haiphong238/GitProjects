package com.plug2field.oauth2.authserver.service;

import com.plug2field.oauth2.authserver.document.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("company").is(username));
        Account user =
                mongoTemplate.findOne(query, Account.class);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }

        String[] roles = new String[user.getRoles().size()];

        return new User(user.getCompany(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles().toArray(roles)));
    }
}