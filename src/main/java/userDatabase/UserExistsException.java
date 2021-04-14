package userDatabase;

class UserExistsException extends RuntimeException {
	UserExistsException(String firstName, String lastName) {
		super("User (" + firstName + " " + lastName + ") already exists.");
	}
}
