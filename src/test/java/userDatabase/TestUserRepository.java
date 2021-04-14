package userDatabase;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

@DataJpaTest
public class TestUserRepository {

	@Autowired
	private UserRepository repository;
	
	@BeforeEach
	public void setup() {
		repository.deleteAll();
	}
	
	@Test void findByIdNoRecords() {
		Assertions.assertEquals(null, repository.findById(1L));	
	}
	
	@Test
	public void findById() {
		User jane = repository.save(new User("Jane", "Doe"));
		Assertions.assertEquals(Optional.of(jane), repository.findById(jane.getid()));
	}
	
	@Test
	public void findAllOrderByLastNameAscNoRecords() {
		Assertions.assertEquals(null, repository.findById(1L));			
	}
	
	@Test
	public void findAllOrderByLastNameAsc() {
		User jane = repository.save(new User("Jane", "Doe"));
		User justin = repository.save(new User("Justin", "Keller"));
		User john = repository.save(new User("John", "Deere"));
		
		List<User> users = new ArrayList<User>();
		users.add(john);
		users.add(jane);
		users.add(justin);
		
		Assertions.assertEquals(users, repository.findAllByOrderByLastNameAsc());
	}
	
	@Test
	public void deleteUser() {
		User jane = repository.save(new User("Jane", "Doe"));
		Assertions.assertEquals(jane,
				repository.findByFirstNameAndLastNameIgnoreCase("Jane", "Doe"));
		repository.deleteById(jane.getid());
		Assertions.assertEquals(null, 
				repository.findByFirstNameAndLastNameIgnoreCase("Jane", "Doe"));
	}
	
	@Test
	public void deleteUserNoRecord() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {repository.deleteById(1L);});
	}
	
	@Test
	public void saveUser() {
		User jane = repository.save(new User("Jane", "Doe"));
		Assertions.assertEquals(jane, repository.save(jane));
	}
	
}

