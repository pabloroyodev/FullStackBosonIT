package recipes.Recipe.Infrastructure.Repository.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.Recipe.Domain.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findAllByNameContainsIgnoreCaseOrderByDateDesc(String name);

    List<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);
}
