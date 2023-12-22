package spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser,Long> {
}
