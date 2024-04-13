package com.example.bank.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long>, JpaSpecificationExecutor<Clients> {
    @Query("SELECT Clients FROM Clients")
    List<Clients> findDepositsByClien();
}
