package absens.test.controller;
import absens.test.entity.Cabang;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.service.CabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/cabang")
@RestController
public class CabangController {
    private final CabangService cabangService;
    @Autowired
    public CabangController(CabangService cabangService) {
        this.cabangService = cabangService;
    }

    @PostMapping("/adddata")
    public ResponseEntity<String> addCabang(@Validated @RequestBody Cabang cabang) {
        Cabang savedCabang = CabangService.saveCabang(cabang);
        return ResponseEntity.ok("Data Kapal dengan ID : " + savedCabang.getIdcabang() +
                " || dengan nama : " + savedCabang.getName() + " berhasil ditambahkan");
    }

    @GetMapping("/showcabang")
    public ResponseEntity<Page<Cabang>> findAllCabang(
            @RequestHeader(defaultValue = "0") int page,
            @RequestHeader(defaultValue = "10") int size) {
        Page<Cabang> cabangPage = cabangService.getCabang(page, size);
        return ResponseEntity.ok(cabangPage);
    }
    // Sort Users by Name in Ascending Order


    @GetMapping("/showcabangid")
    public ResponseEntity<Cabang> findByidcabang(
            @RequestHeader(name = "idcabang") int idcabang) {
        Cabang cabang = cabangService.getCabangByid(idcabang);
        if (cabang != null) {
            return ResponseEntity.ok(cabang);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatecabang")
    public ResponseEntity<String> updateCabang(@Validated @RequestBody Cabang cabang) {
        try {
            Cabang updateCabang = cabangService.updateCabang(cabang);
            if (updateCabang != null) {
                return ResponseEntity.ok("Data Kapal dengan ID: " + updateCabang.getIdcabang() + " berhasil diperbarui");
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

    @DeleteMapping("/deletecabangId/{idcabang}")
    public ResponseEntity<String> deleteCabang(@PathVariable int idcabang) {
        try {
            String result = cabangService.deleteCabang(idcabang);
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
