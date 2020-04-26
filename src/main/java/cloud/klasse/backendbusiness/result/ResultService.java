package cloud.klasse.backendbusiness.result;

import cloud.klasse.backendbusiness.student.StudentRepository;
import cloud.klasse.backendbusiness.task.TaskNotFoundException;
import cloud.klasse.backendbusiness.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Result service to manage a result
 *
 * <p>This class injects the result repository.</p>
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
public class ResultService {

    private final ResultRepository resultRepository;

    private final TaskRepository taskRepository;

    private final StudentRepository studentRepository;

    /**
     * Create an result with the given create result model {@link CreateResultModel}.
     *
     * @param createResultModel create result model
     * @return the created result
     *
     * @since 0.0.1
     */
    public Result createResult(CreateResultModel createResultModel) {

        var userName = Objects.toString(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var student = studentRepository.findByUserName(userName);
        var task = taskRepository.findById(createResultModel.getTaskId());

        if(!task.isPresent()) {
            log.info("Task id {} not found. Result can not be created.", createResultModel.getTaskId());
            throw new TaskNotFoundException(createResultModel.getTaskId());
        }

        var result = resultRepository.save(new Result(0, createResultModel.getTitle(), createResultModel.getComment(),
                createResultModel.getState(), task.get(), student));
        log.info("Create result with id {}.", result.getId());

        return result;
    }

    /**
     * Get the result with the given id.
     *
     * @param id result id
     * @return a result
     * @throws {@link ResultNotFoundException}  if result is not found
     */
    public Result getResult(final long id) {
        var result = resultRepository.findById(id)
                .orElseThrow(() -> new ResultNotFoundException(id));
        log.info("Get result with id {}.", result.getId());

        return result;
    }

    /**
     * Update the result with the given id.
     *
     * @param id result id
     * @param updateResultModel update result model
     * @return a classroom
     * @throws {@link ResultNotFoundException}  if result is not found
     */
    public Result updateResult(final UpdateResultModel updateResultModel, final long id ) {

        var result = resultRepository.findById(id)
                .map(rs -> {
                    rs.setTitle(updateResultModel.getTitle());
                    rs.setComment(updateResultModel.getComment());
                    rs.setState(updateResultModel.getState());

                    return resultRepository.save(rs);
                }).orElseThrow(() -> new ResultNotFoundException(id));

        log.info("Update result with id {}", result.getId());
        return result;
    }

    /**
     * Delete the result with the given id.
     *
     * @param id result id
     *
     */
    public void deleteResult(final long id) {
        resultRepository.deleteById(id);
        log.info("Delete result with id {}.", id);
    }
}
