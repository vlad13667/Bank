package com.example.bank.service;

import com.example.bank.model.Bank;
import com.example.bank.model.BankRepository;
import com.example.bank.model.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
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


    public  ResponseEntity<Object> loadAllFiltered(String name, String bik) throws FileNotFoundException {
        Specification<Bank> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(BankHasName(name));
        }
        if (bik != null && !bik.isEmpty()) {
            spec = spec.and(BankHasBik(bik));
        }


        List<Bank> fileDataList = bankRepository.findAll(spec);

        if (fileDataList.isEmpty()) {
            throw new FileNotFoundException("Файла с введенными фильтрами не найден");
        }
        return ResponseEntity.ok().body(fileDataList);
    }

    private static Specification<Bank> BankHasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Bank> BankHasBik(String bik) {
        return (root, query, cb) -> cb.like(root.get("bik"),"%" + bik + "%");
    }


}
