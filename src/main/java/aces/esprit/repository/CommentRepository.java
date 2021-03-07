package aces.esprit.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import aces.esprit.entity.Comment;
import aces.esprit.entity.CommentPk;

public interface CommentRepository extends CrudRepository<Comment, CommentPk>{


}
