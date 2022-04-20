package recipes.Recipe.Application;

import recipes.Recipe.Infrastructure.Controller.Dto.Input.RecipeInputDto;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDto;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDtoWithoutId;
import recipes.User.Domain.User;

import java.util.List;

public interface RecipeService {
    List<RecipeOutputDtoWithoutId> getAllRecipes();
    RecipeOutputDtoWithoutId findById(Integer id);
    RecipeOutputDto addRecipe(RecipeInputDto recipeInputDto, User user);
    RecipeOutputDto updateRecipe(Integer id, RecipeInputDto recipeInputDto, User user);
    void deleteRecipe(Integer id, User user);
}
