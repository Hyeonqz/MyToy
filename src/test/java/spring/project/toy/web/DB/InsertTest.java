package spring.project.toy.web.DB;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.project.domain.question.Question;
import spring.project.domain.question.QuestionRepository;
import spring.project.web.dto.QuestionDto;

@SpringBootTest
public class InsertTest {

	@Autowired
	private QuestionRepository questionRepository;

/*	@AfterEach
	public void cleanup() {
		questionRepository.deleteAll();
	}*/

	@Test
	void 저장된게시글_불러오기() {
		// given
		String subject = "안녕";
		String content = "ㅎㅇ";

		questionRepository.save(Question.builder()
			.subject(subject)
			.content(content)
			.build());

		// when
		List<Question> postList = questionRepository.findAll();

		// then
		Question question = postList.get(0);
		assertThat(question.getSubject()).isEqualTo(subject);
		assertThat(question.getContent()).isEqualTo(content);
	}

	@Test
	void 인서트됨_Test() {
		List<Question> list = this.questionRepository.findAll();
		assertEquals(2, list.size());

		Question q = list.get(0);
		assertEquals("1트다", q.getSubject());
	}

	@Test
	void findSubject_테스트() {
		Question q = this.questionRepository.findBySubject("1트다");
		assertEquals(1, q.getId());
	}

	@Test
	void findSubject_Content테스트() {
		Question q = this.questionRepository.findBySubjectAndContent("1트다","1트다");
		assertEquals(1, q.getId());
	}

	@Test
	void like_테스트() {
		List<Question> list = this.questionRepository.findBySubjectLike("1%");
		Question q = list.get(0);
		assertEquals("1트다",q.getSubject());
	}

	@Test
	void 수정_테스트() {
		Optional<Question> oq = this.questionRepository.findById(3L);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Question dto = Question.builder()
			.subject("3트다")
			.content("3트다")
			.build();
		this.questionRepository.save(q);
	}

	//삭제
	@Test
	void 삭제_테스트() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(3L);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}

}
