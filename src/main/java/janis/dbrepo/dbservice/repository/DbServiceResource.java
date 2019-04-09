package janis.dbrepo.dbservice.repository;

import janis.dbrepo.dbservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public DbServiceResource(UserService userService, UserRepository userRepository){
        this.userRepository=userRepository;
        this.userService = userService;
    }

    @GetMapping("/{email}")
    private List<String> getUsers(@PathVariable("email") final String email){
        return getUserByUserName(email);
    }

    @GetMapping("/all")
    private List<User> getAll(){
        return getAllUsers();
    }

    private List<String> getUserByUserName(@PathVariable("username") final String email){
        return userRepository.findByEmail(email).stream().map(User::getLastName).collect(Collectors.toList());
    }

    private List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
