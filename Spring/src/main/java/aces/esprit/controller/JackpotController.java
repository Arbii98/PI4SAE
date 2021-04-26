package aces.esprit.controller;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

import org.apache.http.client.methods.*;



import aces.esprit.entity.Jackpot;
import aces.esprit.entity.JackpotEvolutionStat;
import aces.esprit.entity.TopDonatorsStat;
import aces.esprit.service.EmailService;
import aces.esprit.service.JackpotService;

@RestController
public class JackpotController {
	
	@Autowired
	JackpotService js;
	
	@Autowired
    private EmailService emailService;
	
	@PostMapping("/addContribution")
	@ResponseBody
	public Jackpot addContribution(@RequestBody Jackpot jackpot) {
		/*SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("arbisaidi8@gmail.com");
	    message.setFrom("ConsommiTounsi@gmail.com");
	    message.setSubject("Contribution Reçue !");
	    message.setText("Vous avez reçu une contribution");
	    JavaMailSender mailSender = new JavaMailSenderImpl();*/
	    /*mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("mohamedelarbi.saidi@esprit.tn");
	    mailSender.setPassword("esprit1162");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");*/
	    
	   // mailSender.send(message);
	    emailService.sendMailContribution("Arbisaidi8@gmail.com", "Contribution");
		
	/*	
	NexmoClient client = NexmoClient.builder().apiKey("a7c8d346").apiSecret("06RtyiF7aVUXE90L").build();
		  TextMessage message = new TextMessage("Contribution",
		                   "+21653364314",
		                    "Vous avez reçu une contibution"
		            );
		  
		  try {
			  SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
			  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
			    System.out.println("Message sent successfully.");
			} else {
			    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
			}
		  }
		  catch(Exception e) {}*/

		
		
		
		
		return js.addContribution(jackpot);
		
	}
	
	
	@PostMapping("/addWithdraw")
	@ResponseBody
	public Jackpot addWithdraw(@RequestBody Jackpot jackpot) {
	
		return js.addWithdraw(jackpot);
		
	}
	
	@GetMapping("/getCurrentMontantTotal")
	@ResponseBody
	public float getCurrentMontantTotal()
	{
		return js.getCurrentMontantTotal();
	}
	
	@GetMapping("/getMontantTotal")
	@ResponseBody
	public float getMontantTotal()
	{
		return js.getMontantTotal();
	}
	
	@GetMapping("/getJackpotEvolution")
	@ResponseBody
	public List<JackpotEvolutionStat> getJackpotEvolution()
	{
		return js.GetJackpotEvolution();
	}
	
	@GetMapping("/getTopDonators")
	@ResponseBody
	public List<TopDonatorsStat> getTopDonators()
	{
		return js.GetTopDonators();
	}
	
	

}
