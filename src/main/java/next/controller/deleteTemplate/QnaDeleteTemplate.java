package next.controller.deleteTemplate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

public class QnaDeleteTemplate {
	
	private static final Logger log = LoggerFactory.getLogger(QnaDeleteTemplate.class);
	
	public boolean deleteSuccess(long questionId, String writerId) {
		AnswerDao answerDao = new AnswerDao();
		QuestionDao questionDao = new QuestionDao();
		List<Answer> answerList = answerDao.findAllByQuestionId(questionId);
		if(answerList.size() == 0 || isAnswerWriterSameWithQuestionWriter(answerList, writerId)) {
			questionDao.delete(questionId);
			log.debug("무사히 삭제 되었습니다.");
			return true;
		}
		log.debug("답변자 중 글쓴이와 다른 사람이 한명이라도 있거나 답글 갯수가 1개 이상입니다.");
		return false;
	}
	
	private boolean isAnswerWriterSameWithQuestionWriter(List<Answer> answerList, String questionWriter) {
		for(Answer answer : answerList) {
			if(!answer.getWriterId().equals(questionWriter)) {
				return false;
			}
		}
		return true;
	}
}
