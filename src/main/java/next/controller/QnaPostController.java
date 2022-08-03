package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.QuestionsDao;
import next.model.Questions;

public class QnaPostController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(QnaShowController.class);
	
	@Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.debug("qna form 제대로 오는지");
        
        Questions inputQuestions = 
        new Questions("", 	req.getParameter("writer"), 
			        		req.getParameter("title"), 
			        		req.getParameter("contents"),
			        		"", 0);
        log.debug("제대로 받아와요? {}", inputQuestions);
        QuestionsDao qdao = new QuestionsDao();
        qdao.insert(inputQuestions);
        
        log.debug("제대로 받아와요? ");
        return "redirect:/";
    }
}
