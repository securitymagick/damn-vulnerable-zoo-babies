package com.securitymagick.domain;

/**
 * @author leggosgirl
 *
 */
public class RegistrationForm {
	private String username;
	private String password;
	private String confirmPassword;
	private String favorite;
	
	public RegistrationForm() {
		super();
	}
	
	
	public RegistrationForm(String username, String password, String confirmPassword, String favorite) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.favorite = favorite;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}


	@Override
	public String toString() {
		return "RegistrationForm [username=" + username + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", favorite=" + favorite + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((favorite == null) ? 0 : favorite.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationForm other = (RegistrationForm) obj;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (favorite == null) {
			if (other.favorite != null)
				return false;
		} else if (!favorite.equals(other.favorite))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}
