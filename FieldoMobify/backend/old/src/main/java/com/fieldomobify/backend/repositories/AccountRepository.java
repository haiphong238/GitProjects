package com.fieldomobify.backend.repositories;

import java.util.List;

import com.fieldomobify.backend.model.security.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findByEmail(String email);

    Account findByCompany(String company);

}
