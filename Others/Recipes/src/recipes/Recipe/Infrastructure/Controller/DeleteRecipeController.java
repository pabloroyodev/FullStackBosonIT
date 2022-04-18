package recipes.Recipe.Infrastructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipes.Recipe.Application.RecipeService;
import recipes.Utils.CustomExceptions;

@RequestMapping("api/recipe")
@RestController
public class DeleteRecipeController {
    @Autowired
    RecipeService recipeService;

    @DeleteMapping("{id}")
    public void deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
        throw new CustomExceptions.NoContent();
    }
}
