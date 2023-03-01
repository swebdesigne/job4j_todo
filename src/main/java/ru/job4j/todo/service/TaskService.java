package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
     List<Task> findAll();

     List<Task> getCompleted(boolean status);

     List<Task> getNew(boolean status);

     Optional<Task> getById(int id);

     boolean update(Task task);

     void add(Task task);

     boolean delete(int id);

     boolean complete(int id);
}
