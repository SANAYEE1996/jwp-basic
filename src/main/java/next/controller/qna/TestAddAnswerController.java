package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.JsonView;
import core.mvc.TestController;
import next.dao.AnswerDao;
import next.model.Answer;

public class TestAddAnswerController implements TestController{
	
	
	private static final Logger log = LoggerFactory.getLogger(TestAddAnswerController.class);
	
	@Override
	public JsonView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"),
                Long.parseLong(req.getParameter("questionId")));
        log.debug("answer : {}", answer);

        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(answer);
		return new JsonView(req,resp);
	}
	
	

}
