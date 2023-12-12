package absens.test.controller;

import absens.test.entity.Absensi;
import absens.test.entity.User;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.service.AbsensiService;
import absens.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AbsensiService absensiService;


    @PostMapping("/add_dataUser")
    public ResponseEntity<?> addUser(@Validated @RequestBody User user) {
        try {
            Object existingUser = userService.getUserByNpm(user.getNpm());


            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Peserta Dengan NPM " + user.getNpm() + " Sudah Terdaftar");

            }

            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>("Data Dengan " + user +" berhasil ditambahkan", HttpStatus.CREATED);
        } catch (CustomIllegalArgumentException e) {
            String errorMessage = "Invalid request : " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = " An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
//    @PostMapping("/{npm}/checkin")
//    public ResponseEntity<String> checkin(@PathVariable int npm, @RequestBody Absensi absensi) {
//        return processAbsensi(npm, "checkin", absensi);
//    }
//
//    @PostMapping("/{npm}/checkout")
//    public ResponseEntity<String> checkout(@PathVariable int npm, @RequestBody Absensi absensi) {
//        return processAbsensi(npm, "checkout", absensi);
//    }
//@PostMapping("/checkin")
//public ResponseEntity<String> checkin(@RequestParam int npm) {
//    try {
//        User user = userService.getUserByNpm(npm);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        Absensi checkinData = new Absensi();
//        checkinData.setUser(user);
//        checkinData.setStatus("checkin");
//        checkinData.setTimestamp(LocalDateTime.now());
//        checkinData.setWaktuMasuk(LocalDateTime.now());
//
//        absensiService.saveAbsensi(checkinData);
//
//        return ResponseEntity.ok("Checkin successful");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//    }
//}
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(@RequestParam int npm,
//                                           @RequestParam String location,
//                                           @RequestParam String name,
//                                           @RequestParam String typemagang,
//                                           @RequestParam String kapal,
//                                           @RequestParam String divisi) {
//        try {
//            User user = userService.getUserByNpm(npm);
//            if (user == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//            }
//
//            Absensi checkoutData = new Absensi();
//            checkoutData.setUser(user);
//            checkoutData.setStatus("checkout");
//            checkoutData.setTimestamp(LocalDateTime.now());
//            checkoutData.setWaktuKeluar(LocalDateTime.now());
//            checkoutData.setLocation(location);
//            checkoutData.setName(name);
//            checkoutData.setTypemagang(typemagang);
//            checkoutData.setKapal(kapal);
//            checkoutData.setDivisi(divisi);
//
//            absensiService.saveAbsensi(checkoutData);
//
//            return ResponseEntity.ok("Checkout successful");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
//@PostMapping("/checkin")
//public ResponseEntity<String> checkin(@RequestParam int npm) {
//    try {
//        if (npm <= 0) {
//            return ResponseEntity.badRequest().body("Invalid input for checkin");
//        }
//
//        User user = userService.getUserByNpm(npm);
//        if (user == null) return ResponseEntity.ok("Checkin successful");
//
//        Absensi checkinData = new Absensi();
//        checkinData.setUser(user);
//        checkinData.setStatus("checkin");
//        checkinData.setTimestamp(LocalDateTime.now());
//        checkinData.setWaktuMasuk(LocalDateTime.now());
//
//        absensiService.saveAbsensi(checkinData);
//
//        return ResponseEntity.ok("Checkin successful");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during checkin: " + e.getMessage());
//    }
//}
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(@RequestParam int npm,
//                                           @RequestBody Map<String, String> checkoutData) {
//        try {
//            if (npm <= 0) {
//                return ResponseEntity.badRequest().body("Invalid input for checkout");
//            }
//
//            String location = checkoutData.get("location");
//            String name = checkoutData.get("name");
//            String typemagang = checkoutData.get("typemagang");
//            String kapal = checkoutData.get("kapal");
//            String divisi = checkoutData.get("divisi");
//
//            absensiService.checkout(npm, location, name, typemagang, kapal, divisi);
//
//            return ResponseEntity.ok("Checkout successful");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during checkout: " + e.getMessage());
//        }
//    }
//@PostMapping("/checkin")
//public ResponseEntity<String> checkin(@RequestHeader int npm) {
//    try {
//        User user = userService.getUserByNpm(npm);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        Absensi checkinData = new Absensi();
//        checkinData.setUser(user);
//        checkinData.setStatus("checkin");
//        checkinData.setTimestamp(LocalDateTime.now());
//        checkinData.setWaktuMasuk(LocalDateTime.now());
//
//        absensiService.saveAbsensi(checkinData);
//
//        return ResponseEntity.ok("Checkin successful");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//    }
//}
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(@RequestHeader int npm) {
//        try {
//            Absensi existingCheckin = absensiService.getExistingCheckin(npm);
//            if (npm <= 0) {
//             return ResponseEntity.badRequest().body("Invalid input for checkout");
//            }
//
//            // Update existing checkin with checkout data
//            existingCheckin.setStatus("checkout");
//            existingCheckin.setTimestamp(LocalDateTime.now());
//            existingCheckin.setWaktuKeluar(LocalDateTime.now());
//
//            absensiService.saveAbsensi(existingCheckin);
//
//            return ResponseEntity.ok("Checkout successful");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during checkout: " + e.getMessage());
//        }
//    }
//@PostMapping("/checkin")
//public ResponseEntity<String> checkin(@RequestHeader("npm") int npm) {
//    try {
//        User user = userService.getUserByNpm(npm);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        Absensi checkinData = new Absensi();
//        checkinData.setUser(user);
//        checkinData.setStatus("checkin");
//        checkinData.setTimestamp(LocalDateTime.now());
//        checkinData.setWaktuMasuk(LocalDateTime.now());
//
//        // Set name from user
//        checkinData.setName(user.getName());
//
//        absensiService.saveAbsensi(checkinData);
//
//        return ResponseEntity.ok("Checkin With NPM "+npm+" Succesfull");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//    }
//}

//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(@RequestHeader("npm") int npm) {
//        try {
//            Absensi existingCheckin = absensiService.getExistingCheckin(npm);
//            if (existingCheckin == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            // Update existing checkin with checkout data
//            existingCheckin.setStatus("checkout");
//            existingCheckin.setTimestamp(LocalDateTime.now());
//            existingCheckin.setWaktuKeluar(LocalDateTime.now());
//
//            absensiService.saveAbsensi(existingCheckin);
//
//            return ResponseEntity.ok("Checkout successful");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during checkout: " + e.getMessage());
//        }
//    }
//@PostMapping("/checkout")
//public ResponseEntity<String> checkout(@RequestHeader("npm") int npm,
//                                       @RequestHeader("divisi") String divisi,
//                                       @RequestHeader("kapal") String kapal,
//                                       @RequestHeader("location") String location,
//                                       @RequestHeader("typemagang") String typemagang,
//@RequestHeader("name")String name){
//    try {
//        Absensi existingCheckin = absensiService.getExistingCheckin(npm);
//        if (existingCheckin == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Update existing checkin with checkout data
//        existingCheckin.setStatus("checkout");
//        existingCheckin.setTimestamp(LocalDateTime.now());
//        existingCheckin.setWaktuKeluar(LocalDateTime.now());
//        existingCheckin.setDivisi(divisi);
//        existingCheckin.setKapal(kapal);
//        existingCheckin.setLocation(location);
//        existingCheckin.setTypemagang(typemagang);
//        existingCheckin.setName(name);
//
//        absensiService.saveAbsensi(existingCheckin);
//
//        return ResponseEntity.ok("Checkout successful");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during checkout: " + e.getMessage());
//    }
//}
@PostMapping("/checkin")
public ResponseEntity<String> checkin(@RequestHeader("npm") int npm,
                                      @RequestHeader("divisi") String divisi,
                                      @RequestHeader("kapal") String kapal,
                                      @RequestHeader("location") String location,
                                      @RequestHeader("typemagang") String typemagang) {
    try {
        // Validasi NPM
        if (npm <= 0) {
            return ResponseEntity.badRequest().body("Invalid NPM");
        }

        User user = userService.getUserByNpm(npm);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Validasi data lainnya sesuai kebutuhan

        Absensi checkinData = new Absensi();
        checkinData.setUser(user);
        checkinData.setStatus("masuk");
        checkinData.setTimestamp(LocalDateTime.now());
        checkinData.setWaktuMasuk(LocalDateTime.now());
        checkinData.setDivisi(divisi);
        checkinData.setKapal(kapal);
        checkinData.setLocation(location);
        checkinData.setTypemagang(typemagang);

        // Set name from user
        checkinData.setName(user.getName());

        absensiService.saveAbsensi(checkinData);

        return ResponseEntity.ok("Checkin With NPM " + npm + " Successful");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestHeader("npm") int npm,
                                           @RequestHeader("divisi") String divisi,
                                           @RequestHeader("kapal") String kapal,
                                           @RequestHeader("location") String location,
                                           @RequestHeader("typemagang") String typemagang) {
        try {
            // Validasi NPM
            if (npm <= 0) {
                return ResponseEntity.badRequest().body("Invalid NPM");
            }

            Absensi existingCheckin = absensiService.getExistingCheckin(npm);
            if (existingCheckin == null) {
                return ResponseEntity.notFound().build();
            }

            // Validasi data lainnya sesuai kebutuhan

            // Update existing checkin with checkout data
            existingCheckin.setStatus("masuk");
            existingCheckin.setTimestamp(LocalDateTime.now());
            existingCheckin.setWaktuKeluar(LocalDateTime.now());
            existingCheckin.setDivisi(divisi);
            existingCheckin.setKapal(kapal);
            existingCheckin.setLocation(location);
            existingCheckin.setTypemagang(typemagang);

            absensiService.saveAbsensi(existingCheckin);

            return ResponseEntity.ok("Checkout With NPM " + npm + " Successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during checkout: " + e.getMessage());
        }
    }

    @GetMapping("/total-absensi")
    public ResponseEntity<Long> getTotalAbsensi() {
        try {
            Long totalAbsensi = absensiService.getTotalAbsensi();
            return ResponseEntity.ok(totalAbsensi);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/all-absensi")
    public ResponseEntity<List<Absensi>> getAllAbsensi() {
        try {
            List<Absensi> allAbsensi = absensiService.getAllAbsensi();
            return ResponseEntity.ok(allAbsensi);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> processAbsensi(int npm, String status, Absensi absensi) {
        try {
            User user = userService.getUserByNpm(npm);

            absensi.setTimestamp(LocalDateTime.now());
            absensi.setUser(user);

            absensiService.saveAbsensi(absensi);

            return ResponseEntity.ok("Status " + status + " successfully recorded for user " + user.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/search_Data/{Id}")
    public ResponseEntity<String> findUserById(@PathVariable int Id) {
        try {
            User user = userService.getUserById(Id);
            return user != null ? ResponseEntity.ok(String.valueOf(user)) : ResponseEntity.notFound().build();
        } catch (CustomIllegalArgumentException e) {
            String errorMessage = "Invalid request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = " An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/search_dataByNpm/{npm}")
    public ResponseEntity<Object> findUserByNpm(@PathVariable int npm) {
        try {
            User user = userService.getUserByNpm(npm);
            return ResponseEntity.ok(user);
        } catch (CustomIllegalArgumentException e) {
            String errorMessage = "User not found: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = " An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

//    @PutMapping("/update_user")
//    public ResponseEntity<String> updateUser(@Validated @RequestBody User user) {
//        try {
//            User updatedUser = userService.updateUser(user);
//            return updatedUser != null ? ResponseEntity.ok(String.valueOf(updatedUser)) : ResponseEntity.notFound().build();
//        } catch (CustomIllegalArgumentException e) {
//            String errorMessage = "Invalid request: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//        } catch (Exception e) {
//            String errorMessage = " An error occurred while processing the request: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//        }
//    }

    @DeleteMapping("/delete_userById/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable int Id) {
        try {
            String result = userService.deleteUser(Id);
            return ResponseEntity.ok(result);
        } catch (CustomIllegalArgumentException e) {
            String errorMessage = "Invalid request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = " An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}







