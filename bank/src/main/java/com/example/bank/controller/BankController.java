package com.example.bank.controller;

import ch.qos.logback.core.net.server.Client;
import com.example.bank.model.Bank;
import com.example.bank.model.ClientDTO;
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
import java.util.Date;
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

    @GetMapping("/clients")
    public ResponseEntity<?> getClientsNames(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String shortName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Enum form,
            @RequestParam(required = false) List<String> types) throws IOException {

        return ClientService.loadAllFiltered(name, shortName, address, form);

    }
    @GetMapping("/bank")
    public ResponseEntity<?> getBankNames(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bik,
            @RequestParam(required = false) List<String> types) throws IOException {

        return bankService.loadAllFiltered(name,bik);

    }

    @GetMapping("/deposit")
    public ResponseEntity<?> getDepositNames(
            @RequestParam(required = false) String clientName,
            //@RequestParam(required = false) String bankName,
            @RequestParam(required = false) Date openingDate,
            @RequestParam(required = false) Float percent,
            @RequestParam(required = false) Integer termMonths,
            @RequestParam(required = false) List<String> types) throws IOException {

        return depositService.loadAllFiltered(clientName ,openingDate,percent,termMonths);

    }



    @GetMapping("/deposits")
    public ResponseEntity<?> getDepositqNames() throws FileNotFoundException {

        return depositService.loadAll();

    }
    @GetMapping("/deposi")
    public List<Clients> getAllDeposits() {
        return ClientService.getAllDeposits();
    }

    @GetMapping("/clientss")
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> clients = clientService.getClients(); // Предполагается, что у вас есть сервис, который реализует эту логику
        return ResponseEntity.ok(clients);
    }



}
