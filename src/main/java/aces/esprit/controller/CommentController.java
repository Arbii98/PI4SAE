package aces.esprit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import aces.esprit.entity.Comment;
import aces.esprit.entity.CommentPk;
import aces.esprit.entity.Publication;
import aces.esprit.entity.RatingComment;
import aces.esprit.service.CommentService;

@RestController
@RequestMapping("Comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("{idPub}/{idUser}")
	public void addPublication(@RequestBody Comment com,@PathVariable int idPub,@PathVariable int idUser) {
		commentService.addComment(com, idPub,idUser);
	}

	@PutMapping("{idPub}")
	public void updatePublication(@RequestBody Comment com,@RequestBody String description) {
		commentService.updateComment(com, description);
	}
	@GetMapping("{idPub}")
	public List<Comment> getCommentByIdPublication(@PathVariable int idPub){
		return commentService.getCommentByIdPublication(idPub);
	}


}
