package cloud.klasse.backendbusiness.task;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Task repository.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
