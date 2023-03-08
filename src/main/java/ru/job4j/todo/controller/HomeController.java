package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.utils.UserHttpSessionUtil;

import javax.servlet.http.HttpSession;

@Controller
@ThreadSafe
public class HomeController {
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        var user = UserHttpSessionUtil.getUser(session);
        if (user.getName().equals("Гость")) {
            model.addAttribute("user", user);
            return "home";
        }
        return "redirect:/tasks/all";
    }
}
