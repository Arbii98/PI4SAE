package aces.esprit;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import aces.esprit.controller.PublicationController;
import aces.esprit.entity.Comment;
import aces.esprit.entity.CommentPk;
import aces.esprit.entity.Publication;
import aces.esprit.entity.RatingComment;
import aces.esprit.entity.RatingPub;
import aces.esprit.entity.User;
import aces.esprit.repository.PublicationRepository;
import aces.esprit.repository.RatingPubRepository;
import aces.esprit.repository.UserRepository;
import aces.esprit.service.CommentService;
import aces.esprit.service.PublicationService;
import aces.esprit.service.PublicationServiceImpl;
import aces.esprit.utile.FilterBW;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Pi4SaeApplicationTests {

	@Autowired
	PublicationService pub;
	@Autowired
	CommentService cm;

	@Autowired
	PublicationRepository pubr;
	@Autowired
	RatingPubRepository rr;
                                    /////ADDPublication////////////////////
//	 @Test
//	 @Rollback(false)
//	 public void testAddPublication() {
//	 Publication p = new Publication("pidevspring","islem",new Date());
//	 pub.addPublication( p,2);
//	 assertThat(p.getIdPub()).isGreaterThan(0);
//	
//	 }
	                           //////getallPub//////////////
//	 @Test
//	 public void testGetAllPub() {
//	 List<Publication> pubs = (List<Publication>) pubr.findAll();
//	 System.out.println(pubs );
//	 assertThat(pubs).size().isGreaterThan(0);
//	 }
	/////////////getallpubbyTopcomment/////
	

	
	////getAllPubByRat////
//	 @Test
//	 public void testGetAllPub() {
//	 List<Publication> pubs = (List<Publication>) pubr.getAllPublishByTopRating();
//	 System.out.println(pubs );
//	 assertThat(pubs).size().isGreaterThan(0);
//	 }
	
//	 @Test
//	 public void testGetAllPub() {
//	 List<Publication> pubs = (List<Publication>) pubr.getPubByUser(1);
//	 System.out.println(pubs );
//	 assertThat(pubs).size().isGreaterThan(0);
//	 }

	
	                       /////////GetPubById/////////
//	@Test
//	public void testGetByIdpub() {
//		Publication p = pub.getByIdpub(6);
//
//		pub.getByIdpub(p.getIdPub());
//
//		Publication getPublication = pub.getByIdpub(6);
//
//		assertThat(getPublication).isNotNull();
//		System.out.println(getPublication.getDescription());
//
//	}
	
	                  /////////GetPubByRating/////////////
//	@Test
//	public void testGetAllPublishByTopRating() {
//		List<Publication> pubs = (List<Publication>) pubr.getAllPublishByTopRating();
//		assertThat(pubs).isNotNull();
//		for (Publication publication : pubs) {
//			System.out.println(publication.getDescription());
//		}
//		
//
//	}
    /////////GetPubByComment/////////////
//	@Test
//	public void testGetallpubByTopComment() {
//		List<Publication> pubs = (List<Publication>) pubr.getAllPublishByTopComment();
//		assertThat(pubs).isNotNull();
//		for (Publication publication : pubs) {
//			System.out.println(publication.getDescription());
//		}
//
//	}
	
	
	
	
	               ////////////UpdatePub///////
	// @Test
	// @Rollback(false)
	// public void testUpdatePublication() {
	// //Publication p = new Publication();
	// Publication pubb = pub.getByIdpub(6);
	// pubb.setDescription("even");
	// pub.addPublication(pubb, 6);
	// Publication updatedPublication = pub.getByIdpub(6);
	//
	// assertThat(updatedPublication.getDescription()).isEqualTo("even");
	// }
                     ////////deletePub/////////
	// @Test
	// @Rollback(false)
	// public void testDeletePub() {
	// Publication p = pub.getByIdpub(5);
	//
	// pub.deletePublication(p.getIdPub());
	//
	// Publication deletedPublication = pub.getByIdpub(5);
	//
	// assertThat(deletedPublication).isNull();
	//
	// }
	
	                  ////////// Comment//////
//	 @Test
//	 @Rollback(false)
//	 public void testAddComment() {
//	
//	 CommentPk ck = new CommentPk(6,2,new Date() );
//	 Comment c = new Comment ();
//	 c.setDescription(" test ");
//	 c.setCommentPk(ck);
//	 assertThat((FilterBW.filterText(c.getDescription()).size())<(3));
//	 c.setDescription(FilterBW.filterWord(c.getDescription()));
//	 cm.addComment(c);
//	 assertThat(c.getCommentPk()).isNotNull();
//	
//	
//	 }

	// @Test
	// @Rollback(false)
	// public void testupdateComment() {
	//
	// CommentPk ck = new CommentPk(6,2,new Date() );
	// Comment c = new Comment ();
	// c.setDescription("busty fuck test");
	// c.setCommentPk(ck);
	// cm.addComment(c);
	// assertThat(c.getCommentPk()).isNotNull();
	//
	// }

}

