package cloud.klasse.backendbusiness.teacher;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTeacherModel {

    private String userName;
    private String nickName;
    private String email;
    private String password;
}
