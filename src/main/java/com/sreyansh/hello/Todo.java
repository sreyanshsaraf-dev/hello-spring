package com.sreyansh.hello;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean done = false;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public Todo() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }
    public Instant getCreatedAt() { return createdAt; }
}