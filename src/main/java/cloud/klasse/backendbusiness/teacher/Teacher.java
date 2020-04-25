package cloud.klasse.backendbusiness.teacher;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.school.School;
import cloud.klasse.backendbusiness.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

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
        this.id = id;
        this.userName = userName;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.isValidated = isValidated;
        this.subjects = new ArrayList<>();
        this.conversations = new ArrayList<>();
        this.schools = new ArrayList<>();

    }

}
