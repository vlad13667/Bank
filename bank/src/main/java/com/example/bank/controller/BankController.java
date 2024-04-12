package com.example.bank.controller;

import ch.qos.logback.core.net.server.Client;
import com.example.bank.model.Bank;
import com.example.bank.model.Clients;
import com.example.bank.model.Deposit;
import com.example.bank.service.BankService;
import com.example.bank.service.ClientService;
import com.example.bank.service.DepositService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BankController {

    // Загрузка  новой сущности
    @Autowired
    private ClientService clientService;
    @Autowired
    private BankService bankService;
    @Autowired
    private DepositService depositService;

    @PostMapping("/clients")

    public ResponseEntity<?> createClient(@RequestBody Clients client) throws URISyntaxException {
        clientService.create(client);
        return ResponseEntity.created(new URI("/clients/" + client)).build();
    }

    @PostMapping(value = "/banks")
    public ResponseEntity<?> createBank(@RequestBody Bank bank) throws URISyntaxException {
        bankService.save(bank);
        return ResponseEntity.created(new URI("/banks/" + bank.getId())).build();
    }

    @PostMapping("/deposits")
    public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit) throws URISyntaxException {
        depositService.save(deposit);
        return ResponseEntity.created(new URI("/deposits/" + deposit.getId())).build();
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> deleteClients(@PathVariable Long id) {
        final boolean deleted = clientService.deleteClients(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/bank/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = bankService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/deposits/{id}")
    public ResponseEntity<?> deleteDeposits(@PathVariable Long id) {
        boolean isDeleted = depositService.deleteDeposits(id);

        if (isDeleted) {
            return ResponseEntity.ok("Файл с именем '" + id + "' удалён.");
        } else {
            //throw new FileNotFoundException("Файла с именем '" + id + "' несуществует.");
        }
        return null;
    }

   


}
