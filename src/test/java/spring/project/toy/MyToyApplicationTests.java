package spring.project.toy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.project.domain.question.QuestionRepository;
import spring.project.web.service.QuestionService;
import spring.project.web.service.UserService;

@SpringBootTest
class MyToyApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
	}
/*	@Test
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
	}*/
/*	@Test
	public void LargeScale() {
		for (int i = 0; i < 300; i++) {
			String subject = String.format("테스트 데이터 입니다 :[%03d]",i);
			String content = "내용은 없다ㅋ";
			this.questionService.create(subject, content);
		}
	}*/

	@Test
	public void TestAuthor() {
		for (int i = 0; i <=300 ; i++) {
			String subject = String.format("테스트 데이터 입니다:[%03d]",i);
			String content = "정상 작동";

			this.questionService.create(subject, content,null);
		}
	}

}
