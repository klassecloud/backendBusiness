package cloud.klasse.backendbusiness.student;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User model to create an student.
 *
 * @since 0.0.1
 *
 * @see Data
 * @see NoArgsConstructor
 */
@Data
@NoArgsConstructor
public class CreateStudentModel {
    private String userName;
    private String nickName;
    private String password;
}
