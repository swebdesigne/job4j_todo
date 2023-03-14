package ru.job4j.todo.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy, HH:mm:ss")
    private LocalDateTime created = LocalDateTime.now();
    private boolean done;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    public Task() {
    }

    public Task(int id, String name, String description, LocalDateTime created, boolean done, User user, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Task(int id, String name, String description, LocalDateTime created, boolean done, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
    }
}
