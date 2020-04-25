package cloud.klasse.backendbusiness.classroom;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classroom model to create an classroom.
 *
 * @since 0.0.1
 *
 * @see Data
 * @see NoArgsConstructor
 */
@Data
@NoArgsConstructor
public class CreateClassroomModel {
    private String topic;
    private String pushPublicKey;
    private String pushPrivateKey;
}
