package absens.test.repository;

import absens.test.entity.Absensi;
import absens.test.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface AbsensiRepository extends JpaRepository<Absensi, Long> {
    List<Absensi> findByUser_NpmOrderByTimestampDesc(int npm);

    Absensi findByUserAndStatus(User user, String checkin);

    Absensi findByUser_NpmAndStatus(int npm, String checkin);
}