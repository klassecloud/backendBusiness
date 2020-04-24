package cloud.klasse.backendbusiness.result;

import cloud.klasse.backendbusiness.task.Task;
import cloud.klasse.backendbusiness.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Long id;

    private String title;

    private String comment;

    private Task task;

    private User user;

    private String state;
}
