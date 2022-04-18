package recipes.Recipe.Infrastructure.Repository.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.Recipe.Domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe findFirstByOrderByIdDesc();
}
