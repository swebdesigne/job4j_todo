package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.HibernatePriorityRepository;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class SimplePriorityService implements PropertyService {
    private HibernatePriorityRepository hibernatePriorityRepository;

    @Override
    public Optional<Priority> findById(int id) {
        return hibernatePriorityRepository.findById(id);
    }

    @Override
    public List<Priority> findAll() {
        return hibernatePriorityRepository.findAll();
    }
}
