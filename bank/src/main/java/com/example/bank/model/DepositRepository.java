package com.example.bank.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long>, JpaSpecificationExecutor<Deposit> {
    // Дополнительные методы, если необходимо

}
