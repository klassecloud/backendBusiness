package cloud.klasse.backendbusiness.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classroom repository
 */
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
