package io.dasom.comment;

import java.util.List;

import com.google.api.services.youtube.model.CommentThread;

public class CommentContainer {
	public CommentContainer(String videoCode) {
		this.videoCode = videoCode;
		// TODO Auto-generated constructor stub
	}
	
	String videoCode;
	List<CommentThread> videoComment;

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}

	public List<CommentThread> getVideoComment() {
		return videoComment;
	}

	public void setVideoComment(List<CommentThread> videoComment) {
		this.videoComment = videoComment;
	}
	
	
	
}
