package com.example.javafxadvanced.Objects;

import java.sql.Timestamp;

public class Feedback {
    private int id;
    private int company_id;
    private int customer_id;
    private int user_id;
    private String type;
    private String message;
    private int rating;
    private Timestamp created_at;

    public Feedback(int id, int company_id, int customer_id, int user_id, String type, String message, int rating) {
        this.id = id;
        this.company_id = company_id;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.type = type;
        this.message = message;
        this.rating = rating;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
