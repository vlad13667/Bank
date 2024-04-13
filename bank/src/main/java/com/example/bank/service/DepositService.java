package com.example.bank.service;

import com.example.bank.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {
    private static DepositRepository depositRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }


    public ResponseEntity<Deposit> save(Deposit deposit) {
        /*Clients oldFile = fileDataRepository.findByFileName(client.getOriginalFilename());
        if (oldFile != null) {
            fileDataRepository.delete(oldFile);
        }

         */
        depositRepository.save(deposit);
        return ResponseEntity.status(HttpStatus.OK).body(deposit);

    }

    public boolean deleteDeposits(Long id) {
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



    public static ResponseEntity<Object> loadAllFiltered(String clientName, Date openingDate, Float percent, Integer termMonths) throws FileNotFoundException {
        Specification<Deposit> spec = Specification.where(null);

        if (openingDate != null) {
            spec = spec.and(fileDataHasDateFrom(openingDate));
        }
        if (percent != null) {
            spec = spec.and(fileDataHasDateFrom1(percent));
        }

        if (termMonths != null) {
            spec = spec.and(fileDataHasDateFrom2(termMonths));
        }



        List<Deposit> fileDataList = depositRepository.findAll(spec);

        if (fileDataList.isEmpty()) {
            throw new FileNotFoundException("Файла с введенными фильтрами не найден");
        }
        return ResponseEntity.ok().body(fileDataList);
    }


    private static Specification<Deposit> DepositByClientAndBank(String clientName) {
        return (root, query, cb) -> {
            // Фильтрация по имени клиента
            return cb.like(cb.lower(root.get("client").get("name")), "%" + clientName.toLowerCase() + "%");
        };
    }

    private static Specification<Deposit> fileDataHasDateFrom(Date openingDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("openingDate").as(Date.class), openingDate);
    }
    private static Specification<Deposit> fileDataHasDateFrom1(Float percent) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("percent").as(Float.class), percent);
    }
    private static Specification<Deposit> fileDataHasDateFrom2(Integer termMonths) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("termMonths").as(Integer.class), termMonths);
    }


    public static ResponseEntity<Object> loadAll() throws FileNotFoundException {



        List<Deposit> fileDataList = depositRepository.findAll();


        return ResponseEntity.ok().body(fileDataList);
    }

    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }
}
