package absens.test.service;

import absens.test.entity.Absensi;
import absens.test.entity.User;
import absens.test.repository.AbsensiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class AbsensiService {
    private final AbsensiRepository absensiRepository;


    private UserService userService;
    private int npm;

    public AbsensiService(AbsensiRepository absensiRepository) {
        this.absensiRepository = absensiRepository;
    }



    public List<Absensi> getAbsensiByNpm(int npm) {
        return absensiRepository.findByUser_NpmOrderByTimestampDesc(npm);
    }

    public Long getTotalAbsensi() {
        return absensiRepository.count();
    }
        public void checkout(int npm, String location, String name, String typemagang, String kapal, String divisi) {
            User user = userService.getUserByNpm(npm);
            if (user != null) {
                Absensi existingCheckin = absensiRepository.findByUserAndStatus(user, "checkin");

                if (existingCheckin != null) {
                    // Update existing checkin with checkout data
                    existingCheckin.setStatus("checkout");
                    existingCheckin.setTimestamp(LocalDateTime.now());
                    existingCheckin.setWaktuKeluar(LocalDateTime.now());
                    existingCheckin.setLocation(location);
                    existingCheckin.setName(name);
                    existingCheckin.setTypemagang(typemagang);
                    existingCheckin.setKapal(kapal);
                    existingCheckin.setDivisi(divisi);

                    absensiRepository.save(existingCheckin);
                }
            }
        }
    public void saveAbsensi(Absensi absensi) {
        absensiRepository.save(absensi);
    }
@Transactional
    public Absensi getExistingCheckin(int npm) {
        this.npm = npm;
        return absensiRepository.findByUser_NpmAndStatus(npm, "checkin");
    }

    public List<Absensi> getAllAbsensi() {
        return null;
    }
}


