package com.jpa.mappings.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Review Comment
    @Column(name = "comment", nullable = false, length = 500)
    private String comment;

    // Default Constructor (Required by JPA)
    public Review() {
    }

    // Parameterized Constructor
    public Review(String comment) {
        this.comment = comment;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for Comment
    public String getComment() {
        return comment;
    }

    // Setter for Comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    // toString Method for Debugging and Logging
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }

    // equals and hashCode based on ID (recommended for entities)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        return id == review.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
