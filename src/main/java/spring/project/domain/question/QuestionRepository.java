package spring.project.domain.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {
	//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html 참고하기
	public Question findBySubject(String subject);
	public Question findBySubjectAndContent(String subject, String content);
	public List<Question> findBySubjectLike(String subject);

}
