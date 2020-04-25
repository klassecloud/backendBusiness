package cloud.klasse.backendbusiness.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring will create an implementation of the following methods automatically on startup:
 *  - save (insert or update)
 *  - saveAll
 *  - findById
 *  - existsById
 *  - findAll
 *  - findAllById
 *  - count
 *  - deleteById
 *  - delete
 *  - deleteAll
 *
 * @since 0.0.1
 *
 * @see Repository
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByUserName (final String userName);
}
