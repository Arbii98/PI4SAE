package aces.esprit.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO Auto-generated method stub
		if (publicationRepository.findById(idPub).orElse(null) != null) {
			pub.setIdPub(idPub);
			return publicationRepository.save(pub);
		}

		return null;
	}

	@Override
	public void deletePublication(int idPub) {
		// TODO Auto-generated method stub
		publicationRepository.deleteById(idPub);

	}

	@Override
	public Publication getByIdpub(int idPub) {
		// TODO Auto-generated method stub
		return publicationRepository.findById(idPub).orElse(null);
	}

	@Override
	public List<Publication> getAllPublishByTopRating() {
		// TODO Auto-generated method stub
		return (List<Publication>) publicationRepository.getAllPublishByTopRating();
	}

	@Override
	public List<Publication> getAllPub() {
		// TODO Auto-generated method stub
		return (List<Publication>) publicationRepository.findAll();
	}

	@Override
	public List<Publication> getallpubByTopComment() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return (List<RatingPub>) ratingPubRepository.findAll();
	}

	// @Scheduled(cron="*/10 * * * * *")
	// @Scheduled(cron="*/5 * * * * ?")
	@Override
	public void archAutoPub() {
		// TODO Auto-generated method stub
		for (RatingPub ratingpub : ratingPubRepository.findAll()) {
			Date now = new Date();
			if (ratingpub.getPub().getDateCreation().before(now) && ratingpub.getRat() != 0) {
				ratingPubRepository.deleteById(ratingpub.getIdRp());
				// ratingPubRepository.
				System.out.println("sss" + ratingpub.getPub());
			}
		}
	}

	@Override
	public void pubRedondant(Publication pub, int idPub) {

	}

	@Override
	public Map<String, Integer> IAScanner(int idPub) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap();
		Publication p = this.getByIdpub(idPub);
		String sh = "";
		if (p != null)
			for (Comment c : p.getComments()) {
				sh += " " + c.getDescription();
			}
		try {
			map.put("positive", (this.positive(sh)/(this.positive(sh)-this.negative(sh))));

			map.put("negative", this.negative(sh));

			System.out.println(this.positive(sh) + sh);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			BufferedReader in = new BufferedReader(new FileReader(
					"C:\\Users\\islem\\AppData\\Local\\GitHubDesktop\\app-2.5.2\\PI4SAE\\Data\\AFINN-111.txt"));

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
			return  sum;

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
			BufferedReader inn = new BufferedReader(new FileReader(
					"C:\\Users\\islem\\AppData\\Local\\GitHubDesktop\\app-2.5.2\\PI4SAE\\Data\\AFINN-96.txt"));

			line = "";
			while ((line = inn.readLine()) != null) {
				String parts[] = line.split("\t");
				map.put(parts[0], parts[1]);
				count++;
			}
			inn.close();

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
	public List<Publication> getAllPubByUser(int idUser) {
		// TODO Auto-generated method stub
		return publicationRepository.getPubByUser(idUser);
	}

}
