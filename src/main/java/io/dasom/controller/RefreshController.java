package io.dasom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.dasom.comment.CommentContainer;

@RestController
@RequestMapping("/ycs/api")
public class RefreshController {

	@GetMapping(value="")
	public CommentContainer langApi(@RequestParam("videoCode") String videoCode) {
		CommentContainer commentContainer = new CommentContainer(videoCode);
		return commentContainer;
	}
}
