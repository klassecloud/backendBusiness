package cloud.klasse.backendbusiness.classroom;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classroom model to update an classroom.
 *
 * @since 0.0.1
 *
 * @see Data
 * @see NoArgsConstructor
 */
@Data
@NoArgsConstructor
public class UpdateClassroomModel {
    private String topic;
}
