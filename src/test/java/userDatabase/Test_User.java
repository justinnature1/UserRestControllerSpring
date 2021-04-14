package userDatabase;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test_User {

	private User testUser;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

//	@Before
//	public void setup(){
//		System.out.println("Hi");
//		testUser = new User("Justin", "Keller");
//		testUser.setiD(1L);
//	}
	
	@Test
	public void testGetAttributes() {
		testUser = new User("Justin", "Keller");
		testUser.setiD(1L);
		
		assertTrue(testUser.getid()==1L);
		assertEquals("Justin", testUser.getFirstName());
		assertEquals("Keller", testUser.getLastName());	
	}
	
	@Test
	public void testChangeAttributes() {
		testUser = new User();
		
		testUser.setiD(2L);
		testUser.setFirstName("John");
		testUser.setLastName("Deere");
		
		assertTrue(testUser.getid()==2L);
		assertEquals("John",testUser.getFirstName());
		assertEquals("Deere", testUser.getLastName());	
	}
	
}
