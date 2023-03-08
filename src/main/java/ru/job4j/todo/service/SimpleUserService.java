package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.HibernateUserRepository;

import java.util.Optional;

@ThreadSafe
@Service
public class SimpleUserService implements UserService {
    private final HibernateUserRepository hibernateUserRepository;

    public SimpleUserService(HibernateUserRepository hibernateUserRepository) {
        this.hibernateUserRepository = hibernateUserRepository;
    }

    @Override
    public Optional<User> add(User user) {
        return hibernateUserRepository.add(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(User user) {
        return hibernateUserRepository.findByLoginAndPassword(user);
    }
}
