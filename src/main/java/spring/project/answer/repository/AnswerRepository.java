package spring.project.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.answer.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
