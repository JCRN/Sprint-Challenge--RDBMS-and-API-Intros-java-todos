package local.jcrn.javatodos.service;

import local.jcrn.javatodos.model.User;

import java.util.List;

public interface UserService {

    void delete(long id);

    List<User> findAll();

    User findUserById(long id);

    org.springframework.security.core.userdetails.User findUserByName(String name);

    User save(User user);

    User update(User user, long id);
}