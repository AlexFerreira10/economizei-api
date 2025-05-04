package com.economizei.api.domain.statement.repository;

import com.economizei.api.domain.bankaccount.model.BankAccount;
import com.economizei.api.domain.statement.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
    Optional<Statement> findByBankAccountAndPeriod(BankAccount acct, LocalDate period);
    boolean existsByBankAccountAndPeriod(BankAccount acct, LocalDate period);
}
