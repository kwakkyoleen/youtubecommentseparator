package io.dasom.comment;

import java.util.List;

import com.google.api.services.youtube.model.CommentThread;

public class CommentContainer {
	public CommentContainer(String videoCode) {
		this.videoCode = videoCode;
		this.nextPageToken = "";
		// TODO Auto-generated constructor stub
	}
	
	private String videoCode;
	private String nextPageToken;
	List<CommentElement> videoComment;

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	
	
	public List<CommentElement> getVideoComment() {
		return videoComment;
	}

	public void setVideoComment(List<CommentElement> videoComment) {
		this.videoComment = videoComment;
	}




	public class CommentElement{
		private String authorDisplayName;
		private String authorProfileImageUrl;
		private String textDisplay;
		private String lang;
		
		CommentElement(String authorDisplayName, String authorProfileImageUrl, String textDisplay, String lang){
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
	
}
