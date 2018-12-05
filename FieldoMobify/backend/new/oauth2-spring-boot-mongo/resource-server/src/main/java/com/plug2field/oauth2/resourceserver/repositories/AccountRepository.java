package com.plug2field.oauth2.resourceserver.repositories;

import com.plug2field.oauth2.resourceserver.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findById(String id);

    void deleteById(String id);

    List<Account> findByEmail(String email);

    Account findByCompany(String company);

}
