package usopshiy.web4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Setter
    @Getter
    @Column(name="email")
    private String email;
    @Setter
    @Getter
    @Column(name="password")
    private String password;
    @Setter
    @Getter
    @Column(name="login")
    private String login;
    @OneToMany(mappedBy = "user")
    @Getter
    @Setter
    private Set<Point> points;
}
