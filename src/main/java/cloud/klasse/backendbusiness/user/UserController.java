package cloud.klasse.backendbusiness.user;

import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser (@RequestBody final CreateUserModel createUserModel) {
        final User user = userService.createUser(createUserModel);
        log.info("Created user with userName: {}", user.getUserName());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser (@PathVariable final long id) {
        final Optional<User> optionalUser = userService.getUser(id);
        return optionalUser.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody final UpdateUserModel updateUserModel, @PathVariable final long id) {
        final Optional<User> optionalUpdateUser = userService.updateUser(id, updateUserModel);
        return optionalUpdateUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
