package aces.esprit.service;

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

		return commentRepository.findByPubOrderByDateCreation(idPub);

	}

	@Override
	public void deleteById(CommentPk commentpk) {

		commentRepository.deleteById(commentpk);
	}

	@Override
	public Comment addComment(Comment com) {

		Publication publication = publicationRepository.findById(com.getCommentPk().getIdPub()).orElse(null);
		User user = userRepository.findById(com.getCommentPk().getIdUser()).orElse(null);
		FilterBW.loadConfigs();
		if (publication != null && user != null && (user.getBanned() < 3)) {

			if (FilterBW.filterText(com.getDescription()).size() < 3) {
				com.setDescription(FilterBW.filterWord(com.getDescription()));
				return commentRepository.save(com);
			} else {
				if(user.isBlock()==true)
				{
				user.setBanned(user.getBanned() + 1);
				userRepository.save(user);
				
				if (user.getBanned()==3) {
					user.setBlock(true);
				}
					
				}
			}
		}
		return null;
	}

	@Override
	public void updateComment(Comment com, String description) {

		Comment c = commentRepository.findById(com.getCommentPk()).orElse(null);
		if (c != null) {
			com.setDescription(description);
			commentRepository.save(com);
		}
	}

	@Override
	public void addRatForComment(RatingComment rat, int idUser) {

		Comment c = commentRepository.findById(rat.getComment().getCommentPk()).orElse(null);
		User user = userRepository.findById(idUser).orElse(null);
		if (c != null && user != null) {
			List<RatingComment> rc = ratingCommentRepository.findByCommentAndUsers(c, user);
			if(rc.isEmpty()) {
				rat.setUsers(user);
				rat.setComment(c);
				ratingCommentRepository.save(rat);
			}
			else
			{
				ratingCommentRepository.delete(rc.get(0));
			}
		
		}
		


	}

	@Override
	public List<RatingComment> getRatingComment() {

		return (List<RatingComment>) ratingCommentRepository.findAll();
	}

	@Override
	public Long countComment(int idPub) {

		return commentRepository.countComment(idPub);
	}

}
