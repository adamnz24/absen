package absens.test.controller;
import absens.test.entity.Divisi;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.service.DivisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/divisi")
@RestController
public class DivisiController {
    private final DivisiService divisiService;
    @Autowired
    public DivisiController(DivisiService divisiService) {
        this.divisiService = divisiService;
    }

    @PostMapping("/add_data_divisi")
    public ResponseEntity<String> addDivisi(@Validated @RequestBody Divisi divisi) {
        Divisi savedDivisi = DivisiService.saveDivisi(divisi);
        return ResponseEntity.ok("Data Kapal dengan ID : " + savedDivisi.getIddivisi() +
                " || dengan nama : " + savedDivisi.getNamadivisi() + " berhasil ditambahkan");
    }

    @GetMapping("/showdivisi")
    public ResponseEntity<Page<Divisi>> findAllDivisi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Divisi> divisiPage = divisiService.getDivisi(page, size);
        return ResponseEntity.ok(divisiPage);
    }

    @GetMapping("/show_divisi_Byid/{iddivisi}")
    public ResponseEntity<Divisi> findByiddivisi(@PathVariable int iddivisi) {
        Divisi divisi = divisiService.getdivisiByid(iddivisi);
        if (divisi != null) {
            return ResponseEntity.ok(divisi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update_divisi")
    public ResponseEntity<String> updateDivisi(@Validated @RequestBody Divisi divisi) {
        try {
            Divisi updateDivisi = divisiService.updateDivisi(divisi);
            if (updateDivisi != null) {
                return ResponseEntity.ok("Data Kapal dengan ID: " + updateDivisi.getIddivisi() + " berhasil diperbarui");
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

    @DeleteMapping("/delete_divisiById/{iddivisi}")
    public ResponseEntity<String> deleteDivisi(@PathVariable int iddivisi) {
        try {
            String result = divisiService.deleteDivisi(iddivisi);
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