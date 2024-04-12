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
    private long client_id;



    @Column(nullable = false)
    private long bank_id;

    @Column(nullable = false)
    private Date openingDate;

    @Column(nullable = false)
    private Integer percent;

    @Column(nullable = false)
    private Integer termMonths;

    public Long getId() {
        return deposit_id;
    }

    public void setId(Long id) {
        this.deposit_id = id;
    }

    public Long getClient() {
        return client_id;
    }

    public void setClient(Long id) {
        this.client_id = id;
    }

    public Long getBank() {
        return bank_id;
    }

    public void setBank(Long bank) {
        this.bank_id = bank;
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
        return termMonths;
    }

    public void setTermInMonths(Integer termInMonths) {
        this.termMonths = termInMonths;
    }
}