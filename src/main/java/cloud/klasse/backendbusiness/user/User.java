package cloud.klasse.backendbusiness.user;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * User database entity.
 *
 * @author sandra.gerberding
 * @since 0.0.1
 *
 * @see Entity
 * @see Table
 * @see Data
 * @see AllArgsConstructor
 * @see NoArgsConstructor
 */
@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "boolean")
    private boolean isActivated = false;

    @ManyToOne
    @JoinColumn(name = "Classroomid")
    private Classroom classroom;

    @OneToMany(mappedBy = "user")
    private List<Result> results;

    @ManyToMany
    @JoinTable(
            name = "User_Conversation",
            joinColumns = @JoinColumn(name = "Userid"),
            inverseJoinColumns = @JoinColumn(name = "Conversationid"))
    private List<Conversation> conversations;


}