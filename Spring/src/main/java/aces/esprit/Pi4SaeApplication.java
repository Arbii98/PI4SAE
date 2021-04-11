package aces.esprit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class Pi4SaeApplication {
	private static final Logger l = LogManager.getLogger(Pi4SaeApplication.class);

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(Pi4SaeApplication.class, args);
		l.debug("Bonjour NES LKOL (MA3ADECH TBADDALHA)");
		l.info("Bonjour NES LKOL (MA3ADECH TBADDALHA)");

		
		
	}

}
