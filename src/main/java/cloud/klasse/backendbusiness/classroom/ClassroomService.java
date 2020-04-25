package cloud.klasse.backendbusiness.classroom;

import cloud.klasse.backendbusiness.student.CreateStudentModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Classroom service to manage an classroom.
 *
 * <p>This class injects the classrom repository.</p>
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
public class ClassroomService {

    private final ClassroomRepository classroomRepository;


    /**
     * Create an classroom with the given create classroom model {@link CreateClassroomModel}.
     *
     * @param createClassroomModel create classroom model
     * @return the created classroom
     *
     * @since 0.0.1
     */
    public Classroom createClassroom(CreateClassroomModel createClassroomModel) {
        Classroom classroom = classroomRepository.save(new Classroom(0, createClassroomModel.getTopic(),
                createClassroomModel.getPushPublicKey(), createClassroomModel.getPushPrivateKey()));

        log.info("Create a classroom with id {}.", classroom.getId());

        return classroom;
    }


    /**
     * Get the classroom with the given id.
     *
     * @param id classroom id
     * @return a classroom
     * @throws {@link ClassroomNotFoundException}  if classroom is not found
     */
    public Classroom getClassroom(long id) {
        Classroom classroom = classroomRepository.findById(id).orElseThrow( () -> new ClassroomNotFoundException(id));
        log.info("Get classroom with id {}.", classroom.getId());

        return classroom;
    }

    /**
     * Update the classroom with the given id.
     *
     * @param id classroom id
     * @param updateClassroomModel update class model
     * @return a classroom
     * @throws {@link ClassroomNotFoundException}  if classroom is not found
     */
    public Classroom updateClassroom(UpdateClassroomModel updateClassroomModel, long id) {

        Classroom classroom = classroomRepository.findById(id)
                .map(cr -> {
                    cr.setTopic(updateClassroomModel.getTopic());

                    return classroomRepository.save(cr);
                }).orElseThrow(() -> new ClassroomNotFoundException(id));
        log.info("Update classroom with id {},", classroom.getId());

        return classroom;
    }

    /**
     * Delete the classroom with the given id.
     *
     * @param id classroom id
     *
     */
    public void deleteClassroom(long id) {
        classroomRepository.deleteById(id);

        log.info("Delete classroom with id {}.", id);
    }
    
}
