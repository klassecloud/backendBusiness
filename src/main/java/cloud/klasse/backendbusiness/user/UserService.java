
package cloud.klasse.backendbusiness.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User createUser (final CreateUserModel createUserModel) {
        final User user = userRepository.save(new User(0, createUserModel.getUserName(), createUserModel.getNickName(), createUserModel.getPassword(), true));
        log.info("Create a user with id {}", user.getId());
        return user;
    }

    public Optional<User> getUser (final long id) {
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
