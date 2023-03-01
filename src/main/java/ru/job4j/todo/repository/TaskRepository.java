package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void add(Task task);
    List<Task> findAll();
    boolean update(Task task);
    Optional<Task> getById(int id);
    boolean delete(int id);
    List<Task> findByStatus(boolean status);
    boolean complete(int id);
}
