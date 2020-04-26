package cloud.klasse.backendbusiness.result;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to manage result.
 *
 * <p>This class injects the result service.</p>
 *
 * @since 0.0.1
 *
 * @see RestController
 * @see RequestMapping
 * @see RequiredArgsConstructor
 * @see Slf4j
 */

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
@Slf4j
public class ResultController {

    private final ResultService resultService;

    /**
     * Post mapping {@link PostMapping} to create a result with the given request body {@link RequestBody} as
     * create classroom model {@link CreateResultModel} and returns a result response entity {@link ResponseEntity}.
     *
     * @param createResultModel create result model
     * @return the response entity {@link ResponseEntity} of result
     *
     * @since 0.0.1
     */
    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody final CreateResultModel createResultModel) {
        var result = resultService.createResult(createResultModel);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Get mapping {@link GetMapping} to get a result response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id result id
     * @return the response entity {@link ResponseEntity} of result
     *
     * @since 0.0.1
     */
    @GetMapping("{/id}")
    public ResponseEntity<Result> getResult(@PathVariable final long id) {
       var result = resultService.getResult(id);

       return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Put mapping {@link PutMapping} to update a result response entity {@link ResponseEntity} from the given
     * id.
     *
     * @param id result id
     * @param updateResultModel update result model
     * @return the response entity {@link ResponseEntity} of result
     *
     * @since 0.0.1
     */
    @PutMapping("{/id}")
    public ResponseEntity<Result> updateResult(@RequestBody final UpdateResultModel updateResultModel, @PathVariable final long id) {
        var result = resultService.updateResult(updateResultModel, id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Delete mapping {@link DeleteMapping} to delete a result from the given id.
     *
     * @param id result id
     *
     * @since 0.0.1
     */
    @DeleteMapping("{/id}")
    public void deleteResult(@PathVariable final long id) {
        resultService.deleteResult(id);
    }
}
