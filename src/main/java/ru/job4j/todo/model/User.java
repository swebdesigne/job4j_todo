package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "todo_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String login;
    private String password;

    public User() {
    }

    public User(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
