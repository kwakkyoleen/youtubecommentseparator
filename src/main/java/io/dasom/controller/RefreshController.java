package io.dasom.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThreadListResponse;

import io.dasom.comment.CommentContainer;
import io.dasom.externelapi.YoutubeCommentApi;

@RestController
@RequestMapping("/ycs/api")
public class RefreshController {

	@GetMapping(value="/searchvideo")
	public CommentContainer langApi(@RequestParam("videoCode") String videoCode) {
		CommentContainer commentContainer = new CommentContainer(videoCode);
		CommentThreadListResponse response;
		try {
			YouTube youtubeService = YoutubeCommentApi.getService();
	        // Define and execute the API request
			YouTube.CommentThreads.List request = youtubeService.commentThreads()
		            .list("snippet");
		         response = request.setKey(YoutubeCommentApi.getDeveloperKey())
		            .setVideoId(videoCode)
		            .execute();
	         commentContainer.setVideoComment(response.getItems());
	         
		}catch(GeneralSecurityException e) {
			
		}catch(IOException e) {
			
		}
		
		
		return commentContainer;
	}
}
