package userDatabase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserController {

	private static User testUser;
	private static List<User> testUsers;
	private static UserController userController;
	private static UserRepository userRepository;

	@BeforeAll
	public static void setup() {
		testUser = new User ("Justin", "Keller");
		testUsers = new ArrayList<User>();
		testUsers.add(testUser);
		userRepository = mock(UserRepository.class);
		userController = new UserController(userRepository);
	}


	@Test
	public void testFindAllOrderBy() {
		when (userRepository.findAllByOrderByLastNameAsc()).thenReturn(testUsers);
		Assertions.assertEquals(testUsers, userController.allUsersByLastName());
	}

	@Test
	public void testNewUser() {
		when (userRepository.findByFirstNameAndLastNameIgnoreCase("Justin", "Keller"))
		.thenReturn(null);
		when (userRepository.save(testUser)).thenReturn(testUser);
		Assertions.assertEquals(testUser, userController.newUser(testUser));
	}

	@Test
	public void testNewUserDuplicate() {
		when (userRepository.findByFirstNameAndLastNameIgnoreCase("Justin", "Keller"))
		.thenReturn(testUser);
		when (userRepository.save(testUser)).thenReturn(null);
		Assertions.assertThrows(UserExistsException.class, () -> {userController.newUser(testUser);});
	}
	
	@Test
	public void testDeleteUser() {
		userController.deleteUser(1L);
		Mockito.verify(userRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteUserNoUserFound() {
		Assertions.assertThrows(UserNotFoundException.class, () -> {userController.deleteUser(10L);});
	}
	
	@Test
	public void testFindUser() {
		when (userRepository.existsById(1L)).thenReturn(true);
		when (userRepository.findById(1L)).thenReturn(testUser);
		Assertions.assertEquals(testUser, userController.findUser(1L));
	}
	
	@Test
	public void testFindUserNotFound() {
		when (userRepository.existsById(1L)).thenReturn(false);
		Assertions.assertThrows(UserNotFoundException.class, () -> {userController.findUser(1L);});
	}
	
}
