package cloud.klasse.backendbusiness.result;

/**
 * Specific exception for result
 */
public class ResultNotFoundException extends RuntimeException{

    public ResultNotFoundException(final long id) {
        super("Could not find result: " + id);
    }
}
