package cloud.klasse.backendbusiness.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to manage task.
 *
 * <p>This class injects the task service.</p>
 *
 * @since 0.0.1
 *
 * @see RestController
 * @see RequestMapping
 * @see RequiredArgsConstructor
 * @see Slf4j
 */

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    /**
     * Post mapping {@link PostMapping} to create a task with the given request body {@link RequestBody} as
     * task model {@link TaskModel} and returns a task response entity {@link ResponseEntity}.
     *
     * @param taskModel task model
     * @return the response entity {@link ResponseEntity} of task
     *
     * @since 0.0.1
     */

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody final TaskModel taskModel) {
        Task task = taskService.createTask(taskModel);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    /**
     * Get mapping {@link GetMapping} to get a task response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id task id
     * @return the response entity {@link ResponseEntity} of task
     *
     * @since 0.0.1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable final long id) {
        Task task = taskService.getTask(id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    /**
     * Put mapping {@link PutMapping} to update a task response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param taskModel  task model
     * @param id task id
     * @return the response entity {@link ResponseEntity} of task
     *
     * @since 0.0.1
     */
    @PutMapping("/{id}")
    public ResponseEntity updateTask(@RequestBody final TaskModel taskModel, @PathVariable final long id) {
        Task task = taskService.updateTask(taskModel, id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    /**
     * Delete mapping {@link DeleteMapping} to delete a task from the given
     * id.
     *
     * @param id task id
     *
     * @since 0.0.1
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable  final long id) {
        taskService.deleteTask(id);
    }

}
