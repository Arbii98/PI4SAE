package aces.esprit.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Comment;
import aces.esprit.entity.CommentPk;
import aces.esprit.entity.Publication;
import aces.esprit.entity.RatingComment;
import aces.esprit.entity.User;
import aces.esprit.repository.CommentRepository;
import aces.esprit.repository.PublicationRepository;
import aces.esprit.repository.RatingCommentRepository;
import aces.esprit.repository.UserRepository;
import aces.esprit.utile.FilterBW;


@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PublicationRepository publicationRepository;
	@Autowired
	RatingCommentRepository ratingCommentRepository;

	@Override
	public List<Comment> getCommentByIdPublication(int idPub) {
		// TODO Auto-generated method stub
	
		return (List<Comment>) commentRepository.findByPubOrderByDateCreation(idPub);

	}
	

	@Override
	public void deleteById(CommentPk commentpk) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentpk);
	}

	@Override
	public Optional<Comment> getCommentById(Comment commentpk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment addComment(Comment com) {
		// TODO Auto-generated method stub
		Publication publication = (Publication) publicationRepository.findById(com.getCommentPk().getIdPub()).orElse(null);
		User user = (User) userRepository.findById(com.getCommentPk().getIdUser()).orElse(null);
		FilterBW.loadConfigs();
		if (publication != null && user != null && (user.getBanned() < 3)) {

			if (FilterBW.filterText(com.getDescription()).size() < 3) {
				com.setDescription(FilterBW.filterWord(com.getDescription()));
				return commentRepository.save(com);}
			else {
				user.setBanned(user.getBanned() + 1);
				userRepository.save(user);
			}
		}
		return null;
	}

	@Override
	public void updateComment(Comment com, String description) {
		// Optional<Comment> comment = commentRepository.findById(commentpk);
		Comment c = commentRepository.findById(com.getCommentPk()).orElse(null);
		if (c != null) {
			com.setDescription(description);
			commentRepository.save(com);
		}
	}

	@Override
	public void addRatForComment(RatingComment rat, int idUser) {
		// TODO Auto-generated method stub
		Comment c = commentRepository.findById(rat.getComment().getCommentPk()).orElse(null);
		User user = (User) userRepository.findById(idUser).orElse(null);
		if (c != null && user != null) {
			rat.setUsers(user);
			rat.setComment(c);
			ratingCommentRepository.save(rat);

		}

	}

	@Override
	public List<RatingComment> getRatingComment() {
		// TODO Auto-generated method stub
		return (List<RatingComment>) ratingCommentRepository.findAll();
	}

	@Override
	public Long countComment(int idPub) {
		// TODO Auto-generated method stub
		return commentRepository.countComment(idPub);
	}

}
