package ru.job4j.todo.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy, HH:mm:ss")
    private LocalDateTime created = LocalDateTime.now();
    private boolean done;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }

    public Task(int id, String name, String description, LocalDateTime created, boolean done, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Task(int id, String name, String description, LocalDateTime created, boolean done) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
    }
}
