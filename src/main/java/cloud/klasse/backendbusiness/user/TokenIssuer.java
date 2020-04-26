package cloud.klasse.backendbusiness.user;

public interface TokenIssuer {

    String createToken(final String username);

}
