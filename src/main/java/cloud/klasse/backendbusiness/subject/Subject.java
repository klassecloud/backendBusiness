package cloud.klasse.backendbusiness.subject;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @javax.persistence.Id
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Teacher teacher;

    @OneToOne
    private Conversation conversation;

    @ManyToOne
    private Classroom classroom;

}
