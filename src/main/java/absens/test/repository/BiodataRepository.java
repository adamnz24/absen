package absens.test.repository;

import absens.test.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BiodataRepository extends JpaRepository<Biodata, Integer> {

    Biodata findByNpm(int npm);

    // Tambahan method jika diperlukan

    void deleteBiodataById(int id);

    List<Biodata> findByNpmContaining(String npm);
}
