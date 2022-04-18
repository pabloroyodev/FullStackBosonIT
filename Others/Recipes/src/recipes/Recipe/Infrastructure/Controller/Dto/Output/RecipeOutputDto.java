package recipes.Recipe.Infrastructure.Controller.Dto.Output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.Recipe.Domain.Recipe;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class RecipeOutputDto implements Serializable {
    private Integer id;

    public RecipeOutputDto(Recipe recipe) {
        setId(recipe.getId());
    }
}
