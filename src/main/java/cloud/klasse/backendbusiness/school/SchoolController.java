package cloud.klasse.backendbusiness.school;

import cloud.klasse.backendbusiness.teacher.Teacher;
import cloud.klasse.backendbusiness.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolRepository schoolRepository;

    private final TeacherRepository teacherRepository;

    /**
     * Create a school with the requested {@link SchoolAttributes} and return the resulting {@link School} object.
     *
     * @param attributes Attributes of the new {@link School}. Missing attributes are set to default values if possible
     * @return The created {@link School}
     */
    @PostMapping
    public School createSchool(final SchoolAttributes attributes) {
        final var school = schoolRepository.save(attributes.mergeInto(new School()));
        log.info("School {} created with name '{}'", school.getId(), school.getName());
        return school;
    }

    /**
     * Get the {@link School} with the requested id if it exists. Returns {@link HttpStatus#NOT_FOUND} otherwise.
     *
     * @param id id of a school {@link School}
     * @return The requested {@link School} if present
     */
    @GetMapping("/{id}")
    public ResponseEntity<School> getSchool(@PathVariable final long id) {
        return schoolRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update the {@link School} with the specified id if it exists. Returns {@link HttpStatus#NOT_FOUND} otherwise.
     *
     * @param id         id of a school {@link School}
     * @param attributes The attributes for the school update
     * @return The updated {@link School} if present
     */
    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable final long id, @RequestBody final SchoolAttributes attributes) {
        return schoolRepository.findById(id)
                .map(attributes::mergeInto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete and return the {@link School} with the specified id if it exists. Returns {@link HttpStatus#NOT_FOUND} otherwise.
     *
     * @param id id of a school {@link School}
     * @return The updated {@link School} if present
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<School> deleteSchool(@PathVariable final long id) {
        return schoolRepository.findById(id)
                .map(school -> {
                    schoolRepository.delete(school);
                    return school;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Add the teacher identified by tid to the school identified by sid. If either does not exist it returns {@link HttpStatus#NOT_FOUND}.
     *
     * @param sid id of a school {@link School}
     * @param tid id of a teacher {@link Teacher}
     * @return The updated {@link School} if present
     */
    @PostMapping("/{sid}/teacher/{tid}")
    public ResponseEntity<School> addTeacher(@PathVariable final long sid, @PathVariable final long tid) {
        return updateTeachers(sid, tid, List::add);
    }

    /**
     * Remove the teacher identified by tid to the school identified by sid. If either does not exist it returns {@link HttpStatus#NOT_FOUND}.
     *
     * @param sid id of a school {@link School}
     * @param tid id of a teacher {@link Teacher}
     * @return The updated {@link School} if present
     */
    @DeleteMapping("/{sid}/teacher/{tid}")
    public ResponseEntity<School> removeTeacher(@PathVariable final long sid, @PathVariable final long tid) {
        return updateTeachers(sid, tid, List::remove);
    }

    private ResponseEntity<School> updateTeachers(final long sid, final long tid, final BiConsumer<List<Teacher>, Teacher> operation) {
        final var schoolEntity = schoolRepository.findById(sid);
        if (schoolEntity.isEmpty()) return ResponseEntity.notFound().build();
        final var teacherEntity = teacherRepository.findById(tid);
        if (teacherEntity.isEmpty()) return ResponseEntity.notFound().build();
        final var school = schoolEntity.get();
        final var teacher = teacherEntity.get();
        operation.accept(school.getTeachers(), teacher);
        return ResponseEntity.ok(schoolRepository.save(school));
    }

}
