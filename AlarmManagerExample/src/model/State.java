package model;

public class State {
	private static State instance = null;
	private String email;
	private String access_token = null;
	private boolean isLoggedIn = false;
	
	private Post newPost;

	public static State getInstance() {
		if (instance == null) {
			instance = new State();
		}
		return instance;
	}
	
	public Post getNewPost() {
		return this.newPost;
	}
	
	public void setNewPost(Post newPost) {
		this.newPost = newPost;
	}
	
	public void setAccessToken(String a) {
		this.access_token = a;
		
	}
	
	public String getAccessToken() {
		return this.access_token;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean b) {
		isLoggedIn = b;
	}
	
}
