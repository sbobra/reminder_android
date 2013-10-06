package model;

public class Post {
	
	int feeling;
	int functioning;
	int activityId;
	String note;
	
	public Post(int feeling, int functioning, int activityId, String note) {
		this.feeling = feeling;
		this.functioning = functioning;
		this.activityId = activityId;
		this.note = note;
	}
	
	public Post() {
		feeling = 0;
		functioning = 0;
		activityId = 0;
		note = null;
	}
	
	public void setFeeling(int feeling) {
		this.feeling =feeling;
	}
	
	public void setFunctioning(int functioning) {
		this.functioning = functioning;
	}
	
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public int getFeeling() {
		return feeling;
	}
	
	public int getFunctioning() {
		return functioning;
	}
	
	public int getActivityId() {
		return activityId;
	}
	
	public String getNote() {
		return note;
	}
}
