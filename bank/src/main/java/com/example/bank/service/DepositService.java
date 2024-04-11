package com.example.bank.service;

import com.example.bank.model.ClientRepository;
import com.example.bank.model.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    private DepositService depositService;

    @Autowired
    public DepositService(DepositService depositService) {
        this.depositService = depositService;
    }



    public ResponseEntity<Deposit> save(Deposit deposit)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        depositService.save(deposit);
        return ResponseEntity.status(HttpStatus.OK).body(deposit);

    }
}
