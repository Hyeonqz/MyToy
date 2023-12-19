package spring.project.toy;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.project.dto.Question;
import spring.project.repository.QuestionRepository;

@SpringBootTest
class MyToyApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void findByIdTest() {
		Optional<Question> oq = this.questionRepository.findById(5);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("안녕?",q.getSubject());
		}
	}

	@Test
	void findBySubjectTest() {
		Question q = this.questionRepository.findBySubject("안녕?");
		assertEquals("5", q.getId());
	}

	@Test
	void testJpa() {
		List<Question> qList = this.questionRepository.findBySubjectLike("안녕%");
		Question q = qList.get(0);
		assertEquals("안녕?", q.getSubject());
	}

	@Test
	void Update() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}
	@Test
	void deleteTest() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(5);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(5, this.questionRepository.count());
	}


}
