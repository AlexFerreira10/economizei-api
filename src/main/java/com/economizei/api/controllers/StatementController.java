package com.economizei.api.controllers;

import com.economizei.api.domain.statement.model.dto.DataStatementDto;
import com.economizei.api.domain.statement.service.StatementService;
import com.economizei.api.domain.transaction.model.dto.DataTransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Statements", description = "APIs for retrieving statements")
@RestController
@RequestMapping("/statements")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @Operation(summary = "Get current monthly statement for a user")
    @GetMapping("/user/{userId}/current")
    public ResponseEntity<DataStatementDto> getCurrentForUser(@PathVariable Long userId) {
        LocalDate firstOfMonth = LocalDate.now().withDayOfMonth(1);

        return ResponseEntity.ok(new DataStatementDto(
                statementService.getOrCreateStatement(userId, firstOfMonth)
        ));
    }

    @Operation(summary = "List income transactions for a statement")
    @GetMapping("/{id}/incomes")
    public ResponseEntity<List<DataTransactionDto>> listIncomes(@PathVariable Long id) {
        return ResponseEntity.ok(statementService.listIncomeMonthly(id));
    }

    @Operation(summary = "List expense transactions for a statement")
    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<DataTransactionDto>> listExpenses(@PathVariable Long id) {
        return ResponseEntity.ok(statementService.listExpenseMonthly(id));
    }
}