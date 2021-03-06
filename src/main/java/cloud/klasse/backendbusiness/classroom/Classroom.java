package cloud.klasse.backendbusiness.classroom;

import cloud.klasse.backendbusiness.subject.Subject;
import cloud.klasse.backendbusiness.teacher.Teacher;
import cloud.klasse.backendbusiness.student.Student;
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
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Classroom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String topic;

    @ManyToOne
    @JoinColumn(name = "Teacherid")
    private Teacher teacher;

    private String pushPublicKey;

    private String pushPrivateKey;

    @OneToMany(mappedBy = "classroom")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "classroom")
    private List<Student> students;

    public Classroom(long id, String topic, String pushPublicKey, String pushPrivateKey, Teacher teacher) {
        this.id = id;
        this.topic = topic;
        this.teacher = teacher;
        this.pushPublicKey = pushPublicKey;
        this.pushPrivateKey = pushPrivateKey;
        this.subjects = new ArrayList<>();
        this.students = new ArrayList<>();
    }
}
