
package cloud.klasse.backendbusiness.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User service to manage an user. Uses a spring-security {@link PasswordEncoder} to store the passwords.
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

    private final PasswordEncoder passwordEncoder;
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
        final User user = userRepository.save(
                new User(0, createUserModel.getUserName(), createUserModel.getNickName(), passwordEncoder.encode(createUserModel.getPassword()), true)
        );
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

    public Optional<User> updateUser(final long id, final UpdateUserModel updateUserModel) {
        final Optional<User> optionalUser = userRepository.findById(id);
        log.info("Get user with id: {}", id);

        optionalUser.ifPresent((final User user) -> {
            user.setUserName(updateUserModel.getUserName());
            user.setNickName(updateUserModel.getNickName());
            userRepository.save(user);
            log.info("Update user with id: {}", id);
        });
        return optionalUser;
    }
}
