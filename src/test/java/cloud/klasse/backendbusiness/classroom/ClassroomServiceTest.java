package cloud.klasse.backendbusiness.classroom;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

import cloud.klasse.backendbusiness.teacher.Teacher;
import cloud.klasse.backendbusiness.teacher.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTest {

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private ClassroomService classroomService;

    private String topicMock;
    private String pushPrivateKeyMock;
    private String pushPublicKeyMock;
    private Classroom classroomMock;
    @Mock
    private Teacher teacherMock;

    @BeforeEach
    void setup() {
        topicMock = "Math";
        pushPrivateKeyMock = "asd4dsfdf";
        pushPublicKeyMock = "sdfd3fdf";
        classroomMock = new Classroom(1, topicMock, pushPublicKeyMock, pushPrivateKeyMock, teacherMock);
    }


    @Test
    @DisplayName("Create classroom test.")
    public void createClassroomTest() {

        CreateClassroomModel createClassroomModel = new CreateClassroomModel();
        createClassroomModel.setTopic(topicMock);
        teacherMock.setUserName("teacher");
        mockSecurityContext();
        when(classroomRepository.save(any(Classroom.class))).thenReturn(classroomMock);
        when(teacherRepository.findByUserName(anyString())).thenReturn(Optional.of(teacherMock));

        Classroom classroom = classroomService.createClassroom(createClassroomModel);
        assertEquals(1, classroom.getId());
        assertEquals(topicMock, classroom.getTopic());
        assertEquals(pushPrivateKeyMock, classroom.getPushPrivateKey());
        assertEquals(pushPublicKeyMock, classroom.getPushPublicKey());

    }
    @Test
    @DisplayName("Create classroom test.")
    public void testGetClassroom() {
        when(classroomRepository.findById(anyLong())).thenReturn(Optional.of(classroomMock));

        Classroom classroom = classroomService.getClassroom(1);

        assertEquals(topicMock, classroom.getTopic());
        assertEquals(pushPrivateKeyMock, classroom.getPushPrivateKey());
        assertEquals(pushPublicKeyMock, classroom.getPushPublicKey());

    }

    @Test
    @DisplayName("Testing get classroom with wrong id.")
    public void testGetClassroomWithWrongId() {
        when(classroomRepository.findById(anyLong())).thenThrow(new ClassroomNotFoundException(1));
        assertThrows(ClassroomNotFoundException.class, () -> classroomService.getClassroom(1));
    }

    @Test
    @DisplayName("Testing update classroom")
    public void testUpdateClassroom() {

        when(classroomRepository.findById(anyLong())).thenReturn(Optional.of(classroomMock));
        when(classroomRepository.save(any(Classroom.class))).thenReturn(classroomMock);

        UpdateClassroomModel updateClassroomModel = new UpdateClassroomModel();
        updateClassroomModel.setTopic(topicMock);
        final Classroom classroom = classroomService.updateClassroom(updateClassroomModel, 1L);
        assertEquals(topicMock, classroom.getTopic());
    }

    @Test
    @DisplayName("Testing update classroom with wrong id")
    public void testUpdateClassroomWithWrongId() {
        when(classroomRepository.findById(anyLong())).thenThrow(new ClassroomNotFoundException(1));

        UpdateClassroomModel updateClassroomModel = new UpdateClassroomModel();
        updateClassroomModel.setTopic(topicMock);

        assertThrows(ClassroomNotFoundException.class, () -> classroomService.updateClassroom(updateClassroomModel,1));
    }

    private void mockSecurityContext() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication().getPrincipal()).thenReturn("teacher");
    }


}
