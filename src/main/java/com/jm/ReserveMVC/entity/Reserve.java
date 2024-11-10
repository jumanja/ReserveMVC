package com.jm.ReserveMVC.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserve")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;

    @Column(name = "date")
    private LocalDate date;

    public Reserve() {
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
