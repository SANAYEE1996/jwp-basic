package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.session.UserSessionUtils;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	session.invalidate();
    	log.debug("session user delete ? {}", UserSessionUtils.USER_SESSION_KEY);
    	req.setAttribute("isLogin", false);
    	resp.sendRedirect("/");
    }
    
}
