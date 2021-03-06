package cloud.klasse.backendbusiness.task;


import cloud.klasse.backendbusiness.file.File;
import cloud.klasse.backendbusiness.result.Result;
import cloud.klasse.backendbusiness.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
import java.util.ArrayList;
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
    @JoinColumn(name = "Subjectid", nullable = false)
    private Subject subject;

    @OneToMany(mappedBy = "task")
    private List<Result> results;

    @ManyToMany
    @JoinTable(
            name = "Task_File",
            joinColumns = @JoinColumn(name = "Taskid"),
            inverseJoinColumns = @JoinColumn(name = "Fileid"))
    private List<File> files;

    public Task(long id, String title, String content, Timestamp dueDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.subject = new Subject();//TO DO
        this.results = new ArrayList<>();
        this.files = new ArrayList<>();

    }
}
