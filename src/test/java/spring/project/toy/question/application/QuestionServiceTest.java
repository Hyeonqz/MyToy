package spring.project.toy.question.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.project.question.application.QuestionService;

@SpringBootTest
public class QuestionServiceTest {

	@Autowired
	private QuestionService questionService;

/*	@Test
	void 더미데이터() {
		for (int i = 1; i <=10000 ; i++) {
			String subject = String.format("테스트데이터[%03d]",i);
			String content = String.format("테스트내용[%03d",i);
			this.questionService.create(subject, content);
		}
	}*/
}
