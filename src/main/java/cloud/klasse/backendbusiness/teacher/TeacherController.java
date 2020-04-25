package cloud.klasse.backendbusiness.teacher;

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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody final CreateTeacherModel createTeacherModel) {
        final Teacher teacher = teacherService.createTeacher(createTeacherModel);
        log.info("Teacher {} was created.", teacher.getUserName());

        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable final long id) {
        final Teacher teacher = teacherService.getTeacher(id);
        log.info("Teacher with id {} was found.", teacher.getId());

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String email) {
        final Teacher teacher = teacherService.getTeacherByEmail(email);
        log.info("Teacher with email {} was found.", teacher.getEmail());

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody UpdateTeacherModel updateTeacherModel,
                                                 @PathVariable final long id) {
        final Teacher teacher = teacherService.updateTeacher(updateTeacherModel, id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

}
