package cloud.klasse.backendbusiness.school;

import cloud.klasse.backendbusiness.teacher.Teacher;
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
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "School")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String street;

    @Column
    private int zip;

    @Column
    private String city;

    @Column
    private int schoolId;

    @ManyToMany
    @JoinTable(
            name = "Teacher_School",
            joinColumns = @JoinColumn(name = "Schoolid"),
            inverseJoinColumns = @JoinColumn(name = "Teacherid"))
    private List<Teacher> teachers;

}
