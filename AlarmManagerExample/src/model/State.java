package model;

public class State {
	private static State instance = null;
	private String email;
	private boolean isLoggedIn;

	public static State getInstance() {
		if (instance == null) {
			instance = new State();
		}
		return instance;
	}
	
}
