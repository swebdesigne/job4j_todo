package ru.job4j.todo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class AuthFilter implements Filter {
    private final Set<String> uri = Set.of(
            "/",
            "user/loginPage",
            "user/login",
            "user/add",
            "user/create",
            "user/success",
            "user/error",
            "/css/style.css"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (isExistsUri(uri)) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }
        chain.doFilter(req, res);
    }

    private boolean isExistsUri(String findUri) {
        return uri.stream().anyMatch(findUri::endsWith);
    }
}
