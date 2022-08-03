package next.model;

public class Questions {
	private String questionId;
	private String writer;
	private String title;
	private String contents;
	private String createdDate;
	private int countOfAnswer;
	
	public Questions(String questionId, String writer, 
					 String title, String contents, 
					 String createdDate, int countOfAnswer){
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
		this.countOfAnswer = countOfAnswer;
	}
	
	public String getQuestionId() {
		return questionId;
	}
	public String getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public String getContents() {
		return contents;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public int getCountOfAnswer() {
		return countOfAnswer;
	}
	
}
