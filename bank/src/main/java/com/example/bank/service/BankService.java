package com.example.bank.service;

import com.example.bank.model.Bank;
import com.example.bank.model.BankRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BankService {


    private BankRepository bankRepository;

    public ResponseEntity<Bank> save(Bank bank)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        bankRepository.save(bank);
        return ResponseEntity.status(HttpStatus.OK).body(bank);

    }

}
