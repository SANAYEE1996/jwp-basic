package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import core.session.UserSessionUtils;
import next.model.User;


@WebServlet("/user/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RequestDispatcher getLoginPage = req.getRequestDispatcher("/user/login.jsp");
    	getLoginPage.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String userId = req.getParameter("userId");
    	String pw = req.getParameter("password");
    	User user = DataBase.findUserById(userId);
    	if(user != null && user.getUserId().equals(userId) && user.getPassword().equals(pw)) {
    		HttpSession session = req.getSession();
    		session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
        	resp.sendRedirect("/");
    	}
    	else {
    		req.setAttribute("isLogin", false);
    		resp.sendRedirect("/user/login_failed.jsp");
    	}
    	log.debug("logined User : {}", user);
    }

}
