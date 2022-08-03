package next.controller.answer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;

public class DeleteAnswerController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String answerId = req.getParameter("answerId");
		log.debug("answerId : {}", answerId);
		
		AnswerDao answerDao = new AnswerDao();
		answerDao.delete(answerId);
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("applicatoin/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(mapper.writeValueAsString(1));
		
		return null;
	}
}
