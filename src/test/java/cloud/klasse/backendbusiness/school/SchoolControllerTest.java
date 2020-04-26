package cloud.klasse.backendbusiness.school;

import cloud.klasse.backendbusiness.teacher.Teacher;
import cloud.klasse.backendbusiness.teacher.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SchoolControllerTest {

    private final List<School> schools = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();

    private final SchoolRepository schoolRepository = mock(SchoolRepository.class);
    private final TeacherRepository teacherRepository = mock(TeacherRepository.class);

    private final SchoolController classUnderTest = new SchoolController(schoolRepository, teacherRepository);

    @BeforeEach
    void setUp() {
        schools.clear();
        teachers.clear();

        reset(schoolRepository, teacherRepository);

        when(schoolRepository.save(any())).thenAnswer(invocation -> {
            final var school = invocation.getArgument(0, School.class);
            schools.add(school);
            school.setId((long) schools.size());
            return school;
        });

        when(schoolRepository.findById(any())).thenAnswer(invocation -> {
            final long id = invocation.getArgument(0, Long.class);
            return id > 0 && id <= schools.size()
                    ? Optional.of(schools.get((int) (id - 1)))
                    : Optional.empty();
        });

        when(teacherRepository.findById(any())).thenAnswer(invocation -> {
            final long id = invocation.getArgument(0, Long.class);
            return id > 0 && id <= teachers.size()
                    ? Optional.of(teachers.get((int) (id - 1)))
                    : Optional.empty();
        });
    }

    @Test
    @DisplayName("Create school")
    void createSchool() {
        final var attributes = new SchoolAttributes();
        attributes.setName("Some School");
        attributes.setStreet("Some Street 42");
        attributes.setZip(12345);
        attributes.setCity("Some City");

        final var school = classUnderTest.createSchool(attributes);

        assertThat(school.getId()).isEqualTo(1L);
        assertThat(school.getName()).isEqualTo("Some School");
        assertThat(school.getStreet()).isEqualTo("Some Street 42");
        assertThat(school.getZip()).isEqualTo(12345);
        assertThat(school.getCity()).isEqualTo("Some City");

        assertThat(schools).contains(school);
    }

    @Test
    @DisplayName("Get existing school")
    void getExistingSchool() {
        final var existingSchool = new School();
        existingSchool.setId(1L);
        existingSchool.setName("Some School");
        existingSchool.setStreet("Some Street 42");
        existingSchool.setZip(12345);
        existingSchool.setCity("Some City");
        schools.add(existingSchool);

        final var school = classUnderTest.getSchool(1).getBody();

        assertThat(school.getId()).isEqualTo(1L);
        assertThat(school.getName()).isEqualTo("Some School");
        assertThat(school.getStreet()).isEqualTo("Some Street 42");
        assertThat(school.getZip()).isEqualTo(12345);
        assertThat(school.getCity()).isEqualTo("Some City");

        assertThat(schools).contains(school);
    }

    @Test
    @DisplayName("School not found")
    void schoolNotFound() {
        final var attributes = new SchoolAttributes();
        attributes.setName("Some School");
        attributes.setStreet("Some Street 42");
        attributes.setZip(12345);
        attributes.setCity("Some City");

        assertThat(classUnderTest.getSchool(25).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(classUnderTest.updateSchool(25, attributes).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Update school")
    void updateSchool() {
        final var existingSchool = new School();
        existingSchool.setId(1L);
        existingSchool.setName("Some School");
        existingSchool.setStreet("Some Street 42");
        existingSchool.setZip(12345);
        existingSchool.setCity("Some City");
        schools.add(existingSchool);

        final var attributes = new SchoolAttributes();
        attributes.setName("Some other School");
        attributes.setStreet("Some other Street 42");
        attributes.setZip(67890);
        attributes.setCity("Some other City");

        final var responseEntity = classUnderTest.updateSchool(1L, attributes);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final var school = responseEntity.getBody();
        assertThat(school).isNotNull();
        assertThat(school.getId()).isEqualTo(1L);
        assertThat(school.getName()).isEqualTo("Some other School");
        assertThat(school.getStreet()).isEqualTo("Some other Street 42");
        assertThat(school.getZip()).isEqualTo(67890);
        assertThat(school.getCity()).isEqualTo("Some other City");
    }

    @Test
    @DisplayName("Add teacher to school")
    void addTeacher() {
        final var existingSchool = new School();
        existingSchool.setId(1L);
        existingSchool.setName("Some School");
        existingSchool.setStreet("Some Street 42");
        existingSchool.setZip(12345);
        existingSchool.setCity("Some City");
        schools.add(existingSchool);

        final var existingTeacher = new Teacher();
        existingTeacher.setId(1L);
        existingTeacher.setUserName("whatever");
        teachers.add(existingTeacher);

        final var responseEntity = classUnderTest.addTeacher(1, 1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final var school = responseEntity.getBody();
        assertThat(school).isNotNull();
        assertThat(school.getTeachers()).containsExactly(existingTeacher);
    }

    @Test
    @DisplayName("Remove teacher from school")
    void removeTeacher() {
        final var existingTeacher = new Teacher();
        existingTeacher.setId(1L);
        existingTeacher.setUserName("whatever");
        teachers.add(existingTeacher);

        final var existingSchool = new School();
        existingSchool.setId(1L);
        existingSchool.setName("Some School");
        existingSchool.setStreet("Some Street 42");
        existingSchool.setZip(12345);
        existingSchool.setCity("Some City");
        existingSchool.getTeachers().add(existingTeacher);
        schools.add(existingSchool);

        final var responseEntity = classUnderTest.removeTeacher(1, 1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final var school = responseEntity.getBody();
        assertThat(school).isNotNull();
        assertThat(teachers).contains(existingTeacher);
        assertThat(school.getTeachers()).doesNotContain(existingTeacher);
    }

}
