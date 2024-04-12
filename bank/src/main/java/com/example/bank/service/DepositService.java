package com.example.bank.service;

import com.example.bank.model.ClientRepository;
import com.example.bank.model.Clients;
import com.example.bank.model.Deposit;
import com.example.bank.model.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {
    private DepositRepository depositRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }



    public ResponseEntity<Deposit> save(Deposit deposit)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        depositRepository.save(deposit);
        return ResponseEntity.status(HttpStatus.OK).body(deposit);

    }

    public boolean deleteDeposits(Long id)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        Optional<Deposit> foundDeposit = depositRepository.findById(id);
        Deposit deposit = foundDeposit.get();
        this.depositRepository.delete(deposit);
        return ResponseEntity.status(HttpStatus.OK).body(foundDeposit).hasBody();

    }
}
