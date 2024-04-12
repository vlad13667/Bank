package com.example.bank.service;

import com.example.bank.model.Bank;
import com.example.bank.model.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public ResponseEntity<Bank> save(Bank bank)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */

        this.bankRepository.save(bank);
        return ResponseEntity.status(HttpStatus.OK).body(bank);

    }
    public boolean delete(Long id)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        Optional<Bank> foundBank = bankRepository.findById(id);
        Bank bank = foundBank.get();
        this.bankRepository.delete(bank);
        return ResponseEntity.status(HttpStatus.OK).body(foundBank).hasBody();

    }

}
