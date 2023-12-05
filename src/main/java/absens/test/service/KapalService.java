package absens.test.service;

import absens.test.entity.Kapal;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.repository.KapalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KapalService {



    @Autowired
    private KapalRepository kapalRepository;
    public KapalService(KapalRepository repository) {
        this.kapalRepository = repository;
    }

    public Kapal saveKapal(Kapal kapal) {
        return kapalRepository.save(kapal);
    }

    public Page<Kapal> getKapal(int page, int size) {
        Pageable pageable;
        pageable = PageRequest.of(page, size);
        return kapalRepository.findAll(pageable);
    }

    public Kapal getkapalByid(int idkapal) {
        Optional<Kapal> optionalKapal = Optional.ofNullable(kapalRepository.findById(idkapal));
        return optionalKapal.orElse(null);
    }

    public String deleteKapal(int idkapal) {
        // Cari kapal berdasarkan ID
        Optional<Kapal> optionalKapal = Optional.ofNullable(kapalRepository.findById(idkapal));

        // Jika kapal tidak ditemukan, lempar CustomIllegalArgumentException
        if (optionalKapal.isEmpty()) {
            throw new CustomIllegalArgumentException("Kapal with ID " + idkapal + " not found");
        }

        kapalRepository.deleteById(idkapal);
        return "Data with ID " + idkapal + " deleted successfully";
    }

    public Kapal updateKapal(Kapal kapal) {
        Optional<Kapal> optionalExistingKapal = Optional.ofNullable(kapalRepository.findById(kapal.getIdkapal()));

        // Jika kapal tidak ditemukan, lempar CustomIllegalArgumentException
        Kapal existingKapal = optionalExistingKapal.orElseThrow(() -> new CustomIllegalArgumentException("Kapal with ID " + kapal.getIdkapal() + " not found"));

        existingKapal.setNamakapal(kapal.getNamakapal());
        return kapalRepository.save(existingKapal);
    }
}




