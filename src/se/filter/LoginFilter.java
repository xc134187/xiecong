/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.filter;

import se.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter {
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 1.check session if user exits
        HttpServletRequest httpRequest = (HttpServletRequest) req;

        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.getWriter().write("<script>alert('Please Login to access this page')</script>");
        } else
            chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
