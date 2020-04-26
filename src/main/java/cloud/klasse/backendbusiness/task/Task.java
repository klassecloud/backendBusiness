package cloud.klasse.backendbusiness.task;


import cloud.klasse.backendbusiness.file.File;
import cloud.klasse.backendbusiness.result.Result;
import cloud.klasse.backendbusiness.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2500)
    private String content;

    private Timestamp dueDate;

    @ManyToOne
    private Subject subject;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "Task_File",
            joinColumns = @JoinColumn(name = "Taskid"),
            inverseJoinColumns = @JoinColumn(name = "Fileid"))
    private List<File> files;
}
