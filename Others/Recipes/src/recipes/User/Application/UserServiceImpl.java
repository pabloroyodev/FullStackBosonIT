package recipes.User.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.User.Domain.User;
import recipes.User.Infrastructure.Controller.Dto.Input.UserInputDto;
import recipes.User.Infrastructure.Repository.Jpa.UserRepository;
import recipes.Utils.CustomExceptions;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserInputDto userInputDto) {
        if (userRepository.findById(userInputDto.getEmail()).isEmpty()) {
            User user = UserInputDtoToEntity(userInputDto);
            userRepository.save(user);
            return;
        }
        throw new CustomExceptions.BadRequest();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(CustomExceptions.NotFound::new);

    }

    public User UserInputDtoToEntity(UserInputDto userInputDto) {
        User user = new User();
        user.setEmail(userInputDto.getEmail());
        user.setPassword(passwordEncoder.encode(userInputDto.getPassword()));

        return user;
    }

}
