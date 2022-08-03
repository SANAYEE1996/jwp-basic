package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.QuestionsDao;
import next.dao.UserDao;

public class HomeController implements Controller {
	
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserDao userDao = new UserDao();
        req.setAttribute("users", userDao.findAll());
        
        QuestionsDao qdao = new QuestionsDao();
        req.setAttribute("questions", qdao.findAll());
        log.debug("가져온 모든 질문글 : {}", qdao.findAll());
        return "home.jsp";
    }
}
