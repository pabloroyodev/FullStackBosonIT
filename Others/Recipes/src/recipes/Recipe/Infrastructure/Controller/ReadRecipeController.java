package recipes.Recipe.Infrastructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipes.Recipe.Application.RecipeService;
import recipes.Recipe.Domain.Recipe;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDto;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDtoWithoutId;
import recipes.Recipe.Infrastructure.Repository.Jpa.RecipeRepository;
import recipes.Utils.CustomExceptions;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/recipe")
@RestController
public class ReadRecipeController {
    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("all")
    public List<RecipeOutputDtoWithoutId> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("{id}")
    public RecipeOutputDtoWithoutId getById(@PathVariable Integer id) {
        return recipeService.findById(id);
    }

    @GetMapping(value ="search/", params = { "name" })
    public List<RecipeOutputDtoWithoutId> searchByName(@RequestParam(required = false) String name) {
        if (name == null) {throw new CustomExceptions.BadRequest();}
        return recipeRepository.findAllByNameContainsIgnoreCaseOrderByDateDesc(name).stream().map(RecipeOutputDtoWithoutId::new).collect(Collectors.toList());
    }

    @GetMapping(value ="search/", params = { "category" })
    public List<RecipeOutputDtoWithoutId> searchByCategory(@RequestParam(required = false) String category) {
        if (category == null) {throw new CustomExceptions.BadRequest();}
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category).stream().map(RecipeOutputDtoWithoutId::new).collect(Collectors.toList());
    }
}
