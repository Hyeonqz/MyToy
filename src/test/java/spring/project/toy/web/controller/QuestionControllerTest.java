package spring.project.toy.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import spring.project.question.domain.Question;
import spring.project.question.repository.QuestionRepository;
import spring.project.question.ui.QuestionController;
import spring.project.question.application.dto.request.QuestionRequestDto;
import spring.project.question.application.QuestionService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	private QuestionRepository questionRepository;

	@AfterEach
	public void tearDow() throws Exception {
		questionRepository.deleteAll();
	}

	@Test
	public void API_테스트() throws Exception{
		//given
		String subject = "subject";
		String content = "content";

		QuestionRequestDto questionRequestDto = QuestionRequestDto.builder()
			.subject(subject)
			.content(content)
			.build();

		String url = "http://localhost:" +port+"/api/v1/posts";

		//when
		ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, questionRequestDto, Long.class);
		//then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Question> all = questionRepository.findAll();
		assertThat(all.get(0).getSubject()).isEqualTo(subject);
		assertThat(all.get(0).getContent()).isEqualTo(content);
	}

	@Test
	public void BaseTimeEntity_등록() {
		//givne
		LocalDateTime now = LocalDateTime.of(2024,01,24,0,0,0);
		questionRepository.save(Question.builder()
			.subject("subject")
			.content("content")
			.build());

		//when
		List<Question> questionlist = questionRepository.findAll();

		//then
		Question question = questionlist.get(0);
		System.out.println(question.getCreateDate() + " " +question.getModifiedDate());

		assertThat(question.getCreateDate()).isAfter(now);
		assertThat(question.getModifiedDate()).isAfter(now);
	}

}
