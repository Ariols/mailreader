package fr.ferhat.mailreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MailreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailreaderApplication.class, args);
	}

}
