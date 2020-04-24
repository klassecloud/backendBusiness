package cloud.klasse.backendbusiness.school;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "School")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    private String street;

    private int zip;

    private String city;

    private int schoolId;

}
