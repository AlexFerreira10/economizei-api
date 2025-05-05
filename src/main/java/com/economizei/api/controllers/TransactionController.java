package com.economizei.api.controllers;

import com.economizei.api.domain.transaction.model.dto.DataTransactionDto;
import com.economizei.api.domain.transaction.model.dto.RegisterTransactionDto;
import com.economizei.api.domain.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "APIs for creating transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<DataTransactionDto> register(@RequestBody @Valid RegisterTransactionDto dto, UriComponentsBuilder uriBuilder) {
        DataTransactionDto created = transactionService.registerTransaction(dto);

        URI uri = uriBuilder
                .path("/transactions/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(uri).body(created);
    }
}
