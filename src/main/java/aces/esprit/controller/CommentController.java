package aces.esprit.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Comment;
import aces.esprit.entity.Publication;
import aces.esprit.service.CommentService;

@RestController
@RequestMapping("Comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping
	public ResponseEntity<Void> addComment(@Valid @RequestBody Comment com) {

		if (commentService.addComment(com) != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

	}

	@PutMapping("{idPub}")
	public void updateComment(@RequestBody Comment com, @RequestBody String description) {
		commentService.updateComment(com, description);
	}

	@GetMapping("{idPub}")
	public List<Comment> getCommentByIdPublication(@PathVariable int idPub) {
		return commentService.getCommentByIdPublication(idPub);
	}
	@GetMapping(value = "/getnomb/{idPub}")
	public Long countComment(@PathVariable int idPub) {
		return commentService.countComment(idPub);
	}
	

}
