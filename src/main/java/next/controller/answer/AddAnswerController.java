package next.controller.answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class AddAnswerController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
    	if(!UserSessionUtils.isLogined(req.getSession())) {
			return jsonView().addObject("result", Result.fail("fail"));
		}
    	String userId = UserSessionUtils.getUserFromSession(req.getSession()).getUserId();
        Answer answer = new Answer(userId, req.getParameter("writer"), req.getParameter("contents"),
                Long.parseLong(req.getParameter("questionId")));
        log.debug("answer : {}", answer);
        Answer savedAnswer = answerDao.insert(answer);
        int answerCount = answerDao.getAnswerCount(Long.parseLong(req.getParameter("questionId")));
        log.debug("댓글 수 : {} ", answerCount);
        ModelAndView mv = jsonView();
        mv.addObject("answer", savedAnswer);
        mv.addObject("answerCount", answerCount);
        return mv;
    }
}
