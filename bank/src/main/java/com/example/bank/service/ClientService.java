package com.example.bank.service;

import com.example.bank.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private static ClientRepository clientRepository;
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
    public static ResponseEntity<Object> loadAllFiltered(String name, String shortName, String address, Enum form) throws FileNotFoundException {
        Specification<Clients> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(ClientHasName(name));
        }
        if (shortName != null && !shortName.isEmpty()) {
            spec = spec.and(ClientHasShortName(shortName));
        }
        if (address != null && !address.isEmpty()) {
            spec = spec.and(ClientHasAddress(address));
        }
        if (form != null) {
            spec = spec.and(ClientHasForm(form));
        }

        List<Clients> fileDataList = clientRepository.findAll(spec);

        if (fileDataList.isEmpty()) {
            throw new FileNotFoundException("Файла с введенными фильтрами не найден");
        }
        return ResponseEntity.ok().body(fileDataList);
    }

    private static Specification<Clients> ClientHasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Clients> ClientHasShortName(String shortName) {
        return (root, query, cb) -> cb.like(root.get("short_name"),"%" + shortName + "%");
    }

    private static Specification<Clients> ClientHasAddress(String address) {
        return (root, query, cb) -> cb.like(root.get("address"),"%" + address + "%");
    }

    private static Specification<Clients> ClientHasForm(Enum form) {
        return (root, query, cb) -> cb.equal(root.<Clients>get("form"), form);
    }

    public static List<Clients> getAllDeposits() {
        return clientRepository.findAll();
    }

    public static List<Clients> getAll() {
        return clientRepository.findAll();
    }

    public List<ClientDTO> getClients() {
        List<Clients> clients = clientRepository.findAll(); // Замените на ваш метод репозитория для извлечения всех клиентов
        return clients.stream()
                .map(client -> new ClientDTO(client.getId(), client.getName(), client.getShortName(), client.getAddress(), client.getForm()))
                .collect(Collectors.toList());
    }

    // Метод для создания DTO из сущности Client
    private ClientDTO newClientDTO(Clients client) {
        return new ClientDTO(client.getId(), client.getName(), client.getShortName(), client.getAddress(), client.getForm());
    }


}
