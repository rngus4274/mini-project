package mini.project;

import java.util.ArrayList;

public class Book {
	private String id; 
	private String title;
	private String author;
	private String plot;
	private String content;
	private ArrayList<String> rentMemberList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ArrayList<String> getRentMemberList() {
		return rentMemberList;
	}
	public void setRentMemberList(ArrayList<String> rentMemberList) {
		this.rentMemberList = rentMemberList;
	}
	
}
