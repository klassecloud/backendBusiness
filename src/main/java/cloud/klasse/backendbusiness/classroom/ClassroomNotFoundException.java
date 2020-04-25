package cloud.klasse.backendbusiness.classroom;

/**
 * Specific exception for classroom.
 */
public class ClassroomNotFoundException extends RuntimeException {

    public ClassroomNotFoundException(long id) {

        super("Could not find classroom: " + id);
    }
}
