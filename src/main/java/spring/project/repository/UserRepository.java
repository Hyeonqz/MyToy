package spring.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.project.dto.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser,Long> {
	Optional<SiteUser> findByUsername(String username);
}
