package io.dasom.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;

import io.dasom.comment.CommentContainer;
import io.dasom.externelapi.PapagoLanguageDetection;
import io.dasom.externelapi.YoutubeCommentApi;

@RestController
@RequestMapping("/ycs/api")
public class RefreshController {

	@GetMapping(value="/searchvideo")
	public CommentContainer langApi(@RequestParam("videoCode") String videoCode, @RequestParam("languages") String languages) {
		CommentContainer commentContainer = new CommentContainer(videoCode);
		List<CommentThread> rawComments = new ArrayList<>();
		CommentThreadListResponse response;
		try {
			
			for(int iterationNum = 0; iterationNum <= 3; iterationNum++) {
			
				YouTube youtubeService = YoutubeCommentApi.getService();
		        // Define and execute the API request
				YouTube.CommentThreads.List request = youtubeService.commentThreads()
			            .list("snippet");
			         response = request.setKey(YoutubeCommentApi.getDeveloperKey())
			        	.setMaxResults(50L)
			        	.setPageToken(commentContainer.getNextPageToken())
			            .setVideoId(videoCode)
			            .execute();
			         commentContainer.setNextPageToken(response.getNextPageToken());
			         rawComments.addAll(response.getItems());
			} 
		}catch(GeneralSecurityException e) {
			
		}catch(IOException e) {
			
		}
		
		System.out.println(PapagoLanguageDetection.detectLang(rawComments.get(0).getSnippet().getTopLevelComment().getSnippet().getTextOriginal()));
		
		//System.out.println(rawComments.get(0));
		return commentContainer;
	}
}
