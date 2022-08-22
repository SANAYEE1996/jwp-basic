package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;

public class QnaUpdateFormController extends AbstractController{

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/users/loginForm");
        }
		String accessId = UserSessionUtils.getUserFromSession(request.getSession()).getUserId();
		String writerId = request.getParameter("writerId");
		if(!accessId.equals(writerId)) {
			return jspView("redirect:/");
		}
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = UserSessionUtils.getUserFromSession(request.getSession()).getName();
		ModelAndView mav = jspView("/qna/update.jsp");
		mav.addObject("questionId", questionId);
		mav.addObject("writer", writer);
		return mav;
	}

}
