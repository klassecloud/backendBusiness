package cloud.klasse.backendbusiness.teacher;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTeacherModel {
    private String userName;
    private String nickName;
    private String email;
}
