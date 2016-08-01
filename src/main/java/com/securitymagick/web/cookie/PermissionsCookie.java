package com.securitymagick.web.cookie;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import com.securitymagick.domain.Permissions;
/**
 * @author leggosgirl
 *
 */
public class PermissionsCookie {
	private Cookie permissionsCookie = null;

	/**
	 * 
	 */
	public PermissionsCookie() {
		super();
	}
	
	public Boolean checkForCookie(HttpServletRequest request) {
		Boolean found = false;
		Cookie[] requestCookies = request.getCookies();
		if(requestCookies != null){
			for(Cookie c : requestCookies){
				if (c.getName().equals("permissions")) {
					found = true;
					permissionsCookie = c;
				}
			}
		}
		return found;
	}

	public void addCookie(HttpServletResponse response, Permissions permissions) {
		permissionsCookie = new Cookie("permissions", permissions.getCookieValue());
		response.addCookie(permissionsCookie);
	}
	
	public Cookie getPermissionsCookie() {
		return permissionsCookie;
	}
}