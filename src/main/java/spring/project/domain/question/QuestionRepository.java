package spring.project.domain.question;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html 참고하기
}
