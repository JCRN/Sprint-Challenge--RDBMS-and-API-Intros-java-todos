package local.jcrn.javatodos.controller;

import local.jcrn.javatodos.model.Todo;
import local.jcrn.javatodos.model.User;
import local.jcrn.javatodos.service.TodoService;
import local.jcrn.javatodos.service.UserService;
import local.jcrn.javatodos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/mine", produces = {"application/json"})
    public ResponseEntity<?> getUser(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewTodo(@PathVariable Long userid, @RequestBody Todo newTodo)
    {
        Todo tempTodo = new Todo(newTodo.getDescription(), newTodo.getDatestarted(), userService.findUserById(userid));
        todoService.save(tempTodo);
        return new ResponseEntity<>(tempTodo, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
    {
        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}