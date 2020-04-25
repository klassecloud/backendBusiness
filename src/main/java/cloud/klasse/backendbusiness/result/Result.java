package cloud.klasse.backendbusiness.result;

import cloud.klasse.backendbusiness.file.File;
import cloud.klasse.backendbusiness.task.Task;
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
import java.util.List;


@Entity
@Table(name = "Result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "Taskid", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "Studentid", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String state;

    @OneToMany(mappedBy = "result")
    private List<File> files;

}
