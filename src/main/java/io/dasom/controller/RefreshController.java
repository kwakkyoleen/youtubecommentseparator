package io.dasom.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;

import io.dasom.comment.CommentContainer;
import io.dasom.comment.CommentElement;
import io.dasom.externelapi.PapagoLanguageDetection;
import io.dasom.externelapi.YoutubeCommentApi;

@RestController
@RequestMapping("/ycs/api")
public class RefreshController {

	@GetMapping(value="/searchvideo")
	public CommentContainer langApi(@RequestParam("videoCode") String videoCode, @RequestParam("languages") String languages) {
		ExecutorService excutorService = Executors.newSingleThreadExecutor();
		
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
		List<PapagoCallable> callableList = new ArrayList<>();
		int iterationNum = 0;
		for(iterationNum = 0; iterationNum < rawComments.size(); iterationNum++) {
			callableList.add(new PapagoCallable(rawComments.get(iterationNum)));
		}
		try {
			List<Future<CommentElement>> futures = excutorService.invokeAll(callableList);
			for(Future<CommentElement> f : futures) {
				commentContainer.getVideoComment().add(f.get());
			}
		}catch(InterruptedException e) {
			System.out.println("InterrupedException is occured!");
		}catch(ExecutionException e) {
			System.out.println("ExecutionException is occured!");
		}
		
		//System.out.println(PapagoLanguageDetection.detectLang(rawComments.get(0).getSnippet().getTopLevelComment().getSnippet().getTextOriginal()));
		
		//System.out.println(rawComments.get(0));
		return commentContainer;
	}
	
	public static class PapagoCallable implements Callable<CommentElement>{
		private CommentThread commentThread;
		public PapagoCallable(CommentThread commentThread) {
			this.commentThread = commentThread;
		}
		@Override
		public CommentElement call() throws Exception {
			// TODO Auto-generated method stub
			//PapagoLanguageDetection.detectLang(commentString);
			return new CommentElement(commentThread.getSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName(),
					commentThread.getSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl(),
					commentThread.getSnippet().getTopLevelComment().getSnippet().getTextDisplay(),
					PapagoLanguageDetection.detectLang(commentThread.getSnippet().getTopLevelComment().getSnippet().getTextOriginal()));
		}
		
		
	}
}
