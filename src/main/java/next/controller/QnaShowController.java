package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.QuestionsDao;
import next.model.Questions;

public class QnaShowController implements Controller {
	
	
	private static final Logger log = LoggerFactory.getLogger(QnaShowController.class);
	
	@Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.debug("qna show 제대로 오는지");
        
        String questionsId = req.getParameter("questionsId");
        log.debug("제대로 받아와요? {}", questionsId);
        QuestionsDao qdao = new QuestionsDao();
        Questions questions = qdao.findByQuestionsId(questionsId);
        if(questions == null)
        	throw new NullPointerException("등록된 글이 아닙니다.");
        
        req.setAttribute("questions", questions);
        log.debug("제대로 받아와요? {}", questions);
        return "/qna/show.jsp";
    }

}
