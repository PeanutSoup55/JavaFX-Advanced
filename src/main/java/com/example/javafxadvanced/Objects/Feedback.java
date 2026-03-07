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


}
