package cloud.klasse.backendbusiness.classroom;

import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Classroom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    private Long id;

    private String topic;

    private Teacher teacherId;

    private String pushPublicKey;

    private String pushPrivateKey;
}
