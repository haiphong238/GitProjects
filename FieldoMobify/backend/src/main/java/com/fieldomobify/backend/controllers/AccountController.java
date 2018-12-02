package com.fieldomobify.backend.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fieldomobify.backend.model.Account;
import com.fieldomobify.backend.repositories.AccountRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AccountController {
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
//		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
//		return accountRepository.findAll(sortByCreatedAtDesc);
		return accountRepository.findAll();
		
	}

	@PostMapping("/accounts")
	public Account createAccount(@Valid @RequestBody Account account) {
		return accountRepository.save(account);
	}

	@GetMapping(value = "/accounts/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable("id") String id) {
		return accountRepository.findById(id).map(account -> ResponseEntity.ok().body(account))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/accounts/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable("id") String id, @Valid @RequestBody Account account) {
		accountRepository.save(account);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/accounts/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") String id) {
		return accountRepository.findById(id).map(account -> {
			accountRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
