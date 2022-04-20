package recipes.User.Infrastructure.Repository.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.User.Domain.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
