package cloud.klasse.backendbusiness.student;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller to manage student.
 *
 * <p>This class injects the student service.</p>
 *
 * @since 0.0.1
 *
 * @see RestController
 * @see RequiredArgsConstructor
 * @see Slf4j
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    /** injected student service */
    private final StudentService studentService;

    /**
     * Post mapping {@link PostMapping} to create an student with the given request body {@link RequestBody} as
     * create student model {@link CreateStudentModel} and returns an user response entity {@link ResponseEntity}.
     *
     * @param createStudentModel create student model
     * @return the response entity {@link ResponseEntity} of student
     *
     * @since 0.0.1
     */
    @PostMapping("/user")
    public ResponseEntity<Student> createStudent (@RequestBody final CreateStudentModel createStudentModel) {
        final Student student = studentService.createStudent(createStudentModel);
        log.info("Created student with userName: {}", student.getUserName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    /**
     * Get mapping {@link GetMapping} to get an student response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id user id
     * @return the response entity {@link ResponseEntity} of student
     *
     * @since 0.0.1
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<Student> getStudent (@PathVariable final long id) {
        final Optional<Student> optionalUser = studentService.getStudent(id);
        return optionalUser.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Student> updateUser(@RequestBody final StudentUserModel studentUserModel, @PathVariable final long id) {
        final Optional<Student> optionalUpdateUser = studentService.updateStudent(id, studentUserModel);
        return optionalUpdateUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
