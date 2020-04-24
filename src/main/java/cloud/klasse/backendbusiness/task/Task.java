package cloud.klasse.backendbusiness.task;


import cloud.klasse.backendbusiness.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    private Long id;

    private String title;

    private String content;

    private Timestamp dueDate;

    @ManyToOne
    private Subject subject;

}
