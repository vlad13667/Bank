package com.example.bank.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bank_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String bik;



    // Добавляем связь с депозитами
    @OneToMany(mappedBy = "bank")
    private List<Deposit> deposits;

    // Метод для установки списка депозитов
    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    // Метод для получения списка депозитов
    /*
    public List<Deposit> getDeposits() {
        return deposits;
    }

     */

    public Long getId() {
        return bank_id;
    }

    public void setId(Long id) {
        this.bank_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }
}