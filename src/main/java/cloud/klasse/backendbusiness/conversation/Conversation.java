package cloud.klasse.backendbusiness.conversation;

import cloud.klasse.backendbusiness.message.Message;
import cloud.klasse.backendbusiness.student.Student;
import cloud.klasse.backendbusiness.subject.Subject;
import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "Conversation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @ToString.Exclude
    private Teacher teacher;

    @OneToMany(mappedBy = "conversation")
    @ToString.Exclude
    private List<Message> messages;

    @OneToOne(mappedBy = "conversation")
    @ToString.Exclude
    private Subject subject;

    @ManyToMany(mappedBy = "conversations")
    @ToString.Exclude
    private List<Student> students;

}
