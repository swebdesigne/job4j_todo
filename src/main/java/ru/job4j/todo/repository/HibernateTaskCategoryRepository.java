package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

@Repository
@ThreadSafe
@AllArgsConstructor
public class HibernateTaskCategoryRepository implements TaskCategoryRepository {
    private final CrudRepository crudRepository;
    private final static String FIND_ALL = "FROM Category";
    private final static String FIND_BY_ID = "FROM Category WHERE id IN :fIDS";

    @Override
    public List<Category> findAll() {
        return crudRepository.query(FIND_ALL, Category.class);
    }

    @Override
    public List<Category> findByIDS(List<Integer> ids) {
        return crudRepository.query(FIND_BY_ID, Category.class,
                Map.of("fIDS", ids)
        );
    }
}
