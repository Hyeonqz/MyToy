package spring.project.toy.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
	void createQuestionTest() {
		// Given
		QuestionRepository mockQuestionRepository = mock(QuestionRepository.class);
		QuestionController questionController = new QuestionController((QuestionRepository)mockQuestionRepository);

		QuestionRequestDto questionRequestDto = QuestionRequestDto.builder()
			.subject("테스트 주제")
			.content("테스트 내용")
			.build();

		// When
		//String result = questionController.생성메서드 넣기

		// Then
		verify(mockQuestionRepository, times(1)).save(any());
		// 적절한 결과 확인 로직을 추가해야 합니다.
	}
}
