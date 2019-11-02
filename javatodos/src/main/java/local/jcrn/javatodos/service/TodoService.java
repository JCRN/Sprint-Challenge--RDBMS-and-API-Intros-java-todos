package local.jcrn.javatodos.service;

import local.jcrn.javatodos.model.Todo;

import java.util.List;

public interface TodoService
{
    void delete(long id);

    List<Todo> findAll();

    List<Todo> findByUserName(String username);

    Todo findTodoById(long id);

    Todo save(Todo todo);

    Todo update(Todo updateTodo, long id);

}
