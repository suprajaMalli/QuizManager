package fr.epita.quiz.datamodel;

public class Student {

	private String name,id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Student(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	
}
