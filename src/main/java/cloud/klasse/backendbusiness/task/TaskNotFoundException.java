package cloud.klasse.backendbusiness.task;

/**
 * Specific exception for tasks
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(long id) {
        super("Could not found task with id " + id);
    }
}
