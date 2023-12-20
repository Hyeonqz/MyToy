package spring.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable);
}

