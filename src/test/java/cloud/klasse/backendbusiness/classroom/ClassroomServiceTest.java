package cloud.klasse.backendbusiness.classroom;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
public class ClassroomServiceTest {

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private ClassroomService classroomService;

    private String topicMock;
    private String pushPrivateKeyMock;
    private String pushPublicKeyMock;
    private Classroom classroomMock;

    @BeforeEach
    void setup() {
        topicMock = "Math";
        pushPrivateKeyMock = "asd4dsfdf";
        pushPublicKeyMock = "sdfd3fdf";
        classroomMock = new Classroom(1, topicMock, pushPublicKeyMock, pushPrivateKeyMock);
    }


    @Test
    @DisplayName("Create classroom test.")
    public void createClassroomTest() {

        CreateClassroomModel createClassroomModel = new CreateClassroomModel();
        createClassroomModel.setTopic(topicMock);
        createClassroomModel.setPushPublicKey(pushPublicKeyMock);
        createClassroomModel.setPushPrivateKey(pushPrivateKeyMock);

        when(classroomRepository.save(any(Classroom.class))).thenReturn(classroomMock);

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


}
