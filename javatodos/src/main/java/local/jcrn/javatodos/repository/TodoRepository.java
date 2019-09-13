package local.jcrn.javatodos.repository;

import local.jcrn.javatodos.model.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
}
