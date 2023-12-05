package absens.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long id;

    public String name;

    @Column(unique = true)
    public int npm;

    public String typemagang;
    public String kapal;
    public String cabang;
    public String divisi;

    @Column(name = "checkin", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime checkin= LocalDateTime.now();
    @Column(name = "checkout", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime checkout=LocalDateTime.now();
}
