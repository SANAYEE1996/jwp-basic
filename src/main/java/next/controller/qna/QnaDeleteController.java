package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.controller.deleteTemplate.QnaDeleteTemplate;

public class QnaDeleteController extends AbstractController{

	
	private static final Logger log = LoggerFactory.getLogger(QnaDeleteController.class);
	private QnaDeleteTemplate qnaDeleteTemplate = new QnaDeleteTemplate();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!UserSessionUtils.isLogined(request.getSession())) {
			log.debug("로그인이나 하십시오.");
			return jspView("redirect:/user/login.jsp");
		}
		String writerId = request.getParameter("writerId");
		String userId = UserSessionUtils.getUserFromSession(request.getSession()).getUserId();
		long questionId = Long.parseLong(request.getParameter("questionId"));
		if(!writerId.equals(userId)) {
			log.debug("삭제하려는 사람이랑 글 쓴 사람의 아이디가 같지 않습니다.");
			return jspView("redirect:/");
		}
		if(qnaDeleteTemplate.deleteSuccess(questionId, writerId)) {
			return jspView("redirect:/");
		}
		return jspView("redirect:/qna/show?questionId="+request.getParameter("questionId"));
	}

}
