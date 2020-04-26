package cloud.klasse.backendbusiness.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
/**
 * Task service to manage a task.
 *
 * <p>This class injects the task repository.</p>
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
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Create task with the given create task model {@link TaskModel}.
     *
     * @param taskModel task model
     * @return the created task
     *
     * @since 0.0.1
     */
    public Task createTask(final TaskModel taskModel) {
        Task task = taskRepository.save(new Task(0, taskModel.getTitle(), taskModel.getContent(), taskModel.getDueDate()));
        log.info("Create task with id {}.", task.getId());

        return task;
    }

    /**
     * Get the task with the given id.
     *
     * @param id task id
     * @return a task
     * @throws {@link TaskNotFoundException}  if task is not found
     */
    public Task getTask(final long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        log.info("Get task with id {}.", task.getId());

        return task;
    }

    /**
     * Update the task with the given id.
     *
     * @param id task id
     * @param taskModel task model
     * @return a task
     * @throws {@link TaskNotFoundException}  if task is not found
     */
    public Task updateTask(final TaskModel taskModel, final long id) {
        Task task = taskRepository.findById(id)
                .map(tsk -> {
                    tsk.setContent(taskModel.getContent());
                    tsk.setTitle(taskModel.getTitle());
                    tsk.setDueDate(taskModel.getDueDate());

                    return taskRepository.save(tsk);
                } ).orElseThrow(() -> new TaskNotFoundException(id));

        log.info("Update task with id {}.", task.getId());

        return task;
    }

    /**
     * Delete a task with a given id
     * @param id
     */
    public void deleteTask(final long id) {
        taskRepository.deleteById(id);
        log.info("Delete task with id {}", id);
    }
}
