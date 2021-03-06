package com.plug2field.oauth2.resourceserver.controllers;

import com.plug2field.oauth2.resourceserver.model.Account;
import com.plug2field.oauth2.resourceserver.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
    	return Collections.singletonList(new Account("id", "cpn", "email", ""));
        

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

    //Since we can register many accounts with the same email. When users login with an email, we must display all accounts using that email.
    @GetMapping("/email/{emailId}/accounts")
    public List<Account> getAccountsByEmail(@PathVariable("emailId") String emailId) {
        return accountRepository.findByEmail(emailId);
    }

}
