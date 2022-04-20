package recipes.User.Application;

import recipes.User.Infrastructure.Controller.Dto.Input.UserInputDto;

public interface UserService {
    void addUser(UserInputDto userInputDto);
}
