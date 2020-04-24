package cloud.klasse.backendbusiness.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String userName;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean isValidated;

}
