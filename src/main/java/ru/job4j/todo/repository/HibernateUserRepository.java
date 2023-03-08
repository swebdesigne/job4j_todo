package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class HibernateUserRepository implements UserStore {
    private final SessionFactory sessionFactory;
    private final static String FIND = "FROM User WHERE login = :login AND password = :password";

    @Override
    public Optional<User> add(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Optional<User> res = session.createQuery(FIND, User.class)
                    .setParameter("login", user.getLogin())
                    .setParameter("password", user.getPassword())
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return res;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
