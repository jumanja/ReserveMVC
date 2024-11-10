package com.jm.ReserveMVC.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "card_identity")
    private Integer cardIdentity;

    @Column(name = "admission_of_date")
    private LocalDate admissionOfDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(mappedBy = "client")
    private Set<Reserve> reserves = new HashSet<>();

    public Client() {
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCardIdentity() {
        return cardIdentity;
    }

    public void setCardIdentity(Integer cardIdentity) {
        this.cardIdentity = cardIdentity;
    }

    public LocalDate getAdmissionOfDate() {
        return admissionOfDate;
    }

    public void setAdmissionOfDate(LocalDate admissionOfDate) {
        this.admissionOfDate = admissionOfDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(Set<Reserve> reserves) {
        this.reserves = reserves;
    }
}
