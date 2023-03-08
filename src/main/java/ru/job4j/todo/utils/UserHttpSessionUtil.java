package ru.job4j.todo.utils;

import ru.job4j.todo.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class UserHttpSessionUtil {
  private UserHttpSessionUtil() {
  }

  public static User getUser(HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      user = new User();
      user.setName("Гость");
    }
    return user;
  }

  public static void setUser(HttpServletRequest rq, User user) {
    HttpSession session = rq.getSession();
    session.setAttribute("user", user);
  }
}
