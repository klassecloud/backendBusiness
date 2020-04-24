package cloud.klasse.backendbusiness.classroom;

import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Classroom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    @Id
    private Long id;

    private String topic;

    @ManyToOne
    private Teacher teacher;

    private String pushPublicKey;

    private String pushPrivateKey;
}
