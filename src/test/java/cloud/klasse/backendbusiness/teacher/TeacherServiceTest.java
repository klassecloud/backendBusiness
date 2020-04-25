package cloud.klasse.backendbusiness.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    private String userNameMock;
    private String nickNameMock;
    private String passwordMock;
    private String emailMock;
    private Teacher teacherMock;

    @BeforeEach
    void setUp() {
        userNameMock = "user";
        nickNameMock = "nick";
        passwordMock = "pass";
        emailMock = "email@mock";
        teacherMock = new Teacher (1, userNameMock, nickNameMock, emailMock, passwordMock, true);
    }

    @Test
    @DisplayName("Testing create teacher.")
    void testCreateTeacher() {

        CreateTeacherModel createTeacherModel = new CreateTeacherModel();
        createTeacherModel.setUserName(userNameMock);
        createTeacherModel.setNickName(nickNameMock);
        createTeacherModel.setEmail(emailMock);
        createTeacherModel.setPassword(passwordMock);

        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacherMock);

        final Teacher teacher = teacherService.createTeacher(createTeacherModel);

        assertEquals(1, teacher.getId());
        assertEquals(userNameMock, teacher.getUserName());
        assertEquals(nickNameMock, teacher.getNickname());
        assertEquals(emailMock, teacher.getEmail());
        assertEquals(passwordMock, teacher.getPassword());
        assertTrue(teacher.isValidated());

    }

    @Test
    @DisplayName("Testing get teacher.")
    void testGetUser() {

        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacherMock));

        final Teacher teacher = teacherService.getTeacher(1);

        assertEquals(userNameMock, teacher.getUserName());
        assertEquals(nickNameMock, teacher.getNickname());
        assertEquals(emailMock, teacher.getEmail());
        assertEquals(passwordMock, teacher.getPassword());
        assertTrue(teacher.isValidated());
    }

    @Test
    @DisplayName("Testing get teacher with wrong id.")
    void testGetTeacherWithWrongId() {

        when(teacherRepository.findById(anyLong())).thenThrow(new TeacherNotFoundException("1"));
        assertThrows(TeacherNotFoundException.class, () -> teacherService.getTeacher(1));

    }

    @Test
    @DisplayName("Testing get teacher by email.")
    void testGetUserByEmail() {

        when(teacherRepository.findByEmail(anyString())).thenReturn(Optional.of(teacherMock));

        final Teacher teacher = teacherService.getTeacherByEmail("email@mock");

        assertEquals(1, teacher.getId());
        assertEquals(userNameMock, teacher.getUserName());
        assertEquals(nickNameMock, teacher.getNickname());
        assertEquals(passwordMock, teacher.getPassword());
        assertTrue(teacher.isValidated());
    }

    @Test
    @DisplayName("Testing get teacher with wrong email." )
    void testGetTeacherWithWrongEmail() {

        when(teacherRepository.findByEmail(anyString())).thenThrow(new TeacherNotFoundException("email@mock"));
        assertThrows(TeacherNotFoundException.class, () -> teacherService.getTeacherByEmail("email@mock"));

    }

    @Test
    @DisplayName("Testing get teacher by username.")
    void testGetUserByUserName() {

        when(teacherRepository.findByUserName(anyString())).thenReturn(Optional.of(teacherMock));

        final Teacher teacher = teacherService.getTeacherByUserName("user");

        assertEquals(1, teacher.getId());
        assertEquals(emailMock, teacher.getEmail());
        assertEquals(nickNameMock, teacher.getNickname());
        assertEquals(passwordMock, teacher.getPassword());
        assertTrue(teacher.isValidated());
    }


    @Test
    @DisplayName("Testing get teacher with wrong username.")
    void testGetTeacherWithWrongWrongUserName() {

        when(teacherRepository.findByUserName(anyString())).thenThrow(new TeacherNotFoundException("user"));
        assertThrows(TeacherNotFoundException.class, () -> teacherService.getTeacherByUserName("user"));

    }


    @Test
    @DisplayName("Testing update teacher.")
    void testUpdateTeacher() {

        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacherMock));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacherMock);

        UpdateTeacherModel updateTeacherModel = new UpdateTeacherModel();
        updateTeacherModel.setUserName(userNameMock);
        updateTeacherModel.setNickName(nickNameMock);
        updateTeacherModel.setEmail(emailMock);

        final Teacher teacher = teacherService.updateTeacher(updateTeacherModel, 1L);

        assertEquals(userNameMock, teacher.getUserName());
        assertEquals(nickNameMock, teacher.getNickname());
        assertEquals(emailMock, teacher.getEmail());

    }

    @Test
    @DisplayName("Testing update teacher with wrong id.")
    void testUpdateTeacherWithWrongId() {

        UpdateTeacherModel updateTeacherModel = new UpdateTeacherModel();
        updateTeacherModel.setUserName(userNameMock);
        updateTeacherModel.setNickName(nickNameMock);
        updateTeacherModel.setEmail(emailMock);

        when(teacherRepository.findById(anyLong())).thenThrow(new TeacherNotFoundException("1"));
        assertThrows(TeacherNotFoundException.class, () -> teacherService.updateTeacher(updateTeacherModel, 1L));

    }

}
