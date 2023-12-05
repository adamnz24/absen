package absens.test.repository;
import absens.test.entity.Divisi;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
@EnableJpaRepositories
public interface DivisiRepository extends JpaRepository<Divisi,Integer> { ;

    Divisi findByiddivisi(int iddivisi);
}
