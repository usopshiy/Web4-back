package usopshiy.web4.entity;

import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="POINT")
public class Point {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    @Column(name = "id")
    private long id;
    @Getter
    @Setter
    @Column(name = "x")
    private double x;
    @Getter
    @Setter
    @Column(name = "y")
    private double y;
    @Getter
    @Setter
    @Column(name = "r")
    private Integer r;
    @Getter
    @Setter
    @Column(name = "isHit")
    private boolean isHit;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "email")
    private User user;

    public void check(){
        this.isHit = ((x <= 0 && y <= 0 && r >= 0) && (x >= -r && y >= -r)) ||
                ((x >= 0 && y <= 0 && r >= 0) && (y - 2*x >= -r)) ||
                ((x >= 0 && y >= 0 && r <= 0) && (x <= -r && y <= -r)) ||
                ((x <= 0 && y >= 0 && r <= 0) && (y - 2*x <= -r)) ||
                ((x >= 0 && y >= 0 && r >= 0) && (x*x + y*y <= r*r)) ||
                ((x <= 0 && y <= 0 && r<=0 ) && (x*x + y*y <= r*r));

    }
}
