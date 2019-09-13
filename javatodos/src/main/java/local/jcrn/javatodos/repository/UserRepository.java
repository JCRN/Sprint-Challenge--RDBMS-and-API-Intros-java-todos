package local.jcrn.javatodos.repository;

import local.jcrn.javatodos.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
