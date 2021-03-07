package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Comment;
import aces.esprit.entity.CommentPk;
import aces.esprit.entity.RatingComment;
import aces.esprit.service.CommentService;
@RestController
@RequestMapping("rating")
public class RatingCommentController {
	@Autowired
	CommentService commentService;
	
	@PostMapping("{idUser}")
	public void addRatForComment(@RequestBody RatingComment rat,@PathVariable int idUser) {
		commentService.addRatForComment(rat, idUser);
	}
	
	@GetMapping()
	public List<RatingComment> getCommentByIdPublication(){
		return commentService.getRatingComment();
	}
}
