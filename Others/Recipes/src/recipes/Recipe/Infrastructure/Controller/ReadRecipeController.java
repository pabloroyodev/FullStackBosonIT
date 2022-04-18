package recipes.Recipe.Infrastructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.Recipe.Application.RecipeService;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDtoWithoutId;

import java.util.List;

@RequestMapping("api/recipe")
@RestController
public class ReadRecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("all")
    public List<RecipeOutputDtoWithoutId> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("{id}")
    public RecipeOutputDtoWithoutId getById(@PathVariable Integer id) {
        return recipeService.findById(id);
    }

    @GetMapping
    public RecipeOutputDtoWithoutId getLastRecipe() {
        return recipeService.findLastRecipe();
    }
}
