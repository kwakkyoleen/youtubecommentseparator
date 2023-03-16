package io.dasom.comment;

import java.util.ArrayList;
import java.util.List;

public class CommentContainer {
	public CommentContainer(String videoCode) {
		this.videoCode = videoCode;
		this.nextPageToken = "";
		this.videoComment = new ArrayList<>();
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
	
}
