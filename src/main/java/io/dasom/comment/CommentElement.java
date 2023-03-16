package io.dasom.comment;

public class CommentElement {
	private String authorDisplayName;
	private String authorProfileImageUrl;
	private String textDisplay;
	private String lang;
	
	public CommentElement(String authorDisplayName, String authorProfileImageUrl, String textDisplay, String lang){
		this.authorDisplayName = authorDisplayName;
		this.authorProfileImageUrl = authorProfileImageUrl;
		this.textDisplay = textDisplay;
		this.lang = lang;
	}

	public String getAuthorDisplayName() {
		return authorDisplayName;
	}

	public void setAuthorDisplayName(String authorDisplayName) {
		this.authorDisplayName = authorDisplayName;
	}

	public String getAuthorProfileImageUrl() {
		return authorProfileImageUrl;
	}

	public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
		this.authorProfileImageUrl = authorProfileImageUrl;
	}

	public String getTextDisplay() {
		return textDisplay;
	}

	public void setTextDisplay(String textDisplay) {
		this.textDisplay = textDisplay;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}
