package absens.test.entity;
import jakarta.persistence.*;
import lombok.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "cabang")
public class Cabang {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int idcabang;
    public String name;


}

