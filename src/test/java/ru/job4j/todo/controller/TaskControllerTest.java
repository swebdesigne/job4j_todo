package ru.job4j.todo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.SimplePriorityService;
import ru.job4j.todo.service.SimpleTaskCategory;
import ru.job4j.todo.service.SimpleTaskService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class TaskControllerTest {
    private TaskController taskController;
    private SimpleTaskService simpleTaskService;
    private SimplePriorityService simplePriorityService;
    private SimpleTaskCategory simpleTaskCategory;
    private final List<Integer> categoriesIDS = List.of(1, 2);


    private Model model;
    private HttpSession session;

    @BeforeEach
    public void initPostController() {
        simpleTaskService = mock(SimpleTaskService.class);
        session = mock(HttpSession.class);
        model = mock(Model.class);
        taskController = new TaskController(simpleTaskService, simplePriorityService, simpleTaskCategory);
    }

    @Test
    public void whenFindAllTask() {
        List<Task> tasks = new ArrayList<>(
                List.of(
                        Task.builder()
                                .id(1)
                                .name("Task 1")
                                .description("Create task 1")
                                .created(LocalDateTime.now())
                                .done(true).build(),
                        Task.builder()
                                .id(2)
                                .name("Task 2")
                                .description("Create task 2")
                                .created(LocalDateTime.now())
                                .done(false).build()
                )
        );
        when(simpleTaskService.findAll()).thenReturn(tasks);
        String page = taskController.allTask(model, session);
        verify(model).addAttribute("tasks", simpleTaskService.findAll());
        assertThat(page, is("task/all"));
    }

    @Test
    public void whenFindCompletedTask() {
        List<Task> tasks = new ArrayList<>(
                List.of(
                        Task.builder()
                                .id(1)
                                .name("Task 1")
                                .description("Create task 1")
                                .created(LocalDateTime.now())
                                .done(true).build(),
                        Task.builder()
                                .id(2)
                                .name("Task 2")
                                .description("Create task 2")
                                .created(LocalDateTime.now())
                                .done(false).build()
                )
        );
        when(simpleTaskService.getCompleted(true)).thenReturn(tasks);
        String page = taskController.completedTask(model, session);
        verify(model).addAttribute("tasks", simpleTaskService.getCompleted(true));
        assertThat(page, is("task/all"));
    }

    @Test
    public void whenFindNewTask() {
        List<Task> tasks = new ArrayList<>(
                List.of(
                        Task.builder()
                                .id(1)
                                .name("Task 1")
                                .description("Create task 1")
                                .created(LocalDateTime.now())
                                .done(true).build(),
                        Task.builder()
                                .id(2)
                                .name("Task 2")
                                .description("Create task 2")
                                .created(LocalDateTime.now())
                                .done(false).build()
                )
        );
        when(simpleTaskService.getNew(false)).thenReturn(tasks);
        String page = taskController.newTask(model, session);
        verify(model).addAttribute("tasks", simpleTaskService.getNew(false));
        assertThat(page, is("task/all"));
    }

    @Test
    public void whenAddTask() {
        Task task = Task.builder()
                .id(1)
                .name("Task 1")
                .description("Create task 1")
                .build();
        String page = taskController.addTask(task, session, categoriesIDS);
        verify(simpleTaskService).add(task, categoriesIDS);
        assertThat(page, is("redirect:/tasks/all"));
    }

    @Test
    public void whenUpdateTaskNotSuccessful() {
        Task task = Task.builder()
                .id(1)
                .name("Task 1")
                .description("Create task 1")
                .build();
        when(simpleTaskService.update(task, categoriesIDS)).thenReturn(false);
        String page = taskController.updateTask(model, task, session, categoriesIDS);
        verify(simpleTaskService).update(task, categoriesIDS);
        assertThat(page, is("task/error"));
    }

    @Test
    public void whenUpdateTaskSuccessful() {
        Task task = Task.builder()
                .id(1)
                .name("Task 1")
                .description("Create task 1")
                .build();
        when(simpleTaskService.update(task, categoriesIDS)).thenReturn(true);
        String page = taskController.updateTask(model, task, session, categoriesIDS);
        verify(simpleTaskService).update(task, categoriesIDS);
        assertThat(page, is("redirect:/tasks/all"));
    }

    @Test
    public void whenDeleteTaskSuccessful() {
        when(simpleTaskService.delete(1)).thenReturn(true);
        String page = taskController.deleteTask(model, 1, session);
        verify(simpleTaskService).delete(1);
        assertThat(page, is("redirect:/tasks/all"));
    }

    @Test
    public void whenDeleteTaskNotSuccessful() {
        when(simpleTaskService.delete(1)).thenReturn(false);
        String page = taskController.deleteTask(model, 1, session);
        verify(simpleTaskService).delete(1);
        assertThat(page, is("task/error"));
    }
}