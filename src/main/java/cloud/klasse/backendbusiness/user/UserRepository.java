package cloud.klasse.backendbusiness.user;

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
 * @author sandra.gerberding
 * @since 0.0.1
 *
 * @see Repository
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName (final String userName);
}
