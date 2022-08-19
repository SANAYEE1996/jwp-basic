package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;

public class QnaFormController extends AbstractController{
	
	
	private static final Logger log = LoggerFactory.getLogger(QnaFormController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/users/loginForm");
        }
		String username = UserSessionUtils.getUserFromSession(request.getSession()).getName();
		log.debug("user name : {}", username);
		return jspView("/qna/form.jsp").addObject("writer", username);
	}

}
