package com.fieldomobify.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fieldomobify.backend.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
	List<Account> findByEmail(String email);

}
