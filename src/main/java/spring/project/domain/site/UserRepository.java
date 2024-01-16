package spring.project.domain.site;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser,Long> {
	Optional<SiteUser> findByUsername(String username);
}
