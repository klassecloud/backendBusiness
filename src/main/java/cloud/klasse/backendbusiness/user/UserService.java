package cloud.klasse.backendbusiness.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User service to manage an user.
 *
 * <p>This class injects the user repository.</p>
 *
 * @since 0.0.1
 *
 * @see Service
 * @see RequiredArgsConstructor
 * @see Slf4j
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    /** injected user repository */
    private final UserRepository userRepository;

    /**
     * Create an user with the given create user model {@link CreateUserModel}.
     *
     * @param createUserModel create user model
     * @return the created user
     *
     * @since 0.0.1
     */
    User createUser (final CreateUserModel createUserModel) {
        final User user = userRepository.save(new User(0, createUserModel.getUserName(), createUserModel.getNickName(), createUserModel.getPassword(), true));
        log.info("Create a user with id {}", user.getId());
        return user;
    }

    /**
     * Get the user with the given id.
     *
     * @param id user id
     * @return an optional of the user
     */
    Optional<User> getUser (final long id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        log.info("Get user with id: {}", id);
        optionalUser.ifPresent((final User user) -> user.setPassword("****"));
        return optionalUser;
    }
}
