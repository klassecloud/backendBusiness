package cloud.klasse.backendbusiness.file;


import cloud.klasse.backendbusiness.result.Result;
import cloud.klasse.backendbusiness.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Lob;
import java.util.List;

@Entity
@Table(name = "File")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String alt;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false, columnDefinition = "boolean")
    private boolean isCompressed;

    @Column(nullable = false)
    private String mimeType;

    @ManyToOne
    @JoinColumn(name = "Resultid")
    private Result result;

    @ManyToMany(mappedBy = "files")
    private List<Task> tasks;
}

