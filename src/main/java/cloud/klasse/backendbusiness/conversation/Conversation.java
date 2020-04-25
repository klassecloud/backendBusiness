package cloud.klasse.backendbusiness.conversation;

import cloud.klasse.backendbusiness.message.Message;
import cloud.klasse.backendbusiness.subject.Subject;
import cloud.klasse.backendbusiness.teacher.Teacher;
import cloud.klasse.backendbusiness.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name="Teacherid")
    private Teacher teacher;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;

    @OneToOne(mappedBy = "conversation")
    private Subject subject;

    @ManyToMany(mappedBy = "conversations")
    private List<User> users;
}
