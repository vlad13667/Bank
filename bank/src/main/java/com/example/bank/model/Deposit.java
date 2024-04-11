package com.example.bank.model;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Bank bank;

    @Column(nullable = false)
    private Date openingDate;

    @Column(nullable = false)
    private Integer percent;

    @Column(nullable = false)
    private Integer termInMonths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
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