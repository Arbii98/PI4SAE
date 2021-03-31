package aces.esprit.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Comment;
import aces.esprit.entity.Publication;
import aces.esprit.entity.RatingPub;
import aces.esprit.entity.User;
import aces.esprit.repository.CommentRepository;
import aces.esprit.repository.PublicationRepository;
import aces.esprit.repository.RatingPubRepository;
import aces.esprit.repository.UserRepository;

@Service
public class PublicationServiceImpl implements PublicationService {
	@Autowired
	PublicationRepository publicationRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RatingPubRepository ratingPubRepository;
	@Autowired
	CommentRepository commentrepository;
	@Autowired
    private EmailService emailService;


	@Override
	public Publication addPublication(Publication pub, int idUser) {
		User userp = (User) userRepository.findById(idUser).orElse(null);
		if (userp != null) {
			pub.setUserp(userp);
			return publicationRepository.save(pub);
		}

		return null;
	}

	@Override
	public Publication updatePublication(Publication pub, int idPub) {
		if (publicationRepository.findById(idPub).orElse(null) != null) {
			pub.setIdPub(idPub);
			return publicationRepository.save(pub);
		}

		return null;
	}

	@Override
	public void deletePublication(int idPub) {
		publicationRepository.deleteById(idPub);

	}

	@Override
	public Publication getByIdpub(int idPub) {
		// TODO Auto-generated method stub
		return publicationRepository.findById(idPub).orElse(null);
	}

	@Override
	public List<Publication> getAllPublishByTopRating() {
		return (List<Publication>) publicationRepository.getAllPublishByTopRating();
	}

	@Override
	public Map<String, Object> getPub(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<Publication> pageQuestions =  publicationRepository.getPub(pageable);

		Map<String, Object> response = new HashMap<>();
		response.put("publications", pageQuestions.getContent());
		response.put("currentPage", pageQuestions.getNumber());
		response.put("totalItems", pageQuestions.getTotalElements());
		response.put("totalPages", pageQuestions.getTotalPages());

		return response;
	}
	@Override
	public List<Publication> getAllPubByUser(int idUser) {
		return publicationRepository.getPubByUser(idUser);
	}

	@Override
	public Publication getByTitle(String title) {
		return publicationRepository.getPubByTitle(title);
	}

	@Override
	public List<Publication> getAllPub() {
		return (List<Publication>) publicationRepository.findAll();
	}

	@Override
	public List<Publication> getallpubByTopComment() {
		return (List<Publication>) publicationRepository.getAllPublish();
	}

	@Override
	public RatingPub affectRatForPub(RatingPub ratp, int idUser, int idPub) {
		Publication p = publicationRepository.findById(idPub).orElse(null);
		User user = (User) userRepository.findById(idUser).orElse(null);
		if (p != null && user != null) {
			ratp.setUser(user);
			ratp.setPub(p);
			return ratingPubRepository.save(ratp);

		}
		return null;
	}

	@Override
	public List<RatingPub> getRatingPub() {
		return (List<RatingPub>) ratingPubRepository.findAll();
	}

	//@Scheduled(cron = "*/10 * * * * *")
	// @Scheduled(cron="*/5 * * * * ?")
	@Override
	public void archAutoPub() {
		/*for (Publication p : publicationRepository.findAll()) {
			Date now = new Date();
			if (new Date(p.getDateCreation().getYear(), p.getDateCreation().getMonth(), p.getDateCreation().getDate())
					.before(new Date(now.getYear(), now.getMonth() - 1, now.getDate())) && p.getRatPub().size() == 0) {
				publicationRepository.deleteById(p.getIdPub());
			}
		}*/
	}


