package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.SimpleTaskService;
import ru.job4j.todo.utils.TaskActionStatus;
import ru.job4j.todo.utils.TaskStatus;

import java.util.Optional;

@ThreadSafe
@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final SimpleTaskService simpleTaskService;

    public TaskController(SimpleTaskService simpleTaskService) {
        this.simpleTaskService = simpleTaskService;
    }

    @GetMapping("/all")
    public String allTask(Model model) {
        model.addAttribute("msgError", TaskStatus.NO_TASK.getStatus());
        model.addAttribute("isAll", "active");
        model.addAttribute("tasks", simpleTaskService.findAll());
        return "task/all";
    }

    @GetMapping("/completed")
    public String completedTask(Model model) {
        model.addAttribute("msgError", TaskStatus.NO_COMPLETED_TASK.getStatus());
        model.addAttribute("isCompleted", "active");
        model.addAttribute("tasks", simpleTaskService.getCompleted(true));
        return "task/all";
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("msgError", TaskStatus.NO_NEW_TASK.getStatus());
        model.addAttribute("isNew", "active");
        model.addAttribute("tasks", simpleTaskService.getNew(false));
        return "task/all";
    }

    @GetMapping("/info/{id}")
    public String taskInfo(Model model, @PathVariable("id") int id) {
        Optional<Task> opt = simpleTaskService.getById(id);
        model.addAttribute("isEmpty", opt.isEmpty());
        opt.ifPresent(task -> model.addAttribute("task", task));
        return "task/info";
    }

    @GetMapping("/edit/{id}")
    public String editTask(Model model, @PathVariable("id") int id) {
        Optional<Task> opt = simpleTaskService.getById(id);
        model.addAttribute("isEmpty", opt.isEmpty());
        opt.ifPresent(task -> model.addAttribute("task", task));
        return "task/edit";
    }

    @PostMapping("/update")
    public String updateTask(Model model, @ModelAttribute Task task) {
        if (!simpleTaskService.update(task)) {
            model.addAttribute("msgError", TaskActionStatus.NOT_UPDATE.getStatus());
            return "task/error";
        }
        return "redirect:/tasks/all";
    }

    @GetMapping("/create")
    public String createTask() {
        return "task/create";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        simpleTaskService.add(task);
        return "redirect:/tasks/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(Model model, @PathVariable("id") int id) {
        if (!simpleTaskService.delete(id)) {
            model.addAttribute("msgError", TaskActionStatus.NOT_DELETE.getStatus());
            return "task/error";
        }
        return "redirect:/tasks/all";
    }

    @GetMapping("/complete/{id}")
    public String completeTask(Model model, @PathVariable("id") int id) {
        if (!simpleTaskService.complete(id)) {
            model.addAttribute("msgError", TaskActionStatus.NOT_COMPLETE.getStatus());
            return "task/error";
        }
        return "redirect:/tasks/all";
    }
}
