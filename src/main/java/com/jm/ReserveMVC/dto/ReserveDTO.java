package com.jm.ReserveMVC.dto;

public class ReserveDTO {
    private Long id;
    private Long product_id;
    private Long client_id;
    private String date;

    public ReserveDTO() {
    }

    public ReserveDTO(Long id, Long product_id, Long client_id, String date) {
        this.id = id;
        this.product_id = product_id;
        this.client_id = client_id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