	@Override
	public Map<String, Float> IAScanner(int idPub) {
		Map<String, Float> map = new HashMap();
		Publication p = this.getByIdpub(idPub);
		String sh = "";
		if (p != null)
			for (Comment c : p.getComments()) {
				sh += " " + c.getDescription();
			}
		try {
			map.put("positive", (float) this.positive(sh) / (this.positive(sh) - this.negative(sh)));

			map.put("negative", -(float) this.negative(sh) / (this.positive(sh) - this.negative(sh)));
			
			if( (float) this.positive(sh) / (this.positive(sh) - this.negative(sh))< (-(float) this.negative(sh) / (this.positive(sh) - this.negative(sh))))
			{
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo("islem.louati@esprit.tn");
			    message.setFrom("ConsommiTounsi@gmail.com");
			    message.setSubject("PUBLUCATION NOT SUCESS !");
			    message.setText("the publication is not sucess ");
			    JavaMailSender mailSender = new JavaMailSenderImpl();
			    ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");
			    ((JavaMailSenderImpl) mailSender).setPort(587);
			    ((JavaMailSenderImpl) mailSender).setUsername("mohamedelarbi.saidi@esprit.tn");
			    ((JavaMailSenderImpl) mailSender).setPassword("esprit1162");
			    
			    Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
			    props.put("mail.transport.protocol", "smtp");
			    props.put("mail.smtp.auth", "true");
			    props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.debug", "true");
			   
			    emailService.sendMailContribution("islem.louati@esprit.tn", "NOTSUCESS");
			

			}

			System.out.println(this.positive(sh) + sh);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public int positive(String input) throws IOException {
		try {
			int count = 0;
			String tweet;

			String line = "";

			Map<String, String> map = new HashMap<String, String>();

			Resource resource = new ClassPathResource("Data/AFINN-111.txt");
			BufferedReader in = new BufferedReader(new FileReader(resource.getFile()));

			line = "";
			while ((line = in.readLine()) != null) {
				String parts[] = line.split("\t");
				map.put(parts[0], parts[1]);
				count++;
			}
			in.close();

			Scanner inputStream = new Scanner(input);
			int sum = 0;
			while (inputStream.hasNextLine()) {
				float tweetscore = 0;
				tweet = inputStream.nextLine();
				String[] word = tweet.split(" ");

				for (int i = 0; i < word.length; i++) {

					if (map.get(word[i]) != null) {
						String wordscore = map.get(word[i].toLowerCase());
						tweetscore = (float) tweetscore + Integer.parseInt(wordscore);
					}
				}
				Map<String, Float> sentiment = new HashMap<String, Float>();
				sentiment.put(tweet, tweetscore);
				System.out.println(sentiment.toString());
				sum += tweetscore;

			}
			return sum;

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return 0;

	}

	public int negative(String input) throws IOException {
		try {
			int count = 0;
			String tweet;

			String line = "";

			Map<String, String> map = new HashMap<String, String>();
			Resource resource = new ClassPathResource("Data/AFINN-96.txt");
			BufferedReader in = new BufferedReader(new FileReader(resource.getFile()));

			line = "";
			while ((line = in.readLine()) != null) {
				String parts[] = line.split("\t");
				map.put(parts[0], parts[1]);
				count++;
			}
			in.close();

			Scanner inputStream = new Scanner(input);
			int sum = 0;
			while (inputStream.hasNextLine()) {
				float tweetscore = 0;
				tweet = inputStream.nextLine();
				String[] word = tweet.split(" ");

				for (int i = 0; i < word.length; i++) {

					if (map.get(word[i]) != null) {
						String wordscore = map.get(word[i].toLowerCase());
						tweetscore = (float) tweetscore + Integer.parseInt(wordscore);
					}
				}
				Map<String, Float> sentiment = new HashMap<String, Float>();
				sentiment.put(tweet, tweetscore);
				System.out.println(sentiment.toString());
				sum += tweetscore;

			}
			return sum;

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return 0;

	}

	@Override
	public int nbrLike(int idPub) {
		int max=0;
		
	Publication pub= publicationRepository.findById(idPub).orElse(null);		
	List<RatingPub> re = pub.getRatPub();
		for (RatingPub rr : re) {
			if (rr.getRat() == rr.getRat().LIKE)
			{
				max=max+1;
			}
		}
		return max;
	}

	@Override
	public int nbrDislike(int idPub) {
		int max1=0;
		
	Publication pub= publicationRepository.findById(idPub).orElse(null);		
	List<RatingPub> re = pub.getRatPub();
		for (RatingPub rr : re) {
			if (rr.getRat() == rr.getRat().DISLIKE)
			{
				max1=max1+1;
			}
		}
		return max1;
	}

	@Override
	public Publication maxnblike() {
		int k=0;
		List<Publication> pub=(List<Publication>) publicationRepository.findAll();		
		for(Publication pp : pub ) {
			//publicationRepository.findById(pp.getIdPub());
			if(this.nbrLike(pp.getIdPub())> k) {
				k=nbrLike(pp.getIdPub());
				
			}  
		
		return pp;

	}
		return null;
		}
	




}
