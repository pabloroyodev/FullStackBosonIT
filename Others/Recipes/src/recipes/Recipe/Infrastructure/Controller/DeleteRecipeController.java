package recipes.Recipe.Infrastructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.Recipe.Application.RecipeService;
import recipes.User.Domain.User;
import recipes.Utils.CustomExceptions;

@RequestMapping("api/recipe") @RestController @PreAuthorize("isAuthenticated()")
public class DeleteRecipeController {
    @Autowired
    RecipeService recipeService;

    @DeleteMapping("{id}")
    public void deleteRecipe(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        recipeService.deleteRecipe(id, user);
        throw new CustomExceptions.NoContent();
    }
}
