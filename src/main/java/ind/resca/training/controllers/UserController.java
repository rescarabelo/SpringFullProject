package ind.resca.training.controllers;

import ind.resca.training.models.User;
import ind.resca.training.repos.UserRepository;
import ind.resca.training.utils.dtos.RegisterUserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    final UserRepository userRepository;

    @GetMapping("/list")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Valid RegisterUserDto userDto,
            UriComponentsBuilder uriBuilder
    ) {
        User newUser = userRepository.save(userDto.convert());

        URI uri = uriBuilder.path("/user").queryParam("id", newUser.getId()).buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(newUser);
    }
}
