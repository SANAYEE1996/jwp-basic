package next.controller.answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.model.Result;

public class DeleteAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();
    
	private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if(!UserSessionUtils.isLogined(request.getSession())) {
			return jsonView().addObject("result", Result.fail("fail"));
		}
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        log.debug("지우려는 아이디 : {}",answerId);
        ModelAndView mav = jsonView();
        try {
            answerDao.delete(answerId);
            int answerCount = answerDao.getAnswerCount(Long.parseLong(request.getParameter("questionId")));
            mav.addObject("result", Result.ok());
            mav.addObject("answerId", answerId);
            mav.addObject("answerCount", answerCount);
        } catch (DataAccessException e) {
            mav.addObject("result", Result.fail(e.getMessage()));
        }
        return mav;
    }
}
