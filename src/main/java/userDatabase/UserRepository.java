package userDatabase;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  List<User> findByLastName(String lastName);
  List<User> findAllByOrderByLastNameAsc();
  User findByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);
  User findById(long id);
}
