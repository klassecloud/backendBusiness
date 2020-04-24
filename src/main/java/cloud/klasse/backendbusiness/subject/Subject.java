package cloud.klasse.backendbusiness.subject;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.task.Task;
import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "Subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "Teacherid", nullable = false)
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "Conversationid", referencedColumnName = "id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "Classroomid", nullable = false)
    private Classroom classroom;

    @OneToMany(mappedBy = "subject")
    private List<Task> tasks;
}
