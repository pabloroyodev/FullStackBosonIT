package recipes.Recipe.Infrastructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.Recipe.Application.RecipeService;
import recipes.Recipe.Infrastructure.Controller.Dto.Input.RecipeInputDto;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDto;

import javax.validation.Valid;

@RequestMapping("api/recipe")
@RestController
public class CreateRecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping("new")
    public RecipeOutputDto addRecipe(@Valid @RequestBody RecipeInputDto recipeInputDto) {
        return recipeService.addRecipe(recipeInputDto);
    }
}
