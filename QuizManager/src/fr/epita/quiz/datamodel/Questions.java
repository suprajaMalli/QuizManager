package fr.epita.quiz.datamodel;

public class Questions {
	
	
	private String question;
	private String[] topics;
	private int difficulty;
	
	
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public Questions(String question, String[] topics, int difficulty) {
		super();
		this.question = question;
		this.topics = topics;
		this.difficulty = difficulty;
	}
	public Questions() {
		super();
	}

	public Questions(String question) {
		super();
		this.question = question;
	}
	
	
	
	public Questions(String question, int difficulty) {
		super();
		
		this.question = question;
		this.difficulty = difficulty;
	}
	
	


}
