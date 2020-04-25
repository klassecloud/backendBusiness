package cloud.klasse.backendbusiness.user;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller to manage users.
 *
 * <p>This class injects the user service.</p>
 *
 * @since 0.0.1
 *
 * @see RestController
 * @see RequiredArgsConstructor
 * @see Slf4j
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    /** injected user service */
    private final UserService userService;

    /**
     * Post mapping {@link PostMapping} to create an user with the given request body {@link RequestBody} as
     * create user model {@link CreateUserModel} and returns an user response entity {@link ResponseEntity}.
     *
     * @param createUserModel create user model
     * @return the response entity {@link ResponseEntity} of user
     *
     * @since 0.0.1
     */
    @PostMapping("/user")
    public ResponseEntity<User> createUser (@RequestBody final CreateUserModel createUserModel) {
        final User user = userService.createUser(createUserModel);
        log.info("Created user with userName: {}", user.getUserName());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Get mapping {@link GetMapping} to get an user response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id user id
     * @return the response entity {@link ResponseEntity} of user
     *
     * @since 0.0.1
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser (@PathVariable final long id) {
        final Optional<User> optionalUser = userService.getUser(id);
        return optionalUser.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
