package cloud.klasse.backendbusiness.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result model to update an result.
 *
 * @since 0.0.1
 *
 * @see Data
 * @see NoArgsConstructor
 */
@Data
@NoArgsConstructor
public class UpdateResultModel {
    private String title;
    private String comment;
    private String state;
}
