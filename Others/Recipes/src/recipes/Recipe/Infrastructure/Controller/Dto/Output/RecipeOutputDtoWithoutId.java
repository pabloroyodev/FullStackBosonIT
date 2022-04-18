package recipes.Recipe.Infrastructure.Controller.Dto.Output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.Recipe.Domain.Recipe;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeOutputDtoWithoutId implements Serializable {
    private String name;
    private String category;
    private Date date;
    private String description;
    private List<String> ingredients;
    private List<String> directions;

    public RecipeOutputDtoWithoutId(Recipe recipe) {
        setName(recipe.getName());
        setCategory(recipe.getCategory());
        setDate(recipe.getDate());
        setDescription(recipe.getDescription());
        setIngredients(recipe.getIngredients());
        setDirections(recipe.getDirections());
    }
}