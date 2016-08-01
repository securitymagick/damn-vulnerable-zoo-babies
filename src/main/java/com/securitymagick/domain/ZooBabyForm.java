package com.securitymagick.domain;

/**
 * @author leggosgirl
 *
 */
public class ZooBabyForm {

	private String title;
	private String author;
	private String imageName;
	
	public ZooBabyForm() {
		super();
	}
	
	
	public ZooBabyForm(String title, String author, String imageName) {
		super();
		this.title = title;
		this.author = author;
		this.imageName = imageName;
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
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "ZooBabyForm [" + "title=" + title + ", author=" + author + "imageName=" + imageName + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
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
		ZooBabyForm other = (ZooBabyForm) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;		
		return true;
	}
	
	
	
}
