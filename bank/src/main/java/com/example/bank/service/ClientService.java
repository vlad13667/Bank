package com.example.bank.service;

import com.example.bank.model.ClientRepository;
import com.example.bank.model.Clients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;

public class ClientService {
    private ClientRepository clientRepository;

    public ResponseEntity<Clients> save(Clients client)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(client);

    }


}
