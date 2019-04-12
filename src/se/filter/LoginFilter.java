package se.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
        HttpServletRequest httpRequest = (HttpServletRequest)req;

        HttpSession session = httpRequest.getSession();
        // todo change uid to user
        String uid = (String)session.getAttribute("uid");

        System.out.println("uid ==" + uid);             //输出获得的session中属性的值
        if(uid == null) {
            HttpServletResponse response = (HttpServletResponse)resp;
            response.getWriter().write("<script>alert('Please Login to access this page')</script>");
            return;
        }else
            chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
