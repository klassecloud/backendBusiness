package cloud.klasse.backendbusiness.school;

import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "School")
@Data
@AllArgsConstructor
public class School implements SchoolModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String street;

    @Column
    private Integer zip;

    @Column
    private String city;

    @Column
    private Integer schoolId;

    @ManyToMany
    @JoinTable(
            name = "Teacher_School",
            joinColumns = @JoinColumn(name = "Schoolid"),
            inverseJoinColumns = @JoinColumn(name = "Teacherid"))
    private List<Teacher> teachers;

    public School() {
        teachers = new ArrayList<>();
    }

}
