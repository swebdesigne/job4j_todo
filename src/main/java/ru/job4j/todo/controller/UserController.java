package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.SimpleUserService;
import ru.job4j.todo.utils.UserHttpSessionUtil;
import ru.job4j.todo.utils.UserStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@ThreadSafe
@Controller
@RequestMapping("/user")
public class UserController {
    private final SimpleUserService simpleUserService;

    public UserController(SimpleUserService simpleUserService) {
        this.simpleUserService = simpleUserService;
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        var zones = new ArrayList<TimeZone>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        model.addAttribute("zones", zones);
        return "user/create";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, HttpServletRequest rq) {
        Optional<User> optUser = simpleUserService.add(user);
        UserHttpSessionUtil.setUser(rq, optUser.orElse(null));
        if (optUser.isEmpty()) {
            return "redirect:/user/error";
        }
        return "redirect:/user/success";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/loginPage";
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute User user, HttpServletRequest rq) {
        Optional<User> optUser = simpleUserService.findByLoginAndPassword(user);
        UserHttpSessionUtil.setUser(rq, optUser.orElse(null));
        if (optUser.isEmpty()) {
            return "redirect:/user/loginPage";
        }
        return "redirect:/tasks/all";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        model.addAttribute("msg", UserStatus.USER_ALREADY_EXISTS.getStatus());
        return "user/error";
    }

    @GetMapping("/success")
    public String success(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/success";
    }
}
