package aces.esprit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.Comment;
import aces.esprit.entity.RatingComment;
import aces.esprit.entity.User;

public interface RatingCommentRepository extends CrudRepository<RatingComment, Integer> {

	List<RatingComment> findByCommentAndUsers(Comment comment, User users);
}
