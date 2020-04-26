package cloud.klasse.backendbusiness.teacher;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.school.School;
import cloud.klasse.backendbusiness.subject.Subject;
import cloud.klasse.backendbusiness.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

/**
 * Teacher database entity.
 *
 */

@Entity
@Table(name = "Teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User {

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "boolean")
    private boolean isValidated;

    @ToString.Exclude
    @OneToMany(mappedBy = "teacher")
    private List<Classroom> classroom;
//
//    @OneToMany(mappedBy = "teacher")
//    private List<Subject> subjects;

    public Teacher(long id, String userName, String nickname, String email, String password, boolean isValidated) {
        super(id, userName, password);
        this.nickName = nickname;
        this.email = email;
        this.isValidated = isValidated;
        this.classroom = new ArrayList<>();
    }

}
