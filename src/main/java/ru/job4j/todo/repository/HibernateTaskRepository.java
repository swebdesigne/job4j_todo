package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class HibernateTaskRepository implements TaskRepository {
    private SessionFactory sessionFactory;
    private final CrudRepository crudRepository;
    private final static String FIND_ALL = "from Task";
    private static final String DELETE = "DELETE Task WHERE id = :fId";
    private static final String GET_BY_ID = "from Task WHERE id = :fId";
    private static final String FIND_BY_STATUS = "FROM Task WHERE done = :fDone";

    @Override
    public void add(Task task) {
        crudRepository.run(session -> session.persist(task));
    }

    @Override
    public List<Task> findAll() {
        return crudRepository.query(FIND_ALL, Task.class);
    }

    @Override
    public boolean update(Task task) {
        try {
            crudRepository.run(session -> session.merge(task));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Task> getById(int id) {
        return crudRepository.optional(GET_BY_ID, Task.class,
                Map.of("fId", id)
        );
    }

    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run(DELETE, Map.of("fId", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Task> findByStatus(boolean status) {
        return crudRepository.query(FIND_BY_STATUS, Task.class,
                Map.of("fDone", status)
        );
    }
}
