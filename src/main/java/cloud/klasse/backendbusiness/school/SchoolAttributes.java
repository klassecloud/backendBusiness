package cloud.klasse.backendbusiness.school;

import cloud.klasse.backendbusiness.teacher.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class SchoolAttributes implements SchoolModel {

    private String name;
    private String street;
    private Integer zip;
    private String city;
    private Integer schoolId;
    private List<Teacher> teachers;

}
