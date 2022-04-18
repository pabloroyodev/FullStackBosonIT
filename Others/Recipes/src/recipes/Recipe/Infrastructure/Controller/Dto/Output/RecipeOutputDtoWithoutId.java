package recipes.Recipe.Infrastructure.Controller.Dto.Output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.Recipe.Domain.Recipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeOutputDtoWithoutId implements Serializable {
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> directions;

    public RecipeOutputDtoWithoutId(Recipe recipe) {
        setName(recipe.getName());
        setDescription(recipe.getDescription());
        setIngredients(recipe.getIngredients());
        setDirections(recipe.getDirections());
    }
}