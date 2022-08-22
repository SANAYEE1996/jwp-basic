package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

public class QnaUpdateController extends AbstractController{
	
	
	private static final Logger log = LoggerFactory.getLogger(QnaUpdateController.class);
	
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		log.debug("수정 하려는 글 : {}", request.getParameter("questionId"));
		questionDao.update(new Question(Long.parseLong(request.getParameter("questionId")), request.getParameter("title"),request.getParameter("contents")));
		return jspView("redirect:/");	
	}

}
