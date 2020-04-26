package cloud.klasse.backendbusiness.teacher;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException (Long id) {
        super("Could not find teacher: " + id);
    }

    public TeacherNotFoundException(String name) {
        super("Could not find teacher: " + name);
    }
}
