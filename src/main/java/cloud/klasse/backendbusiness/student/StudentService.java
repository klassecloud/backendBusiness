
package cloud.klasse.backendbusiness.student;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User service to manage an student.
 *
 * <p>This class injects the student repository.</p>
 *
 * @since 0.0.1
 *
 * @see Service
 * @see RequiredArgsConstructor
 * @see Slf4j
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final PasswordEncoder passwordEncoder;
    /** injected student repository */
    private final StudentRepository studentRepository;

    /**
     * Create an student with the given create student model {@link CreateStudentModel}.
     *
     * @param createStudentModel create student model
     * @return the created student
     *
     * @since 0.0.1
     */
    Student createStudent (final CreateStudentModel createStudentModel) {
        final var encodedPassword = passwordEncoder.encode(createStudentModel.getPassword());
        final Student student = studentRepository.save(new Student(0, createStudentModel.getUserName(), createStudentModel.getNickName(), encodedPassword, true));
        log.info("Create a student with id {}", student.getId());
        return student;
    }

    /**
     * Get the student with the given id.
     *
     * @param id student id
     * @return an optional of the student
     */
    Optional<Student> getStudent (final long id) {
        final Optional<Student> optionalUser = studentRepository.findById(id);
        log.info("Get student with id: {}", id);
        optionalUser.ifPresent((final Student student) -> student.setPassword("****"));
        return optionalUser;
    }

    public Optional<Student> updateStudent(final long id, final StudentUserModel studentUserModel) {
        final Optional<Student> optionalUser = studentRepository.findById(id);
        log.info("Get student with id: {}", id);

        optionalUser.ifPresent((final Student student) -> {
            student.setUserName(studentUserModel.getUserName());
            student.setNickName(studentUserModel.getNickName());
            studentRepository.save(student);
            log.info("Update student with id: {}", id);
        });
        return optionalUser;
    }
}
