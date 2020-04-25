package cloud.klasse.backendbusiness.teacher;

public class TeacherNotFoundException extends RuntimeException {

    TeacherNotFoundException (Long id) {
        super("Could not find teacher: " + id);
    }

    TeacherNotFoundException(String name) {
        super("Could not find teacher: " + name);
    }
}
