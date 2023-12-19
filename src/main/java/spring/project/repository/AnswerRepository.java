package spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.Answer;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
