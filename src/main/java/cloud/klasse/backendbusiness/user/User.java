package cloud.klasse.backendbusiness.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}