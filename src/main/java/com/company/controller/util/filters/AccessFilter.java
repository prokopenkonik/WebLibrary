package com.company.controller.util.filters;

import com.company.controller.util.PagesPaths;
import com.company.model.domain.Administrator;
import com.company.model.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        boolean passed = false;
        String path = request.getRequestURI();
        Administrator admin = (Administrator) session.getAttribute("admin");
        if (admin == null) {
            if (path.matches(PagesPaths.ADD_BOOK_JSP)
                    || path.matches(PagesPaths.GET_BOOK_TO_UPDATE_JSP)
                    || path.matches(PagesPaths.GET_ORDERS_FOR_ADMIN_JSP)) {
                passed = true;
                response.sendError(403);
            }
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            if (path.matches(PagesPaths.GET_ORDERS_FOR_USER_JSP)) {
                passed = true;
                response.sendError(403);
            }
        }
        if (!passed) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
