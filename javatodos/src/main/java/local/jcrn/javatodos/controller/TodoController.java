package local.jcrn.javatodos.controller;

import local.jcrn.javatodos.model.Todo;
import local.jcrn.javatodos.model.User;
import local.jcrn.javatodos.service.TodoService;
import local.jcrn.javatodos.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{
    @Autowired
    private TodoService todoService;

    @PutMapping(value = "/todoid/{todoid}")
    public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long todoid) {
        todoService.update(updateTodo, todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
