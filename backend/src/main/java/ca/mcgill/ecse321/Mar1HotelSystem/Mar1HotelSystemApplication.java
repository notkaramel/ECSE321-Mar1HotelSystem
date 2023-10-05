// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class Mar1HotelSystemApplication {
	// ----- CRUD -----
	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Mar1HotelSystemApplication.class, args);
	}

}
