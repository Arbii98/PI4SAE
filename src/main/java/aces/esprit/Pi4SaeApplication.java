package aces.esprit;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Pi4SaeApplication {
	private static final Logger l = Logger.getLogger(Pi4SaeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Pi4SaeApplication.class, args);
		l.info("Bonjour NES LKOL (MA3ADECH TBADDALHA)");
	}

}
