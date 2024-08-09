package com.nft.BanTranhNFT;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BanTranhNftApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanTranhNftApplication.class, args);
	}

	

}
