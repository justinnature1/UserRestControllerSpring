package userDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UserDatabase1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserDatabase1Application.class);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
//Used these records to test using curl. Should be taken out for live application.
			repository.save(new User("Jane", "Doe"));
			repository.save(new User("Justin", "Keller"));
			repository.save(new User("John", "Deere"));
		};
	}

}
