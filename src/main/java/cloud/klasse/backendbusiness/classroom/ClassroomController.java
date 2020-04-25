package cloud.klasse.backendbusiness.classroom;

import cloud.klasse.backendbusiness.student.CreateStudentModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to manage classroom.
 *
 * <p>This class injects the classroom service.</p>
 *
 * @since 0.0.1
 *
 * @see RestController
 * @see RequestMapping
 * @see RequiredArgsConstructor
 * @see Slf4j
 */

@RestController
@RequestMapping("/classroom")
@RequiredArgsConstructor
@Slf4j
public class ClassroomController {

    final private ClassroomService classroomService;

    /**
     * Post mapping {@link PostMapping} to create a classroom with the given request body {@link RequestBody} as
     * create classroom model {@link CreateStudentModel} and returns an classroom response entity {@link ResponseEntity}.
     *
     * @param createClassroomModel create classromm model
     * @return the response entity {@link ResponseEntity} of classroom
     *
     * @since 0.0.1
     */
    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody final CreateClassroomModel createClassroomModel) {
        final Classroom classroom = classroomService.createClassroom(createClassroomModel);

        return new ResponseEntity<>(classroom, HttpStatus.CREATED);
    }


    /**
     * Get mapping {@link GetMapping} to get a classroom response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id classroom id
     * @return the response entity {@link ResponseEntity} of classroom
     *
     * @since 0.0.1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable final long id) {
        final Classroom classroom = classroomService.getClassroom(id);

        return new ResponseEntity<>(classroom, HttpStatus.OK);
    }


    /**
     * Put mapping {@link PutMapping} to update a classroom response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id classroom id
     * @return the response entity {@link ResponseEntity} of classroom
     *
     * @since 0.0.1
     */
    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@RequestBody final UpdateClassroomModel updateClassroomModel,
                                                     final long id) {
        final Classroom classroom = classroomService.updateClassroom(updateClassroomModel, id);
        return  new ResponseEntity<>(classroom, HttpStatus.OK);
    }

    /**
     * Delete mapping {@link DeleteMapping} to delete a classroom response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id classroom id
     * @return the response entity {@link ResponseEntity} of classroom
     *
     * @since 0.0.1
     */
    @DeleteMapping("/{id}")
    public void deleteClassroom(final long id) {
        classroomService.deleteClassroom(id);
    }
}
