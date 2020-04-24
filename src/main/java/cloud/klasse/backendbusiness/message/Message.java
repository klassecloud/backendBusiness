package cloud.klasse.backendbusiness.message;


import cloud.klasse.backendbusiness.conversation.Conversation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name = "Message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false)
    private Long sender;

    @ManyToOne
    @JoinColumn(name = "Conversationid", nullable = false)
    private Conversation conversation;
}
