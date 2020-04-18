package cloud.klasse.backendbusiness.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserModel {
    private String userName;
    private String nickName;
    private String password;
}
