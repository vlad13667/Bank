package com.example.bank.service;

import com.example.bank.model.Bank;
import com.example.bank.model.BankRepository;
import com.example.bank.model.ClientRepository;
import com.example.bank.model.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    public ResponseEntity<Clients> create(Clients client)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */

        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(client);

    }
    public boolean deleteClients(Long id)
    {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        Optional<Clients> foundClient = clientRepository.findById(id);
        Clients clients = foundClient.get();
        this.clientRepository.delete(clients);
        return ResponseEntity.status(HttpStatus.OK).body(foundClient).hasBody();

    }


}
