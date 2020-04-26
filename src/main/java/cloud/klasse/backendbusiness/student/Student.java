package cloud.klasse.backendbusiness.student;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.result.Result;
import cloud.klasse.backendbusiness.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Student database entity.
 *
 * @since 0.0.1
 *
 * @see Entity
 * @see Table
 * @see Data
 * @see AllArgsConstructor
 * @see NoArgsConstructor
 */
@Entity
@Table(name = "Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, columnDefinition = "boolean")
    private boolean isActivated = false;

    @ManyToOne
    @ToString.Exclude
    private Classroom classroom;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Result> results;

    @ManyToMany
    @ToString.Exclude
    private List<Conversation> conversations;

    public Student(int id, String userName, String nickName, String password, boolean activated) {
        super(id, userName, password);
        this.nickName = nickName;
        this.isActivated = activated;
        this.results = new ArrayList<>();
        this.conversations = new ArrayList<>();
    }

}
