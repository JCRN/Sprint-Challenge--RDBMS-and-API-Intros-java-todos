package local.jcrn.javatodos.service;

import local.jcrn.javatodos.model.Todo;
import local.jcrn.javatodos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todorepos;


    @Override
    public void delete(long id)
    {
        if (todorepos.findById(id).isPresent())
        {
            todorepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public List<Todo> findAll()
    {
        List<Todo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Todo> findByUserName(String username)
    {
        List<Todo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }

    @Override
    public Todo findTodoById(long id)
    {
        return todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Todo save(Todo todo)
    {
        return todorepos.save(todo);
    }

    @Override
    public Todo update(Todo updateTodo, long id)
    {
        Todo newTodo = todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        newTodo.setCompleted(true);
        return todorepos.save(newTodo);
    }
}
