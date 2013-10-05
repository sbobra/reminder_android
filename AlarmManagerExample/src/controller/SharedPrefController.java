package controller;

import org.apache.http.impl.cookie.BasicClientCookie;

import android.content.Context;

import com.loopj.android.http.*;

public class SharedPrefController {
	private static AsyncHttpClient myClient = new AsyncHttpClient();
	private PersistentCookieStore myCookieStore;

	public SharedPrefController(Context c) {
		myCookieStore = new PersistentCookieStore(c);
		myClient.setCookieStore(myCookieStore);
	}

	public void storeCookie(String name, String value) {
		BasicClientCookie newCookie = new BasicClientCookie(name, value);
		newCookie.setVersion(1);
		newCookie.setDomain("mydomain.com");
		newCookie.setPath("/");
		myCookieStore.addCookie(newCookie);
	}
	
	public Object getCookie(String name) {
		//TODO:
		myCookieStore.getCookies();
		return null;
	}
}
