package com.example.bank.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "clients")
public class Clients {






            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            @Column(nullable = false)
            private String name;

            @Column(nullable = false)
            private String shortName;

            @Column(nullable = false)
            private String address;

            @Enumerated(EnumType.STRING)
            @Column(nullable = false)
            private OrganizationalForm form;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

           public OrganizationalForm getForm() {
                return form;
            }

            public void setForm(OrganizationalForm form) {
                this.form = form;
            }


          public enum OrganizationalForm {
              PRIVATE_PERSON, LEGAL_ENTITY
          }
        }



