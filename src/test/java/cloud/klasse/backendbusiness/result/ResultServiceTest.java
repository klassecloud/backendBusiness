package cloud.klasse.backendbusiness.result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cloud.klasse.backendbusiness.student.Student;
import cloud.klasse.backendbusiness.student.StudentRepository;
import cloud.klasse.backendbusiness.task.Task;
import cloud.klasse.backendbusiness.task.TaskNotFoundException;
import cloud.klasse.backendbusiness.task.TaskRepository;
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
public class ResultServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

    private String titleMock;
    private String commentMock;
    private String stateMock;
    private Result resultMock;

    @Mock
    private Task taskMock;
    @Mock
    private Student studentMock;

    @BeforeEach
    void setup() {
        titleMock = "title.mock";
        commentMock = "comment.mock";
        stateMock = "state.mock";

        resultMock = new Result(1, titleMock, commentMock, stateMock, taskMock, studentMock);
    }

    @Test
    @DisplayName("Create result test")
    public void testCreateResult() {

        when(studentRepository.findByUserName(anyString())).thenReturn(studentMock);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskMock));
        when(resultRepository.save(any(Result.class))).thenReturn(resultMock);
        mockSecurityContext();

        CreateResultModel createResultModel = new CreateResultModel();
        createResultModel.setComment(commentMock);
        createResultModel.setState(stateMock);
        createResultModel.setTaskId(taskMock.getId());
        createResultModel.setTitle(titleMock);

        var result = resultService.createResult(createResultModel);

        assertEquals(commentMock, result.getComment());
        assertEquals(stateMock, result.getState());
        assertEquals(titleMock, result.getTitle());
        assertEquals(taskMock.getId(), result.getTask().getId());

    }

    @Test
    @DisplayName("Create result with wrong task id")
    public void testCreateResultWithWrongTaskId() {

        when(studentRepository.findByUserName(anyString())).thenReturn(studentMock);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
        mockSecurityContext();

        CreateResultModel createResultModel = new CreateResultModel();
        createResultModel.setComment(commentMock);
        createResultModel.setState(stateMock);
        createResultModel.setTaskId(taskMock.getId());
        createResultModel.setTitle(titleMock);

        assertThrows(TaskNotFoundException.class, () -> resultService.createResult(createResultModel));
    }

    @Test
    @DisplayName("Get result test")
    public void testGetResult() {
        when(resultRepository.findById(anyLong())).thenReturn(Optional.of(resultMock));

        var result = resultService.getResult(1);

        assertEquals(1, result.getId());
        assertEquals(titleMock, result.getTitle());
        assertEquals(commentMock, result.getComment());
        assertEquals(stateMock, result.getState());
    }

    @Test
    @DisplayName("Testing get result with wrong id.")
    public void testGetResultWithWrongId() {
        when(resultRepository.findById(anyLong())).thenThrow(new ResultNotFoundException(1));
        assertThrows(ResultNotFoundException.class, () -> resultService.getResult(1));
    }

    @Test
    @DisplayName("Update result test")
    public void testUpdateResult() {
        when(resultRepository.findById(anyLong())).thenReturn(Optional.of(resultMock));
        when(resultRepository.save(any(Result.class))).thenReturn(resultMock);

        UpdateResultModel updateResultModel = new UpdateResultModel();
        updateResultModel.setComment(commentMock);
        updateResultModel.setTitle(titleMock);
        updateResultModel.setState(stateMock);

        var result = resultService.updateResult(updateResultModel, 1);

        assertEquals(commentMock, result.getComment());
        assertEquals(titleMock, result.getTitle());
        assertEquals(stateMock, result.getState());
    }

    @Test
    @DisplayName("Update result with wrong id test.")
    public void testUpdateResultWithWrongId() {
        when(resultRepository.findById(anyLong())).thenThrow(new ResultNotFoundException(1));

        var updateResultModel = new UpdateResultModel();
        updateResultModel.setComment(commentMock);
        updateResultModel.setTitle(titleMock);
        updateResultModel.setState(stateMock);

        assertThrows(ResultNotFoundException.class, () -> resultService.updateResult(updateResultModel, 1));
    }

    private void mockSecurityContext() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication().getPrincipal()).thenReturn("student");
    }

}
