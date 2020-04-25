package cloud.klasse.backendbusiness.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cloud.klasse.backendbusiness.classroom.Classroom;
import cloud.klasse.backendbusiness.conversation.Conversation;
import cloud.klasse.backendbusiness.result.Result;
import cloud.klasse.backendbusiness.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@EqualsAndHashCode
public class Student extends User {

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, columnDefinition = "boolean")
    private boolean isActivated = false;

    @ManyToOne
    @JoinColumn(name = "Classroomid")
    private Classroom classroom;

    @OneToMany(mappedBy = "student")
    private List<Result> results;

    @ManyToMany
    @JoinTable(
            name = "Student_Conversation",
            joinColumns = @JoinColumn(name = "Studentid"),
            inverseJoinColumns = @JoinColumn(name = "Conversationid"))
    private List<Conversation> conversations;

    public Student(int id, String userName, String nickName, String password, boolean activated) {
        super(id, userName, password);
        this.nickName = nickName;
        this.isActivated = activated;
        this.results = new ArrayList<>();
        this.conversations = new ArrayList<>();
    }

}
