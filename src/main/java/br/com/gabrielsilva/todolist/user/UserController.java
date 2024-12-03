package br.com.gabrielsilva.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository iUserRepository;
    @PostMapping
    public UserModel create(@RequestBody UserModel user){
        var userCreated = iUserRepository.save(user);
        return userCreated;
    }
}
