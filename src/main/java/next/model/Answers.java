package next.model;

public class Answers {
	private String answerId;
	private String writerId;
	private String contents;
	private String createdDate;
	private String questionId;
	
	public Answers(	String answerId, String writerId,String contents,
					String createdDate,String questionId) {
		this.answerId = answerId;
		this.writerId = writerId;
		this.contents = contents;
		this.createdDate = createdDate;
		this.questionId = questionId;
	}
	
	public String getAnswerId() {
		return answerId;
	}
	public String getWriterId() {
		return writerId;
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
