package recipes.User.Infrastructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recipes.User.Application.UserService;
import recipes.User.Infrastructure.Controller.Dto.Input.UserInputDto;
import recipes.Utils.CustomExceptions;

import javax.validation.Valid;

@RequestMapping("api/register")
@RestController
public class CreateUser {
    @Autowired
    UserService userService;

    @PostMapping
    public void addUser(@Valid @RequestBody UserInputDto userInputDto) {
        userService.addUser(userInputDto);
    }
}
