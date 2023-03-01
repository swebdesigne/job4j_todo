package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import lombok.val;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@ThreadSafe
@AllArgsConstructor
public class HibernateTaskRepository implements TaskRepository {
    private final SessionFactory sessionFactory;
    private final static String FIND_ALL = "from Task";
    private static final String DELETE = "DELETE Task WHERE id = :fId";
    private static final String GET_BY_ID = "from Task WHERE id = :fId";
    private static final String FIND_BY_STATUS = "FROM Task WHERE done = :fDone";
    private static final String COMPLETE = "UPDATE Task SET done = :fDone WHERE id = :fId";
    private final static String UPDATE = "UPDATE Task SET name = :fName, description = :fDescription WHERE id = :fId";

    @Override
    public void add(Task task) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Task> findAll() {
        Session session = sessionFactory.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            tasks = session.createQuery(FIND_ALL, Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

    @Override
    public boolean update(Task task) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            var res = session.createQuery(UPDATE)
                    .setParameter("fName", task.getName())
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fId", task.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            return res > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Optional<Task> getById(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Optional<Task> task = session.createQuery(GET_BY_ID, Task.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return task;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            val res = session.createQuery(DELETE)
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return res > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Task> findByStatus(boolean status) {
        Session session = sessionFactory.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            tasks = session.createQuery(FIND_BY_STATUS, Task.class)
                    .setParameter("fDone", status)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

    @Override
    public boolean complete(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            var res = session.createQuery(COMPLETE)
                    .setParameter("fDone", true)
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return res > 0;
        } catch (Exception e) {
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
