package absens.test.controller;

import absens.test.entity.User;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

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



    @GetMapping("/searchData")
    public ResponseEntity<String> findAllProduct() {
        try {
            String users = userService.getUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            String errorMessage = "An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/search_Data/{id}")
    public ResponseEntity<String> findUserById(@PathVariable long id) {
        try {
            User user = userService.getUserById(id);
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

    @PutMapping("/update_user")
    public ResponseEntity<String> updateUser(@Validated @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return updatedUser != null ? ResponseEntity.ok(String.valueOf(updatedUser)) : ResponseEntity.notFound().build();
        } catch (CustomIllegalArgumentException e) {
            String errorMessage = "Invalid request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = " An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/delete_userById/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        try {
            String result = userService.deleteUser(id);
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







