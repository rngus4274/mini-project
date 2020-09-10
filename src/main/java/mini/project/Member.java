package mini.project;

import java.util.ArrayList;

public class Member {
	private String name;
	private String id;
	private String password;
	private String rating;
	private ArrayList<String> rentBookList;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public ArrayList<String> getRentBookList() {
		return rentBookList;
	}
	public void setRentBookList(ArrayList<String> rentBookList) {
		this.rentBookList = rentBookList;
	}
}
