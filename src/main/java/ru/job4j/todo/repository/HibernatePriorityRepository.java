package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class HibernatePriorityRepository implements PriorityRepository {
    private final CrudRepository crudRepository;
    private final static String GET_BY_ID = "FROM Priority WHERE id = :fId";
    private final static String FIND_ALL = "FROM Priority p ORDER BY p.position";

    @Override
    public Optional<Priority> findById(int id) {
        return crudRepository.optional(GET_BY_ID, Priority.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Priority> findAll() {
        return crudRepository.query(FIND_ALL, Priority.class);
    }
}
