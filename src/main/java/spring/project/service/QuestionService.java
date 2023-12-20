package spring.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.dto.Question;
import spring.project.repository.QuestionRepository;
import spring.project.toy.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	public List<Question> getList() {
		return this.questionRepository.findAll();
	}

	//num에 해당하는 question 데이터 조회
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	public void create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setWirteday(LocalDateTime.now());
		this.questionRepository.save(q);
	}

	public Page<Question> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<Sort.Order>();
		sorts.add(Sort.Order.desc("wirteday"));
		Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}

}
