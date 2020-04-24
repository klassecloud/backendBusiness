package cloud.klasse.backendbusiness.conversation;

import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Conversation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {

    private Long id;

    private Teacher teacherId;
}
