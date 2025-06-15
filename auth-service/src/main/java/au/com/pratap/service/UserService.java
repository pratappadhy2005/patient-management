package au.com.pratap.service;

import au.com.pratap.model.User;
import au.com.pratap.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is used to retrieve user details based on their email address.
     *
     * @param email
     * @return
     */
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
