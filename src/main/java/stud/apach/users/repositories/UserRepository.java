package stud.apach.users.repositories;

import org.springframework.data.repository.CrudRepository;
import stud.apach.users.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

