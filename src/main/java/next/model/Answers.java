package next.model;

public class Answers {
	private String answerId;
	private String writer;
	private String contents;
	private String createdDate;
	private String questionId;
	
	public Answers(	String answerId, String writer,String contents,
					String createdDate,String questionId) {
		this.answerId = answerId;
		this.writer = writer;
		this.contents = contents;
		this.createdDate = createdDate;
		this.questionId = questionId;
	}
	
	public String getAnswerId() {
		return answerId;
	}
	public String getWriter() {
		return writer;
	}
	public String getContents() {
		return contents;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getQuestionId() {
		return questionId;
	}
	
}
