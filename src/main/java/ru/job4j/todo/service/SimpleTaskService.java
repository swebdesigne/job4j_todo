package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.HibernateTaskRepository;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final HibernateTaskRepository hibernateTaskRepository;


    @Override
    public List<Task> findAll() {
        return hibernateTaskRepository.findAll();
    }

    @Override
    public List<Task> getCompleted(boolean status) {
        return hibernateTaskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getNew(boolean status) {
        return hibernateTaskRepository.findByStatus(status);
    }

    @Override
    public Optional<Task> getById(int id) {
        return hibernateTaskRepository.getById(id);
    }

    @Override
    public boolean update(Task task) {
        return hibernateTaskRepository.update(task);
    }

    @Override
    public void add(Task task) {
        hibernateTaskRepository.add(task);
    }

    @Override
    public boolean delete(int id) {
        return hibernateTaskRepository.delete(id);
    }
}
