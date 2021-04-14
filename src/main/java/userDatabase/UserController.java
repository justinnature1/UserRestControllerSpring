package userDatabase;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final UserRepository repository;

	//https://spring.io/guides/tutorials/rest/
	//https://dzone.com/articles/secure-a-spring-boot-app-with-spring-security-and
	//https://www.baeldung.com/spring-security-basic-authentication

	public UserController (UserRepository repository) {
		this.repository = repository;
	}

	/**
	 * Gets a list of all users sorted by last name
	 * @return List of Users
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/users")
	List<User> allUsersByLastName () {
		return repository.findAllByOrderByLastNameAsc();
	}

	/**
	 * Finds a single user if the ID exists in the database
	 * @param id ID number of the user.
	 * @return User found in the database
	 * @throws UserNotFoundException
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/users/{id}")
	User findUser(@PathVariable long id) throws UserNotFoundException{
		if (repository.existsById(id))
			return repository.findById(id);
		else
			throw new UserNotFoundException(id);
	}

	/**
	 * Creates a new user in the database only if that user doesn't exist already.
	 * @param newUser User object to add.
	 * @return User: New user record created in the database
	 * @throws UserExistsException
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/users")
	User newUser(@RequestBody User newUser) throws UserExistsException{
		if(repository.findByFirstNameAndLastNameIgnoreCase(
				newUser.getFirstName(),
				newUser.getLastName())==null)
			return repository.save(newUser);
		else
			throw new UserExistsException(newUser.getFirstName(), newUser.getLastName());
	}

	/**
	 * Deletes a user from the database by ID number only if the user exists.
	 * @param id The ID of the User
	 * @throws UserNotFoundException
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) throws UserNotFoundException {
		if (repository.existsById(id))
			repository.deleteById(id);
		else
			throw new UserNotFoundException(id);
	}

}
