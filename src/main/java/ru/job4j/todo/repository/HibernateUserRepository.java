package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class HibernateUserRepository implements UserStore {
    private CrudRepository crudRepository;
    private final static String FIND = "FROM User WHERE login = :login AND password = :password";

    @Override
    public Optional<User> add(User user) {
        try {
            crudRepository.run(session -> session.merge(user));
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByLoginAndPassword(User user) {
        return crudRepository.optional(FIND, User.class,
                Map.of(
                        "login", user.getLogin(),
                        "password", user.getPassword()
                )
        );
    }
}
