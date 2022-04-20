package recipes.Recipe.Infrastructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.Recipe.Application.RecipeService;
import recipes.Recipe.Infrastructure.Controller.Dto.Input.RecipeInputDto;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDto;
import recipes.User.Domain.User;
import recipes.Utils.CustomExceptions;

import javax.validation.Valid;

@RequestMapping("api/recipe") @RestController @PreAuthorize("isAuthenticated()")
public class UpdateRecipeController {
    @Autowired
    RecipeService recipeService;

    @PutMapping("{id}")
    public RecipeOutputDto updateRecipe(@PathVariable Integer id, @Valid  @RequestBody RecipeInputDto recipeInputDto, @AuthenticationPrincipal User user) {
        recipeService.updateRecipe(id, recipeInputDto, user);
        throw new CustomExceptions.NoContent();
    }
}
