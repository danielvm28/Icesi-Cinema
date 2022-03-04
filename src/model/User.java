package model;

public class User {
	//attribute
	private String name;
	private String id;
	
	//relations
	private Chair chair;
	
	//methods
	public User(String name, String id, Chair chair) {
		this.name = name;
		this.id = id;
		this.setChair(chair);
	}

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

	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) {
		this.chair = chair;
	}
}
