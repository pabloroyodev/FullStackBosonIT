package recipes.Recipe.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.Recipe.Domain.Recipe;
import recipes.Recipe.Infrastructure.Controller.Dto.Input.RecipeInputDto;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDto;
import recipes.Recipe.Infrastructure.Controller.Dto.Output.RecipeOutputDtoWithoutId;
import recipes.Recipe.Infrastructure.Repository.Jpa.RecipeRepository;
import recipes.User.Domain.User;
import recipes.Utils.CustomExceptions;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<RecipeOutputDtoWithoutId> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(p -> new RecipeOutputDtoWithoutId(p)).collect(Collectors.toList());
    }

    @Override
    public RecipeOutputDtoWithoutId findById(Integer id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(CustomExceptions.NotFound::new);
        return new RecipeOutputDtoWithoutId(recipe);
    }

    @Override
    public RecipeOutputDto addRecipe(RecipeInputDto recipeInputDto, User user) {
        Recipe recipe = RecipeInputDtoToEntity(recipeInputDto);
        recipe.setAuthor(user);
        recipeRepository.save(recipe);
        return new RecipeOutputDto(recipe);
    }

    @Override
    public RecipeOutputDto updateRecipe(Integer id, RecipeInputDto recipeInputDto, User user) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(CustomExceptions.NotFound::new);

        if (recipe.getAuthor().equals(user)) {
            recipe.setName(recipeInputDto.getName());
            recipe.setCategory(recipeInputDto.getCategory());
            recipe.setDate(new Date());
            recipe.setDescription(recipeInputDto.getDescription());
            recipe.setIngredients(recipeInputDto.getIngredients());
            recipe.setDirections(recipeInputDto.getDirections());

            recipeRepository.save(recipe);
            return new RecipeOutputDto(recipe);
        }
        throw new CustomExceptions.ForbiddenRequest();
    }

    @Override
    public void deleteRecipe(Integer id, User user) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(CustomExceptions.NotFound::new);
        if (recipe.getAuthor().equals(user)) {
            recipeRepository.delete(recipe);
            return;
        }
        throw new CustomExceptions.ForbiddenRequest();
    }

    private Recipe RecipeInputDtoToEntity(RecipeInputDto recipeInputDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeInputDto.getName());
        recipe.setCategory(recipeInputDto.getCategory());
        recipe.setDate(new Date());
        recipe.setDescription(recipeInputDto.getDescription());
        recipe.setIngredients(recipeInputDto.getIngredients());
        recipe.setDirections(recipeInputDto.getDirections());

        return recipe;
    }
}
