package cloud.klasse.backendbusiness.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private String userNameMock;
    private String nickNameMock;
    private String passwordMock;
    private Student studentMock;

    @BeforeEach
    void setUp() {
        userNameMock = "max.mustermann";
        nickNameMock = "maxi";
        passwordMock = "foobar";
        studentMock = new Student(1, userNameMock, nickNameMock, passwordMock, true);
    }

    @Test
    @DisplayName("Testing create student ...")
    void testCreateStudent () {
        // given
        final CreateStudentModel createStudentModelMock = new CreateStudentModel();

        createStudentModelMock.setUserName(userNameMock);
        createStudentModelMock.setNickName(nickNameMock);
        createStudentModelMock.setPassword(passwordMock);

        when(studentRepository.save(any(Student.class))).thenReturn(studentMock);

        // when
        final Student createdStudent = studentService.createStudent(createStudentModelMock);

        // then
        assertEquals (1, createdStudent.getId());
        assertEquals (userNameMock, createdStudent.getUserName());
        assertEquals (nickNameMock, createdStudent.getNickName());
        assertEquals (passwordMock, createdStudent.getPassword());
        assertTrue   (createdStudent.isActivated());
    }

    @Test
    @DisplayName("Testing get student ...")
    void testGetStudent () {
        // given
        final Optional<Student> optionalUserMock = Optional.of(studentMock);
        when(studentRepository.findById(anyLong())).thenReturn(optionalUserMock);

        // when
        final Optional<Student> optionalUser = studentService.getStudent(1);

        // then
        assertTrue   (optionalUser.isPresent());
        assertEquals (userNameMock, optionalUser.get().getUserName());
        assertEquals (nickNameMock, optionalUser.get().getNickName());
        assertEquals ("****",       optionalUser.get().getPassword());
    }

    @Test
    @DisplayName("Testing get student with wrong id ...")
    void testGetStudentWithWrongId () {
        // given
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        final Optional<Student> optionalUser = studentService.getStudent(2);

        // then
        assertTrue   (optionalUser.isEmpty());
    }

}