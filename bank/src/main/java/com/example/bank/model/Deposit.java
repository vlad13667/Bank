package com.example.bank.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deposit_id;


    @Column(nullable = false)
    private long client;



    @Column(nullable = false)
    private long bank;

    @Column(nullable = false)
    private Date openingDate;

    @Column(nullable = false)
    private Integer percent;

    @Column(nullable = false)
    private Integer termInMonths;

    public Long getId() {
        return deposit_id;
    }

    public void setId(Long id) {
        this.deposit_id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long id) {
        this.client = id;
    }

    public Long getBank() {
        return bank;
    }

    public void setBank(Long bank) {
        this.bank = bank;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
    }
}