package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.HibernateTaskCategoryRepository;

import java.util.List;

@Service
@ThreadSafe
@AllArgsConstructor
public class SimpleTaskCategory implements TaskCategoryService {
    private HibernateTaskCategoryRepository hibernateTaskCategoryRepository;

    @Override
    public List<Category> findAll() {
        return hibernateTaskCategoryRepository.findAll();
    }

    @Override
    public List<Category> findByIDS(List<Integer> ids) {
        return hibernateTaskCategoryRepository.findByIDS(ids);
    }
}
