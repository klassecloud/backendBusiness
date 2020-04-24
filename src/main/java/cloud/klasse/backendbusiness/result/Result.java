package cloud.klasse.backendbusiness.result;

import cloud.klasse.backendbusiness.task.Task;
import cloud.klasse.backendbusiness.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    private Long id;

    private String title;

    private String comment;

    @ManyToOne
    private Task task;

    @OneToOne
    private User user;

    private String state;
}
