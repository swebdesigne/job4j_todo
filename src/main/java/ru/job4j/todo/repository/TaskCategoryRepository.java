package ru.job4j.todo.repository;

import ru.job4j.todo.model.Category;

import java.util.List;

public interface TaskCategoryRepository {
    List<Category> findAll();

    List<Category> findByIDS(List<Integer> ids);
}
