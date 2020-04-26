package cloud.klasse.backendbusiness.user;

import lombok.Data;

@Data
public class LoginResult {

    private final String userName;

    public LoginResult(final User user) {
        this.userName = user.getUserName();
    }

}
