package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.model.Result;
import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class DeleteAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();
    
	private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	log.debug("지우려는 아이디 : {}",request.getParameter("answerId").getClass().getName());
    	log.debug("지우려는 아이디 : {}",request.getParameter("answerId"));
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        log.debug("지우려는 아이디 : {}",request.getParameter("answerId"));
        ModelAndView mav = jsonView();
        try {
            answerDao.delete(answerId);
            mav.addObject("result", Result.ok());
            mav.addObject("answerId", answerId);
        } catch (DataAccessException e) {
            mav.addObject("result", Result.fail(e.getMessage()));
        }
        return mav;
    }
}
