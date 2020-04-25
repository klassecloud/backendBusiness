package cloud.klasse.backendbusiness.teacher;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.school.School;
import cloud.klasse.backendbusiness.subject.Subject;
import cloud.klasse.backendbusiness.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@EqualsAndHashCode
public class Teacher extends User {


    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "boolean")
    private boolean isValidated;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "teacher")
    private List<Conversation> conversations;

    @OneToMany(mappedBy = "teacher")
    private List<Classroom>  classrooms;

    @ManyToMany(mappedBy = "teachers")
    private List<School> schools;

    public Teacher(long id, String userName, String nickname, String email, String password, boolean isValidated) {
        super(id, userName, password);
        this.nickName = nickname;
        this.email = email;
        this.isValidated = isValidated;
        this.subjects = new ArrayList<>();
        this.conversations = new ArrayList<>();
        this.schools = new ArrayList<>();
    }

}
