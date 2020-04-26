package cloud.klasse.backendbusiness.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private String titleMock;
    private String contentMock;
    private Timestamp dueDateMock;
    private Task taskMock;

    @BeforeEach
    void setup() {
        titleMock = "titleMock";
        contentMock= "contentmock";
        dueDateMock = new Timestamp(12334);
        taskMock = new Task(1, titleMock, contentMock, dueDateMock);
    }

    @Test
    @DisplayName("Create task test")
    public void testCreateTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(taskMock);

        TaskModel taskModelMock = new TaskModel();
        taskModelMock.setContent(contentMock);
        taskModelMock.setTitle(titleMock);
        taskModelMock.setDueDate(dueDateMock);

        Task task = taskService.createTask(taskModelMock);

        assertEquals(1, task.getId());
        assertEquals(titleMock, task.getTitle());
        assertEquals(contentMock, task.getContent());
        assertEquals(dueDateMock, task.getDueDate());

    }

    @Test
    @DisplayName("Get task test")
    public void testGetTask() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskMock));

        Task task = taskService.getTask(1);

        assertEquals(titleMock, task.getTitle());
        assertEquals(contentMock, task.getContent());
        assertEquals(dueDateMock, task.getDueDate());
    }

    @Test
    @DisplayName("Get task with wrong id test")
    public void testGetTaskWithWrongId() {
        when(taskRepository.findById(anyLong())).thenThrow( new TaskNotFoundException(1));
        assertThrows(TaskNotFoundException.class, () -> taskService.getTask(1));
    }

    @Test
    @DisplayName("Update task test")
    public void testUpdateTask() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskMock));

        when(taskRepository.save(any(Task.class))).thenReturn(taskMock);

        TaskModel taskModel = new TaskModel();
        taskModel.setContent(contentMock);
        taskModel.setTitle(titleMock);
        taskModel.setDueDate(dueDateMock);

        Task task = taskService.updateTask(taskModel, 1);

        assertEquals(titleMock, task.getTitle());
        assertEquals(contentMock, task.getContent());
        assertEquals(dueDateMock, task.getDueDate());
    }

    @Test
    @DisplayName("Update task with wrong id test ")
    public void testUpdateTaskWithWrongId() {
        when(taskRepository.findById(anyLong())).thenThrow( new TaskNotFoundException(1));

        TaskModel taskModel = new TaskModel();
        taskModel.setContent(contentMock);
        taskModel.setTitle(titleMock);
        taskModel.setDueDate(dueDateMock);

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskModel, 1));
    }
}
