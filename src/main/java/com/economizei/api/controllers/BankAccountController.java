package com.economizei.api.controllers;

import com.economizei.api.domain.bankaccount.model.BankAccount;
import com.economizei.api.domain.bankaccount.model.dto.DataBankAccountDto;
import com.economizei.api.domain.bankaccount.model.dto.RegisterBankAccountDto;
import com.economizei.api.domain.bankaccount.model.dto.UpdateBalanceDto;
import com.economizei.api.domain.bankaccount.service.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Bank Accounts", description = "APIs for managing bank accounts")
@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Operation(summary = "Register a new bank account")
    @PostMapping("/register")
    public ResponseEntity<DataBankAccountDto> register(@RequestBody @Valid RegisterBankAccountDto dto, UriComponentsBuilder uriBuilder) {
        DataBankAccountDto created = bankAccountService.registerBankAcoount(dto);

        URI uri = uriBuilder
                .path("/bank-accounts/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Operation(summary = "Get bank account by user ID")
    @GetMapping("/{userId}")
    public ResponseEntity<DataBankAccountDto> getBankAccountByUserId(@PathVariable Long userId) {
        BankAccount bankAccount = bankAccountService.getBankAccountByUserId(userId);

        return ResponseEntity.ok(new DataBankAccountDto(bankAccount));
    }


    @Operation(summary = "Update the balance of a bank account")
    @PutMapping("/balance")
    public ResponseEntity<DataBankAccountDto> updateBalance(@RequestBody @Valid UpdateBalanceDto dto) {
        DataBankAccountDto updated = bankAccountService.updateBalance(dto);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Disable a bank account")
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id) {
        bankAccountService.disableBankAccount(id);

        return ResponseEntity.noContent().build();
    }
}