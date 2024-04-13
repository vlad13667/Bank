package com.example.bank.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long>, JpaSpecificationExecutor<Deposit> {
    // Дополнительные методы, если необходимо
    @Query("SELECT d FROM Deposit d JOIN Clients c ON d.client_id = c.client_id WHERE c.name = :clientName")
    List<Deposit> findDepositsByClientName(@Param("clientName") String clientName);






}
