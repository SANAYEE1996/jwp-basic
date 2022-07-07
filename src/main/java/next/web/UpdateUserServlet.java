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
import next.model.User;


@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
    	req.setAttribute("isLogin", session.getAttribute("user"));
		req.setAttribute("userId", req.getParameter("userId"));
		RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
    	req.setAttribute("isLogin", session.getAttribute("user"));
		User updateUser = new User(	req.getParameter("userId"), req.getParameter("password"), 
									req.getParameter("name"), req.getParameter("email"));
		DataBase.addUser(updateUser);
		log.debug("update User : {}", updateUser);
		resp.sendRedirect("/user/list");
    }
	
}
