package cloud.klasse.backendbusiness.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher createTeacher(final CreateTeacherModel model) {
        Teacher teacher = teacherRepository.save(new Teacher(0, model.getUserName(), model.getNickName(), model.getEmail(),
                model.getPassword(), true));
        log.info("Create a teacher with  id {}.", teacher.getId());

        return teacher;
    }

    public Teacher getTeacher(final long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));

        log.info("Get teacher with id: {}.", teacher.getId());

        return teacher;
    }

    public Teacher getTeacherByEmail(String email) {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(() -> new TeacherNotFoundException(email));
        log.info("Get teacher with email: {}.", teacher.getEmail());

        return teacher;
    }

    public Teacher getTeacherByUserName(String userName) {
        Teacher teacher = teacherRepository.findByUserName(userName)
                .orElseThrow(() -> new TeacherNotFoundException(userName));
        log.info("Get teacher with user name: {}.", teacher.getUserName());

        return teacher;
    }

    public Teacher updateTeacher(UpdateTeacherModel updateTeacherModel, final Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .map(newTeacher -> {
                    newTeacher.setNickname(updateTeacherModel.getNickName());
                    newTeacher.setUserName(updateTeacherModel.getUserName());
                    newTeacher.setEmail(updateTeacherModel.getEmail());

                    return teacherRepository.save(newTeacher);
                } )
                .orElseThrow(() -> new TeacherNotFoundException(id));
        log.info("Update teacher with id: {}.", teacher.getId());

        return teacher;
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
        log.info("Delete teacher with id {}.", id);
    }

}
