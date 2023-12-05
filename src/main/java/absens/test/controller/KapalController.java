package absens.test.controller;

import absens.test.entity.Kapal;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.service.KapalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class KapalController {

    private final KapalService kapalService;

    @Autowired
    public KapalController(KapalService kapalService) {
        this.kapalService = kapalService;
    }

    @PostMapping("/add_data_Kapal")
    public ResponseEntity<String> addKapal(@Validated @RequestBody Kapal kapal) {
        Kapal savedKapal = kapalService.saveKapal(kapal);
        if (savedKapal != null) {
            return ResponseEntity.ok("Data Kapal dengan ID : " + savedKapal.getIdkapal() +
                    " || dengan nama : " + savedKapal.getNamakapal() + " berhasil ditambahkan");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal menambahkan data Kapal");
        }
    }

    @GetMapping("/showkapal")
    public ResponseEntity<Page<Kapal>> findAllBiodata(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Kapal> kapalPage = kapalService.getKapal(page, size);
        return ResponseEntity.ok(kapalPage);
    }

    @GetMapping("/show_kapalid/{id}")
    public ResponseEntity<Kapal> findByidkapal(@PathVariable int id) {
        Kapal kapal = kapalService.getkapalByid(id);
        if (kapal != null) {
            return ResponseEntity.ok(kapal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateKapal(@Validated @RequestBody Kapal kapal) {
        try {
            Kapal updatedKapal = kapalService.updateKapal(kapal);
            if (updatedKapal != null) {
                return ResponseEntity.ok("Data Kapal dengan ID: " + updatedKapal.getIdkapal() + " berhasil diperbarui");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal memperbarui data Kapal");
            }
        } catch (CustomIllegalArgumentException e) {
            String errorMessage = "Invalid request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/delete_kapalById/{id}")
    public ResponseEntity<String> deleteKapal(@PathVariable int id) {
        try {
            String result = kapalService.deleteKapal(id);
            return ResponseEntity.ok(result);
        } catch (CustomIllegalArgumentException e) {
            String errorMessage = "Invalid request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
