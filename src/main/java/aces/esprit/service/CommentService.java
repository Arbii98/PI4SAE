package aces.esprit.service;

import java.util.List;
import java.util.Optional;

import aces.esprit.entity.Comment;
import aces.esprit.entity.CommentPk;
import aces.esprit.entity.RatingComment;

public interface CommentService {
	Comment addComment(Comment com);

	void updateComment(Comment com, String description);

	void deleteById(CommentPk commentpk);

	Optional<Comment> getCommentById(Comment commentpk);

	List<Comment> getCommentByIdPublication(int idPub);

	List<RatingComment> getRatingComment();

	void addRatForComment(RatingComment rat, int idUser);
	
	Long countComment(int idPub);

}
