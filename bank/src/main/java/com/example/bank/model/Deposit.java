package com.example.bank.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deposit_id;


    @Column(insertable=false, updatable=false)
    private Long client_id;
    @Column(insertable=false, updatable=false)
    private Long bank_id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Clients client;

    // Связь с банком
    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "bank_id")
    private Bank bank;

    // Предполагаем, что у вас есть свойство для хранения id банка
    // @Column(insertable = false, updatable = false)
    // private Long bankId;

    @Column(nullable = false)
    private Date openingDate;

    @Column(nullable = false)
    private Float percent;

    @Column(nullable = false)
    private Integer termMonths;

    // Методы для установки и получения клиента и банка
    public void setClient(Clients client) {
        this.client = client;
    }
/*
    public Clients getClient() {
        return client;
    }

 */

    public void setBank(Bank bank) {
        this.bank = bank;
    }


    public Bank getBank() {
        return bank;
    }



    public Long getId() {
        return deposit_id;
    }

    public void setId(Long id) {
        this.deposit_id = id;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    public Integer getTermInMonths() {
        return termMonths;
    }

    public void setTermInMonths(Integer termInMonths) {
        this.termMonths = termInMonths;
    }
}