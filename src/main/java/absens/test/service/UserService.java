package absens.test.service;

import absens.test.entity.User;
import absens.test.exception.CustomIllegalArgumentException;
import absens.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to retrieve a user by NPM
    public User getUserByNpm(int npm) {

        // Your logic to handle the user when found
        // For example, you might perform additional operations or validations

        return userRepository.findByNpm(npm);
    }

    // Method to save a user
    public User saveUser(User user) {
        // Check if the user with the given NPM already exists
        User existingUser = getUserByNpm(user.getNpm());

        // If user already exists, throw a CustomIllegalArgumentException
        if (existingUser != null) {
            throw new CustomIllegalArgumentException("Peserta Dengan NPM " + user.getNpm() + " Sudah Terdaftar");
        }

        // Your logic to save the user
        // For example, userRepository.save(user);
        // ...

        // Return the saved user
        return userRepository.save(user);
    }

    public String getUsers() {
        if (userRepository.findAll().isEmpty()) {
            return "Silahkan Input Data Terlebih Dahulu";
        }
        return userRepository.findAll().toString();
    }

    public User getUserById(long id) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(id));

        // Gunakan orElseThrow untuk melempar CustomUserNotFoundException jika user tidak ditemukan
        return userOptional.orElseThrow(() -> new CustomIllegalArgumentException(" ID Dengan Nomor " + id +" tidak ditemukan"));
    }

    // Other methods...

    public User updateUser(@Validated User user) {
        validateUpdateUser(user);
        checkDuplicateNpmForUpdate(user.getId(), user.getNpm());
        User existingUser = userRepository.findById(user.getId());

        if (existingUser != null) {
            updateUserData(existingUser, user);
            return userRepository.save(existingUser);
        } else {
            // Handle if the user with the given ID is not found
            return null;
        }
    }

    private void checkDuplicateNpmForUpdate(Long id, int npm) {
    }

    private void validateUpdateUser(User user) {
    }

    // Other methods...

    public String updateUserData(User existingUser, User newUser) {
        existingUser.setName(newUser.getName());
        existingUser.setNpm(newUser.getNpm());
        existingUser.setTypemagang(newUser.getTypemagang());
        existingUser.setKapal(newUser.getKapal());
        existingUser.setCabang(newUser.getCabang());
        existingUser.setDivisi(newUser.getDivisi());
        return "Data User Berhasil Perbaharui";
    }

    public String deleteUser(long id) {
        // Cari user berdasarkan ID
        User user = userRepository.findById(id);

        // Jika user tidak ditemukan, lempar CustomIllegalArgumentException
        if (user == null) {
            throw new CustomIllegalArgumentException("User with ID " + id + " not found");
        }

        // Logika penghapusan user
        userRepository.delete(user);

        return "User with ID " + id + " has been successfully deleted ARRIGATOU";
    }
    }
