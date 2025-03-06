package com.example.Gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.Gym"}) // Ensure all packages are scanned
public class GymApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymApplication.class, args);
	}

}



// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// public class PasswordCheck {
//     public static void main(String[] args) {
//         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//         String rawPassword = "1234";
//         String hashedPassword = "$2a$10$2tLB4rj68puy.IbKPQbA0uHMZYgxbyobEB4E1mm.PFi.O/aPGYq7y"; // Your DB password

//         boolean isMatch = encoder.matches(rawPassword, hashedPassword);
//         System.out.println("Password matches: " + isMatch);
//     }
// }

