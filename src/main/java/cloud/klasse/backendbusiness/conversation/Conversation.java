package cloud.klasse.backendbusiness.conversation;

import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Conversation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {

    @Id
    private Long id;

    @ManyToOne
    private Teacher teacher;

}
