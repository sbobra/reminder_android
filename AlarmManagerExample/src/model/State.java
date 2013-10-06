package model;

public class State {
	private static State instance = null;
	private String email;
	private boolean isLoggedIn;
	
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
	
}
