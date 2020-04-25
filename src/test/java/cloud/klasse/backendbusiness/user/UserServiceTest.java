package cloud.klasse.backendbusiness.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private String userNameMock;
    private String nickNameMock;
    private String passwordMock;
    private User userMock;

    @BeforeEach
    void setUp() {
        userNameMock = "max.mustermann";
        nickNameMock = "maxi";
        passwordMock = "foobar";
        userMock = new User (1, userNameMock, nickNameMock, passwordMock, true);
    }

    @Test
    @DisplayName("Testing create user ...")
    void testCreateUser () {
        // given
        final CreateUserModel createUserModelMock = new CreateUserModel ();

        createUserModelMock.setUserName(userNameMock);
        createUserModelMock.setNickName(nickNameMock);
        createUserModelMock.setPassword(passwordMock);

        when(userRepository.save(any(User.class))).thenReturn(userMock);

        // when
        final User createdUser = userService.createUser(createUserModelMock);

        // then
        assertEquals (1, createdUser.getId());
        assertEquals (userNameMock, createdUser.getUserName());
        assertEquals (nickNameMock, createdUser.getNickName());
        assertEquals (passwordMock, createdUser.getPassword());
        assertTrue   (createdUser.isActivated());
    }

    @Test
    @DisplayName("Testing get user ...")
    void testGetUser () {
        // given
        final Optional<User> optionalUserMock = Optional.of(userMock);
        when(userRepository.findById(anyLong())).thenReturn(optionalUserMock);

        // when
        final Optional<User> optionalUser = userService.getUser(1);

        // then
        assertTrue   (optionalUser.isPresent());
        assertEquals (userNameMock, optionalUser.get().getUserName());
        assertEquals (nickNameMock, optionalUser.get().getNickName());
        assertEquals ("****",       optionalUser.get().getPassword());
    }

    @Test
    @DisplayName("Testing get user with wrong id ...")
    void testGetUserWithWrongId () {
        // given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        final Optional<User> optionalUser = userService.getUser(2);

        // then
        assertTrue   (optionalUser.isEmpty());
    }

}