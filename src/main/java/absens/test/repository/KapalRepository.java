package absens.test.repository;
import absens.test.entity.Kapal;
import absens.test.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
@EnableJpaRepositories
public interface KapalRepository extends JpaRepository <Kapal,Integer>{



    Kapal findById(int idkapal);
}
