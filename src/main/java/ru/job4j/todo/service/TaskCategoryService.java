package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;

import java.util.List;

public interface TaskCategoryService {
    List<Category> findAll();
    List<Category> findByIDS(List<Integer> ids);
}
