package cloud.klasse.backendbusiness.subject;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.task.Task;
import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @ToString.Exclude
    private Teacher teacher;

    @OneToOne
    @ToString.Exclude
    private Conversation conversation;

    @ManyToOne
    @ToString.Exclude
    private Classroom classroom;

    @OneToMany(mappedBy = "subject")
    @ToString.Exclude
    private List<Task> tasks = new ArrayList<>();

    public Subject(final String name, final String description, final Classroom classroom) {
        this.name = name;
        this.description = description;
        this.classroom = classroom;
    }
}
