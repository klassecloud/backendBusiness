package cloud.klasse.backendbusiness.subject;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.classroom.ClassroomRepository;
import cloud.klasse.backendbusiness.task.Task;
import cloud.klasse.backendbusiness.task.TaskRepository;
import cloud.klasse.backendbusiness.teacher.Teacher;
import cloud.klasse.backendbusiness.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DemoSubjectCreator implements ApplicationListener<ApplicationReadyEvent> {

    private final PasswordEncoder passwordEncoder;

    private final ClassroomRepository classroomRepository;

    private final TeacherRepository teacherRepository;

    private final SubjectRepository subjectRepository;

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        final var klaus = teacherRepository.save(new Teacher(0L, "klaus", "klaus", "klaus@klasse.cloud", passwordEncoder.encode("12345678"), true));
        final var classroom = classroomRepository.save(new Classroom(0L, "6B", "", "", klaus));
        final var math = subjectRepository.save(new Subject("Mathematik", "1 + 1 = 3 for large values of 1", classroom));
        taskRepository.save(new Task(math, "Tu was!", "Irgendwas!", Timestamp.valueOf(LocalDateTime.now().plusWeeks(2))));
        taskRepository.save(new Task(math, "Tu noch was!", "Irgendwas anderes!", Timestamp.valueOf(LocalDateTime.now().plusWeeks(2))));
    }

}
