package aces.esprit.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import aces.esprit.service.CommentService;
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
		return (List<Comment>) commentRepository.findAll();
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
	public void addComment(Comment com, int idPub, int idUser) {
		// TODO Auto-generated method stub
		Publication publication = (Publication) publicationRepository.findById(idPub).orElse(null);
		User user = (User) userRepository.findById(idUser).orElse(null);
		//Comment c= new Comment();
		//c.setCommentPk(new CommentPk (idPub,idUser,new Date()));
		if (publication != null && user != null) {
		
			commentRepository.save(com);
		}
	}
	@Override
	public void updateComment(Comment com,String description ){
		//Optional<Comment> comment =  commentRepository.findById(commentpk);
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
		System.out.println(c+"ely" );
		System.out.println(user);

	}
	@Override
	public List<RatingComment> getRatingComment() {
		// TODO Auto-generated method stub
		return (List<RatingComment>) ratingCommentRepository.findAll();
	}



}
