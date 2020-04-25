package cloud.klasse.backendbusiness.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserModel {
    private String userName;
    private String nickName;
}
