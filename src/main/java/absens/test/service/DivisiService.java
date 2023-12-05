package absens.test.service;
import absens.test.entity.Divisi;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.repository.DivisiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service

public class DivisiService {
    private static DivisiRepository kapalRepository ;

    @Autowired

    public DivisiService(DivisiRepository repository) {
        DivisiService.kapalRepository = repository;
    }

    public static Divisi saveDivisi(Divisi divisi) {
        return kapalRepository.save(divisi);
    }


    public Page<Divisi> getDivisi(int page, int size) {
        Pageable pageable;
        pageable = PageRequest.of(page, size);
        return kapalRepository.findAll(pageable);
    }

    public Divisi getdivisiByid(int iddivisi) {
        Optional<Divisi> optionalDivisi = Optional.ofNullable(kapalRepository.findByiddivisi(iddivisi));
        return optionalDivisi.orElse(null);
    }

    public String deleteDivisi(int iddivisi) {
        // Cari kapal berdasarkan ID
        Optional<Divisi> optionalDivisi = Optional.ofNullable(kapalRepository.findByiddivisi(iddivisi));

        // Jika kapal tidak ditemukan, lempar CustomIllegalArgumentException
        if (optionalDivisi.isEmpty()) {
            throw new CustomIllegalArgumentException("Kapal with ID " + iddivisi + " not found");
        }

        kapalRepository.deleteById(iddivisi);
        return "Data with ID " + iddivisi + " deleted successfully";
    }

    public Divisi updateDivisi(Divisi divisi) {
        Optional<Divisi> optionalExistingDivisi = Optional.ofNullable(kapalRepository.findByiddivisi(divisi.getIddivisi()));

        // Jika kapal tidak ditemukan, lempar CustomIllegalArgumentException
        Divisi existingDivisi = optionalExistingDivisi.orElseThrow(() -> new CustomIllegalArgumentException("Kapal with ID " + divisi.getIddivisi() + " not found"));

        existingDivisi.setNamadivisi(divisi.getNamadivisi());
        return kapalRepository.save(existingDivisi);
    }
}