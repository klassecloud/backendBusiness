package cloud.klasse.backendbusiness.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User model to create an user.
 *
 * @since 0.0.1
 *
 * @see Data
 * @see NoArgsConstructor
 */
@Data
@NoArgsConstructor
public class CreateUserModel {
    private String userName;
    private String nickName;
    private String password;
}
