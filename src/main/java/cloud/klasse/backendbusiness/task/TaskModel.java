package cloud.klasse.backendbusiness.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Task model to manage a task.
 *
 * @since 0.0.1
 *
 * @see Data
 * @see NoArgsConstructor
 */
@Data
@NoArgsConstructor
public class TaskModel {
    private String title;
    private String content;
    private Timestamp dueDate;
}
