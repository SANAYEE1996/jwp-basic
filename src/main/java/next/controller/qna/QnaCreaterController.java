package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

public class QnaCreaterController extends AbstractController{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(new Question(request.getParameter("writer"), request.getParameter("title"), request.getParameter("contents")));
		return jspView("redirect:/");
	}

}
